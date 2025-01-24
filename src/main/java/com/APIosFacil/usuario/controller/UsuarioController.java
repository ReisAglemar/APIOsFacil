package com.APIosFacil.usuario.controller;

import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import com.APIosFacil.usuario.domain.dto.DetalhaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.ListaUsuarioDto;
import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import com.APIosFacil.usuario.domain.repository.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRespository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastraUsuario(@RequestBody CadastraUsuarioDto usuarioDto, UriComponentsBuilder uriComponentsBuilder) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioDto);
        repository.save(usuarioEntity);
        URI uri = uriComponentsBuilder.path("/usuario/detalhar/{id}").buildAndExpand(usuarioEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhaUsuarioDto(usuarioEntity));
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity detalhaUsuarioPorId(@PathVariable Long id) {
        UsuarioEntity usuarioEntity = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhaUsuarioDto(usuarioEntity));
    }

    @DeleteMapping("/apagar/{id}")
    @Transactional
    public ResponseEntity apagaUsuario(@PathVariable Long id) {
        UsuarioEntity usuarioEntity = repository.getReferenceById(id);
        usuarioEntity.inativarUsuario();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ListaUsuarioDto>> listaUsuario(@PageableDefault(size = 5, sort = {"id"}) Pageable pagina) {
        Page usuarios = repository.findAllByAtivoTrue(pagina).map(ListaUsuarioDto::new);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity atualizaUsuario(@PathVariable Long id, @RequestBody CadastraUsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = repository.getReferenceById(id);
        usuarioEntity.atualizar(usuarioDto);
        return ResponseEntity.ok(new DetalhaUsuarioDto(usuarioEntity));
    }
}
