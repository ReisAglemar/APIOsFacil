package com.APIosFacil.usuario.controller;

import com.APIosFacil.usuario.domain.dto.AtualizaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import com.APIosFacil.usuario.domain.dto.DetalhaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.ListaUsuarioDto;
import com.APIosFacil.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;



    @Operation(
            summary = "Cadastro de Usuário",
            description = "Endpoint para cadastrar um novo usuário no sistema. O corpo da requisição deve seguir o formato especificado no CadastraUsuarioDto."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário cadastrado com sucesso. Retorna o código 201, o header 'Location' com o link para detalhes do usuário, e o corpo com os dados do usuário cadastrado.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Resposta",
                                    value = "{ \"id\": 1, \"nome\": \"João da Silva\", \"cpf\": \"123.456.789-00\", \"email\": \"joao.silva@email.com\" }"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida. O retorno inclui detalhes sobre quais campos não passaram nas validações.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Erro",
                                    value = "{ \"erro\": \"CPF inválido\", \"codigo\": 400 }"
                            )
                    )
            )
    })
    @PostMapping("/cadastrar")
    public ResponseEntity cadastraUsuario(@RequestBody @Valid CadastraUsuarioDto usuarioDto,
                                          UriComponentsBuilder uriComponentsBuilder) {
        DetalhaUsuarioDto detalhaUsuarioDto = service.cadastraUsuario(usuarioDto);
        URI uri = uriComponentsBuilder.path("/usuario/detalhar/{id}").buildAndExpand(detalhaUsuarioDto.id()).toUri();
        //201 + location + body
        return ResponseEntity.created(uri).body(detalhaUsuarioDto);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity detalhaUsuarioPorId(@PathVariable Long id) {
        DetalhaUsuarioDto detalhaUsuarioDto = service.detalhaUsuarioPorId(id);
        //200 + body
        return ResponseEntity.ok(detalhaUsuarioDto);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity apagaUsuario(@PathVariable Long id) {
        service.apagaUsuario(id);
        //204
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ListaUsuarioDto>> listaUsuario(@PageableDefault(size = 5, sort = {"id"}) Pageable pagina) {
        Page usuarios = service.listaUsuario(pagina);
        //200 + body
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizaUsuario(@PathVariable Long id, @RequestBody @Valid AtualizaUsuarioDto usuarioDto) {
        DetalhaUsuarioDto detalhaUsuarioDto = service.atualizaUsuario(id, usuarioDto);
        //200 + body
        return ResponseEntity.ok(detalhaUsuarioDto);
    }
}
