package com.APIosFacil.usuario.controller;

import com.APIosFacil.usuario.domain.dto.AtualizaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import com.APIosFacil.usuario.domain.dto.DetalhaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.ListaUsuarioDto;
import com.APIosFacil.usuario.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    UsuarioController controller;

    @Mock
    UsuarioService service;

    @Test
    @DisplayName("Ao cadastrar usuário deve retornar 201, location e body")
    public void deveRetornarStatus201CadastroUsuario() {
        CadastraUsuarioDto cadastraUsuarioDto = new CadastraUsuarioDto("Reis", "35727103088",
                "reis@gmail.com", "ReisSenha3456");

        DetalhaUsuarioDto detalhaUsuarioDto = new DetalhaUsuarioDto(1L, "Reis", "35727103088",
                "reis@gmail.com", "ReisSenha3456", true);

        when(service.cadastraUsuario(cadastraUsuarioDto)).thenReturn(detalhaUsuarioDto);
        ResponseEntity<?> response = controller.cadastraUsuario(cadastraUsuarioDto, UriComponentsBuilder.newInstance());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(detalhaUsuarioDto, response.getBody());
        assertEquals("/usuario/detalhar/1", response.getHeaders().getLocation().getPath());
    }

    @Test
    @DisplayName("Ao detalhar usuário deve retornar 200 e body")
    public void deveRetornarStatus200DetalharUsuario() {
        DetalhaUsuarioDto detalhaUsuarioDto = new DetalhaUsuarioDto(1L, "Reis", "35727103088",
                "reis@gmail.com", "ReisSenha3456", true);

        when(service.detalhaUsuarioPorId(1L)).thenReturn(detalhaUsuarioDto);
        ResponseEntity<?> response = controller.detalhaUsuarioPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(detalhaUsuarioDto, response.getBody());
    }

    @Test
    @DisplayName("Ao apagar usuário deve retornar 204")
    public void deveRetornarStatus204ApagarUsuario() {
        doNothing().when(service).apagaUsuario(1L);
        ResponseEntity<?> response = controller.apagaUsuario(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Ao listar usuários deve retornar 200 e uma lista")
    public void deveRetornarStatus200ListarUsuario() {
        ListaUsuarioDto listaUsuarioDto = new ListaUsuarioDto(1L, "Reis","Reis@gmail.com");
        Page<ListaUsuarioDto> page = new PageImpl<>(List.of(listaUsuarioDto));

        when(service.listaUsuario(Pageable.unpaged())).thenReturn(page);
        ResponseEntity<?> response = controller.listaUsuario(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }

    @Test
    @DisplayName("Ao atualizar usuário deve retornar 200 e body")
    public void deveRetornarStatus200AtualizarUsuario() {
        AtualizaUsuarioDto atualizaUsuarioDto = new AtualizaUsuarioDto("Reis", "reis@gmail.com", "ReisSenha3456");

        DetalhaUsuarioDto detalhaUsuarioDto = new DetalhaUsuarioDto(1L, "Reis", "35727103088",
                "reis@gmail.com", "ReisSenha3456", true);

        when(service.atualizaUsuario(1L, atualizaUsuarioDto)).thenReturn(detalhaUsuarioDto);
        ResponseEntity<?> response = controller.atualizaUsuario(1L, atualizaUsuarioDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(detalhaUsuarioDto, response.getBody());
    }
}