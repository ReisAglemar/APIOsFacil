package com.APIosFacil.usuario.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record AtualizaUsuarioDto(

        @Pattern(regexp = "[a-zA-ZÀ-ÿ ]{3,}", message = "{nome.invalid}")
        String nome,

        @Email(message = "{email.invalid}")
        String email,

        @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{7,}", message = "{senha.invalid}")
        String senha) {
}
