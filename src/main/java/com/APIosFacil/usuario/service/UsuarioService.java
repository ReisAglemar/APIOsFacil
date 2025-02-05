package com.APIosFacil.usuario.service;

import com.APIosFacil.usuario.domain.dto.AtualizaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import com.APIosFacil.usuario.domain.dto.DetalhaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.ListaUsuarioDto;
import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import com.APIosFacil.usuario.domain.repository.UsuarioRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRespository repository;

    @Transactional
    public DetalhaUsuarioDto cadastraUsuario(@Valid CadastraUsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioDto);
        repository.save(usuarioEntity);
        return new DetalhaUsuarioDto(usuarioEntity);
    }

    public DetalhaUsuarioDto detalhaUsuarioPorId(Long id) {
        UsuarioEntity usuarioEntity = repository.getReferenceById(id);
        return new DetalhaUsuarioDto(usuarioEntity);
    }

    @Transactional
    public void apagaUsuario(Long id) {
        UsuarioEntity usuarioEntity = repository.getReferenceById(id);
        usuarioEntity.inativarUsuario();
    }

    @Transactional
    public DetalhaUsuarioDto atualizaUsuario(Long id, @Valid AtualizaUsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = repository.getReferenceById(id);
        usuarioEntity.atualizar(usuarioDto);
        return new DetalhaUsuarioDto(usuarioEntity);
    }

    public Page listaUsuario(Pageable pagina) {
        Page<ListaUsuarioDto> usuario = repository.findAllByAtivoTrue(pagina).map(ListaUsuarioDto::new);
        return usuario;
    }
}
