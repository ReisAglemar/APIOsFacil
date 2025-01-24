package com.APIosFacil.usuario.domain.model;

import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private boolean ativo;

    public UsuarioEntity() {
    }

    public UsuarioEntity(CadastraUsuarioDto usuario) {
        this.nome = usuario.nome();
        this.cpf = usuario.cpf();
        this.email = usuario.email();
        this.senha = usuario.senha();
        this.ativo = true;
    }

    public void inativarUsuario() {
        this.ativo = false;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(cpf, that.cpf) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, email);
    }

    public void atualizar(CadastraUsuarioDto usuarioDto) {

        if (usuarioDto.nome() != null) {
            this.nome = usuarioDto.nome();
        }

        if (usuarioDto.email() != null) {
            this.email = usuarioDto.email();
        }

        if (usuarioDto.senha() != null) {
            this.senha = usuarioDto.senha();
        }
    }
}
