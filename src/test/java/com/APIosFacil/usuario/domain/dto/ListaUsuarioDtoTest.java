package com.APIosFacil.usuario.domain.dto;

import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ListaUsuarioDtoTest {

    @Test
    @DisplayName("Deve listar usuaro")
    public void deveCriarListaUsuarioDtoRecebendoUsuarioEntity() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "Aglemar Reis", "35727103088", "Reis@gmail.com", "Senha12345", true);
        ListaUsuarioDto usuarioDto = new ListaUsuarioDto(usuarioEntity);

        assertEquals(usuarioDto.getClass(), ListaUsuarioDto.class, "Não foi convertido de Entity para DTO");
        assertEquals(1L, usuarioDto.id(), "Nome não foi Atribuído Corretamente");
        assertEquals("Aglemar Reis", usuarioDto.nome(), "Nome não foi Atribuído Corretamente");
        assertEquals("Reis@gmail.com", usuarioDto.email(), "E-mail não foi Atribuído Corretamente");
    }
}