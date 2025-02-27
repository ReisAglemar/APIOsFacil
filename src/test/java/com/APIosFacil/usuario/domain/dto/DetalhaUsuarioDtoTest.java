package com.APIosFacil.usuario.domain.dto;

import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetalhaUsuarioDtoTest {

    @Test
    @DisplayName("Deve detalhar usuário")
    public void deveCriarDetalhaUsuarioDtoRecebendoUsuarioEntity() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "Aglemar Reis", "35727103088", "Reis@gmail.com", "Senha12345", true);
        DetalhaUsuarioDto usuarioDto = new DetalhaUsuarioDto(usuarioEntity);

        assertEquals(usuarioDto.getClass(), DetalhaUsuarioDto.class,"Não foi convertido de Entity para DTO");
        assertEquals(1L, usuarioDto.id(), "Nome não foi Atribuído Corretamente");
        assertEquals("Aglemar Reis", usuarioDto.nome(), "Nome não foi Atribuído Corretamente");
        assertEquals("35727103088", usuarioDto.cpf(), "CPF não foi Atribuído Corretamente");
        assertEquals("Reis@gmail.com", usuarioDto.email(), "E-mail não foi Atribuído Corretamente");
        assertEquals("Senha12345", usuarioDto.senha(), "Senha não foi Atribuído Corretamente");
        assertTrue(usuarioDto.ativo(), "Usuário não foi Ativado");
    }
}