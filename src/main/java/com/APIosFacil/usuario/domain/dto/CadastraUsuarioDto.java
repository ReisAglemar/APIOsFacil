package com.APIosFacil.usuario.domain.dto;

import com.APIosFacil.usuario.domain.model.UsuarioEntity;

public record CadastraUsuarioDto(

        String nome,
        String cpf,
        String email,
        String senha) {

    public CadastraUsuarioDto(UsuarioEntity usuario) {
        this(
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha());
    }
}
