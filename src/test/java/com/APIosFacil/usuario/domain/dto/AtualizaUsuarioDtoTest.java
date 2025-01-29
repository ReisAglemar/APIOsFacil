package com.APIosFacil.usuario.domain.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtualizaUsuarioDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void deveAceitarCamposValidos() {
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto("Aglemar Reis", "reis@gmail.com", "Senha123");
        Set<ConstraintViolation<AtualizaUsuarioDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Deveria aceitar, campos são válidos");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "Re", "re ", " re"})
    public void naoDeveAceitarNomeInvalido(String nomeInvalido) {
        Set<ConstraintViolation<AtualizaUsuarioDto>> violations =
                validator.validate(new AtualizaUsuarioDto(nomeInvalido, "reis@gmail.com", "Senha1234"));

        assertFalse(violations.isEmpty(), "Não deveria aceitar, nome é inválids");
        violations.forEach(violation -> {
            System.out.println("ENTRADA: " + nomeInvalido + " | " + "MENSAGEN: " + violation.getMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "reis@.com", "reisgmail.com", "@gmail.com", " @gmail.com"})
    public void naoDeveAceitarEmaillInvalido(String emailInvalido) {
        Set<ConstraintViolation<AtualizaUsuarioDto>> violations =
                validator.validate(new AtualizaUsuarioDto("Reis", emailInvalido, "Senha1234"));

        assertFalse(violations.isEmpty(), "Não deveria aceitar, email é inválido");
        violations.forEach(violation -> {
            System.out.println("ENTRADA: " + emailInvalido + " | " + "MENSAGEN: " + violation.getMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "semNumero", "1234567", "       4"})
    public void naoDeveAceitarSenhaInvalida(String senhaInvalida) {
        Set<ConstraintViolation<AtualizaUsuarioDto>> violations =
                validator.validate(new AtualizaUsuarioDto("Reis", "reis@gmail.com", senhaInvalida));

        assertFalse(violations.isEmpty(), "Não deveria aceitar, senha é inválida");
        violations.forEach(violation -> {
            System.out.println("ENTRADA: " + senhaInvalida + " | " + "MENSAGEN: " + violation.getMessage());
        });
    }
}
