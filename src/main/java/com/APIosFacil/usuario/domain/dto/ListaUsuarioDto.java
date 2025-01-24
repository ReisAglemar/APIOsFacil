package com.APIosFacil.usuario.domain.dto;

import com.APIosFacil.usuario.domain.model.UsuarioEntity;

public record ListaUsuarioDto(

        Long id,
        String nome,
        String email) {



    public ListaUsuarioDto(UsuarioEntity usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
