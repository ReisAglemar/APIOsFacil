package com.APIosFacil.usuario.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record AtualizaUsuarioDto(

        @Pattern(regexp = "[a-zA-ZÀ-ÿ]{3,}[a-zA-ZÀ-ÿ ]*", message = "{nome.invalid}")
        String nome,

        @Email(message = "{email.invalid}")
        @Pattern(regexp = "(?!\\s).+", message = "{email.notblank}")
        String email,

        @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{7,}", message = "{senha.invalid}")
        String senha) {
}
