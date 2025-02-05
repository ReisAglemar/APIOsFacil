package com.APIosFacil.usuario.domain.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CadastraUsuarioDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Deve aceitar dados válidos")
    public void deveCadastrarUsuarioValido() {
        CadastraUsuarioDto cadastraUsuarioDto = new CadastraUsuarioDto("Aglemar Reis", "35727103088", "reis@gmail.com", "Senha1234");
        Set<ConstraintViolation<CadastraUsuarioDto>> violations = validator.validate(cadastraUsuarioDto);
        Assertions.assertTrue(violations.isEmpty(), "Deveria aceitar, campos são válidos!");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "Re", "re ", " re"})
    @DisplayName("Não deve aceitar, nome inválido")
    public void naoDeveCadastrarUsuarioNomeInvalido(String nomeInvalido) {
        Set<ConstraintViolation<CadastraUsuarioDto>> violations =
                validator.validate(new CadastraUsuarioDto(nomeInvalido, "35727103088", "reis@gmail.com", "Senha1234"));

        assertFalse(violations.isEmpty(), "Não deveria aceitar, nome é inválids");
        violations.forEach(violation -> {
            System.out.println("ENTRADA: " + nomeInvalido + " | " + "MENSAGEN: " + violation.getMessage());
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "35727103080", "12345678901", "11111111111"})
    @DisplayName("Não deve aceitar, cpf inválido")
    public void naoDeveCadastrarUsuarioCpflInvalido(String cpfInvalido) {
        Set<ConstraintViolation<CadastraUsuarioDto>> violations =
                validator.validate(new CadastraUsuarioDto("Reis", cpfInvalido, "reis@gmail.com", "Senha1234"));

        assertFalse(violations.isEmpty(), "Não deveria aceitar, cpf é inválido");
        violations.forEach(violation -> {
            System.out.println("ENTRADA: " + cpfInvalido + " | " + "MENSAGEN: " + violation.getMessage());
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "reis@.com", "reisgmail.com", "@gmail.com", " @gmail.com"})
    @DisplayName("Não deve aceitar, e-mail inválido")
    public void naoDeveCadastrarUsuarioEmaillInvalido(String emailInvalido) {
        Set<ConstraintViolation<CadastraUsuarioDto>> violations =
                validator.validate(new CadastraUsuarioDto("Reis", "35727103088", emailInvalido, "Senha1234"));

        assertFalse(violations.isEmpty(), "Não deveria aceitar, cpf é inválido");
        violations.forEach(violation -> {
            System.out.println("ENTRADA: " + emailInvalido + " | " + "MENSAGEN: " + violation.getMessage());
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "semNumero", "1234567", "       4"})
    @DisplayName("Não deve aceitar, senha inválida")
    public void naoDeveCadastrarUsuarioSenhalInvalida(String senhaInvalida) {

        Set<ConstraintViolation<CadastraUsuarioDto>> violations =
                validator.validate(new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", senhaInvalida));

        assertFalse(violations.isEmpty(), "Não deveria aceitar, cpf é inválido");
        violations.forEach(violation -> {
            System.out.println("ENTRADA: " + senhaInvalida + " | " + "MENSAGEN: " + violation.getMessage());
        });
    }
}