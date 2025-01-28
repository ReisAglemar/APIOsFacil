package com.APIosFacil.usuario.domain.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtualizaUsuarioDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void deveAceitarCamposValidos() {
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto("Aglemar Reis", "teste@gmail.com", "Senha123");

        Set<ConstraintViolation<AtualizaUsuarioDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty(), "Deveria aceitar campos válidos");
    }

    @Test
    public void naoDeveAceitarNomeInvalido() {
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto("re", "teste@gmail.com", "Senha123");

        Set<ConstraintViolation<AtualizaUsuarioDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Deveria rejeitar nome inválido");
    }

    @Test
    public void naoDeveAceitarEmailInvalido() {
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto("Aglemar Reis", "emailinvalido@.com", "Senha123");

        Set<ConstraintViolation<AtualizaUsuarioDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Deveria rejeitar email inválido");
    }

    @Test
    public void naoDeveAceitarSenhaInvalida() {
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto("Aglemar Reis", "teste@gmail.com", "senhaInvalida");

        Set<ConstraintViolation<AtualizaUsuarioDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Deveria rejeitar senha inválida");
    }
}
