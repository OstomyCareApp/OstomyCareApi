package com.ostomycare.api.infraestructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ostomycare.api.infraestructure.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(UsuarioEntity usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token= JWT.create()
                    .withIssuer("ostomycare-api")
                    .withSubject(usuario.getEmail())
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException e){
            throw  new RuntimeException("Error ao gerar o token", e);
        }
    }

    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ostomycare-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e){
            return null;
        }
    }
}
