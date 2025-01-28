package com.APIosFacil.usuario.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record CadastraUsuarioDto(

        @NotBlank(message = "{nome.notblank}")
        @Pattern(regexp = "[a-zA-ZÀ-ÿ]{3,}[a-zA-ZÀ-ÿ ]*", message = "{nome.invalid}")
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
}
