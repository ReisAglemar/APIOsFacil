package com.APIosFacil.usuario.domain.dto;

import com.APIosFacil.usuario.domain.model.UsuarioEntity;

public record DetalhaUsuarioDto(

        Long id,
        String nome,
        String cpf,
        String email,
        String senha,
        boolean ativo) {

    public DetalhaUsuarioDto(UsuarioEntity usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.isAtivo());
    }

}
