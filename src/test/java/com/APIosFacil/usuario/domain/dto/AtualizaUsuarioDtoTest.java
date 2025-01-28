package com.APIosFacil.usuario.domain.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void naoDeveAceitarNomeInvalido() {
        List<String> nomesInvalidos = new ArrayList<>();
        nomesInvalidos.add("");
        nomesInvalidos.add(" ");
        nomesInvalidos.add("  ");
        nomesInvalidos.add("Re");
        nomesInvalidos.add("Re ");

        nomesInvalidos.forEach(nomeInvalido -> {
            Set<ConstraintViolation<AtualizaUsuarioDto>> violations =
                    validator.validate(new AtualizaUsuarioDto(nomeInvalido, "reis@gmail.com", "Senha1234"));

            assertFalse(violations.isEmpty(), "Não deveria aceitar, nome é inválids");
            violations.forEach(violation -> {
                System.out.println("ENTRADA: " + nomeInvalido + " | " + "MENSAGEN: " + violation.getMessage());
            });
        });
    }

    @Test
    public void naoDeveAceitarEmaillInvalido() {
        List<String> emailInvalidos = new ArrayList<>();
        emailInvalidos.add("");
        emailInvalidos.add(" ");
        emailInvalidos.add("  ");
        emailInvalidos.add("reis@.com");
        emailInvalidos.add("reisgmail.com");
        emailInvalidos.add("@gmail.com");
        emailInvalidos.add(" @gmail.com");

        emailInvalidos.forEach(emailInvalido -> {
            Set<ConstraintViolation<AtualizaUsuarioDto>> violations =
                    validator.validate(new AtualizaUsuarioDto("Reis", emailInvalido, "Senha1234"));

            assertFalse(violations.isEmpty(), "Não deveria aceitar, email é inválido");
            violations.forEach(violation -> {
                System.out.println("ENTRADA: " + emailInvalido + " | " + "MENSAGEN: " + violation.getMessage());
            });
        });
    }

    @Test
    public void naoDeveAceitarSenhaInvalida() {
        List<String> senhaInvalidas = new ArrayList<>();
        senhaInvalidas.add("");
        senhaInvalidas.add(" ");
        senhaInvalidas.add("  ");
        senhaInvalidas.add("semNumero");
        senhaInvalidas.add("1234567");
        senhaInvalidas.add("       4");

        senhaInvalidas.forEach(senhaInvalida -> {
            Set<ConstraintViolation<AtualizaUsuarioDto>> violations =
                    validator.validate(new AtualizaUsuarioDto("Reis", "reis@gmail.com", senhaInvalida));

            assertFalse(violations.isEmpty(), "Não deveria aceitar, senha é inválida");
            violations.forEach(violation -> {
                System.out.println("ENTRADA: " + senhaInvalida + " | " + "MENSAGEN: " + violation.getMessage());
            });
        });
    }
}
