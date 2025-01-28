package com.APIosFacil.usuario.domain.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CadastraUsuarioDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void deveCadastrarUsuarioValido() {
        CadastraUsuarioDto cadastraUsuarioDto = new CadastraUsuarioDto("Aglemar Reis", "35727103088", "reis@gmail.com", "Senha1234");
        Set<ConstraintViolation<CadastraUsuarioDto>> violations = validator.validate(cadastraUsuarioDto);
        Assertions.assertTrue(violations.isEmpty(), "Deveria aceitar, campos são válidos!");
    }

    @Test
    public void naoDeveCadastrarUsuarioNomeInvalido() {
        List<String> nomesInvalidos = new ArrayList<>();
        nomesInvalidos.add("");
        nomesInvalidos.add(" ");
        nomesInvalidos.add("  ");
        nomesInvalidos.add("Re");
        nomesInvalidos.add("Re ");
        nomesInvalidos.add(null);

        nomesInvalidos.forEach(nomeInvalido -> {
            Set<ConstraintViolation<CadastraUsuarioDto>> violations =
                    validator.validate(new CadastraUsuarioDto(nomeInvalido, "35727103088", "reis@gmail.com", "Senha1234"));

            assertFalse(violations.isEmpty(), "Não deveria aceitar, nome é inválids");
            violations.forEach(violation -> {
                System.out.println("ENTRADA: " + nomeInvalido + " | " + "MENSAGEN: " + violation.getMessage());
            });
        });
    }

    @Test
    public void naoDeveCadastrarUsuarioCpflInvalido() {
        List<String> cpfInvalidos = new ArrayList<>();
        cpfInvalidos.add("");
        cpfInvalidos.add(" ");
        cpfInvalidos.add("  ");
        cpfInvalidos.add("35727103080");
        cpfInvalidos.add("12345678901");
        cpfInvalidos.add("11111111111");
        cpfInvalidos.add(null);

        cpfInvalidos.forEach(cpfInvalido -> {
            Set<ConstraintViolation<CadastraUsuarioDto>> violations =
                    validator.validate(new CadastraUsuarioDto("Reis", cpfInvalido, "reis@gmail.com", "Senha1234"));

            assertFalse(violations.isEmpty(), "Não deveria aceitar, cpf é inválido");
            violations.forEach(violation -> {
                System.out.println("ENTRADA: " + cpfInvalido + " | " + "MENSAGEN: " + violation.getMessage());
            });
        });
    }

    @Test
    public void naoDeveCadastrarUsuarioEmaillInvalido() {
        List<String> emailInvalidos = new ArrayList<>();
        emailInvalidos.add("");
        emailInvalidos.add(" ");
        emailInvalidos.add("  ");
        emailInvalidos.add("reis@.com");
        emailInvalidos.add("reisgmail.com");
        emailInvalidos.add("@gmail.com");
        emailInvalidos.add(" @gmail.com");
        emailInvalidos.add(null);

        emailInvalidos.forEach(emailInvalido -> {
            Set<ConstraintViolation<CadastraUsuarioDto>> violations =
                    validator.validate(new CadastraUsuarioDto("Reis", "35727103088", emailInvalido, "Senha1234"));

            assertFalse(violations.isEmpty(), "Não deveria aceitar, cpf é inválido");
            violations.forEach(violation -> {
                System.out.println("ENTRADA: " + emailInvalido + " | " + "MENSAGEN: " + violation.getMessage());
            });
        });
    }

    @Test
    public void naoDeveCadastrarUsuarioSenhalInvalida() {
        List<String> senhaInvalidas = new ArrayList<>();
        senhaInvalidas.add("");
        senhaInvalidas.add(" ");
        senhaInvalidas.add("  ");
        senhaInvalidas.add("semNumero");
        senhaInvalidas.add("1234567");
        senhaInvalidas.add("       4");
        senhaInvalidas.add(null);

        senhaInvalidas.forEach(senhaInvalida -> {
            Set<ConstraintViolation<CadastraUsuarioDto>> violations =
                    validator.validate(new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", senhaInvalida));

            assertFalse(violations.isEmpty(), "Não deveria aceitar, cpf é inválido");
            violations.forEach(violation -> {
                System.out.println("ENTRADA: " + senhaInvalida + " | " + "MENSAGEN: " + violation.getMessage());
            });
        });
    }

}