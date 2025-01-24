package com.APIosFacil.usuario.domain.dto;

import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record CadastraUsuarioDto(

        @NotBlank(message = "{nome.notblank}")
        @Pattern(regexp = "[a-zA-Z]{3,}", message = "{nome.invalid}")
        String nome,


        @NotBlank(message = "{cpf.notblank}")
        @CPF(message = "{cpf.invalid}")
        String cpf,

        @NotBlank(message = "{email.notblank}")
        @Email(message = "{email.invalid}")
        String email,

        @NotBlank(message = "{senha.notblank}")
        @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{7,}", message = "{senha.invalid}")
        String senha) {

    public CadastraUsuarioDto(UsuarioEntity usuario) {
        this(
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha());
    }
}
