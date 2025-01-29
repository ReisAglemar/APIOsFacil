package com.APIosFacil.usuario.domain.dto;

import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListaUsuarioDtoTest {

    @Test
    public void deveCriarListaUsuarioDtoRecebendoUsuarioEntity() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "Aglemar Reis", "35727103088", "Reis@gmail.com", "Senha12345", true);
        ListaUsuarioDto usuarioDto = new ListaUsuarioDto(usuarioEntity);

        assertEquals(1L, usuarioDto.id(), "Nome não foi Atribuído Corretamente");
        assertEquals("Aglemar Reis", usuarioDto.nome(), "Nome não foi Atribuído Corretamente");
        assertEquals("Reis@gmail.com", usuarioDto.email(), "E-mail não foi Atribuído Corretamente");
    }
}