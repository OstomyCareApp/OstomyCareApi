package com.ostomycare.api.infraestructure.security;

import com.ostomycare.api.infraestructure.reposity.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokeService;
    private  final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokeService, UsuarioRepository usuarioRepository) {
        this.tokeService = tokeService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token!=null){
            var email = tokeService.validarToken(token);
            UserDetails usuario = usuarioRepository.findByEmail(email);
            System.out.println(usuario);

            var autenticado= new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticado);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
           var authHeader= request.getHeader("Authorization");
           if(authHeader==null){
               return null;
           }

           else{
              return authHeader.replace("Bearer ","");
           }
    }
}
