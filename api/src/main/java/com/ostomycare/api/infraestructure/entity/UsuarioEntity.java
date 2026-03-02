package com.ostomycare.api.infraestructure.entity;

import com.ostomycare.api.infraestructure.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
public class UsuarioEntity implements UserDetails {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String telefone;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
    private LocalDateTime dataCadastro;
    private String numeroCoren;
    private String exercicioProfissional;

    public UsuarioEntity(
            String nome,
            String email,
            String telefone,
            String senha,
            TipoUsuario tipoUsuario,
            LocalDateTime dataCadastro,
            String numeroCoren,
            String exercicioProfissional
    ) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.dataCadastro = dataCadastro;
        this.numeroCoren = numeroCoren;
        this.exercicioProfissional = exercicioProfissional;
    }

    //Verifica o nível de autorização do usuário
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.tipoUsuario == TipoUsuario.PROFISSIONAL){
           return List.of(new SimpleGrantedAuthority("ROLE_PROFISSIONAL"), new SimpleGrantedAuthority("ROLE_PACIENTE"));
        }
        else{
            return List.of(new SimpleGrantedAuthority("ROLE_PACIENTE"));
        }
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
