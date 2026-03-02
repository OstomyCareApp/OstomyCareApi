package com.ostomycare.api.controller;

import com.ostomycare.api.controller.dto.AuthDTO;
import com.ostomycare.api.controller.dto.LoginDTO;
import com.ostomycare.api.controller.dto.RegisterDTO;
import com.ostomycare.api.infraestructure.entity.UsuarioEntity;
import com.ostomycare.api.infraestructure.reposity.UsuarioRepository;
import com.ostomycare.api.infraestructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager auth;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager auth, UsuarioRepository usuarioRepository) {
        this.auth = auth;
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    TokenService tokenService;

    @PostMapping("/entrar")
    public ResponseEntity entrar(@RequestBody @Validated AuthDTO data){
        var validacao = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.auth.authenticate(validacao);
        UsuarioEntity usuario = (UsuarioEntity) auth.getPrincipal();

        var token = tokenService.gerarToken((UsuarioEntity)auth.getPrincipal());
        return ResponseEntity.ok(new LoginDTO(token, usuario.getTipoUsuario().name(), usuario.getNome()));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar (@RequestBody @Validated RegisterDTO data){

        if(this.usuarioRepository.findByEmail(data.email())!=null)
            return ResponseEntity.badRequest().build();

        String senhaCriptografada =
                new BCryptPasswordEncoder().encode(data.senha());

        UsuarioEntity novoUsuario = new UsuarioEntity(
                data.nome(),
                data.email(),
                data.telefone(),
                senhaCriptografada,
                data.tipoUsuario(),
                LocalDateTime.now(),
                data.numeroCoren(),
                data.exercicioProfissional());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}