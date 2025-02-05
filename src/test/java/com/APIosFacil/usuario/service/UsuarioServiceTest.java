package com.APIosFacil.usuario.service;

import com.APIosFacil.usuario.domain.dto.AtualizaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import com.APIosFacil.usuario.domain.dto.ListaUsuarioDto;
import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import com.APIosFacil.usuario.domain.repository.UsuarioRespository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioServiceTest {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRespository respository;

    @BeforeAll()
    public void setUp() {
        CadastraUsuarioDto cadastraUsuarioDto = new CadastraUsuarioDto("Reis", "35727103088",
                "reis@gmail.com", "reisSenha1234");

        service.cadastraUsuario(cadastraUsuarioDto);
    }

    @Test
    @Transactional
    @DisplayName("Deve cadastrar um usuário")
    public void verificaSeCadastrouUsuario() {
        assertNotNull(respository.getReferenceById(1L), "Não foi possível cadastrar um usuário");
    }

    @Test
    @Transactional
    @DisplayName("Deve detalhar um usuário por id")
    public void deveDetalharUsuarioPorid() {
        UsuarioEntity usuarioEntity = respository.getReferenceById(1L);

        assertEquals("Reis", usuarioEntity.getNome(), "O nome está salvo de forma errada");
        assertEquals("35727103088", usuarioEntity.getCpf(),"O cpf está salvo de forma errada");
        assertEquals("reis@gmail.com", usuarioEntity.getEmail(), "O e-mail está salvo de forma errada");
        assertEquals("reisSenha1234", usuarioEntity.getSenha(), "A senha está salva de forma errada");
        assertEquals(1L, usuarioEntity.getId(), "O id não foi gerado");
        assertTrue(usuarioEntity.isAtivo(), "O usuário não está ativado");
    }

    @Test
    @Transactional
    @DisplayName("Deve apagar usuário, apagar = status desativado")
    public void deveInativarUsuario() {
        UsuarioEntity usuarioEntity = respository.getReferenceById(1l);
        usuarioEntity.inativarUsuario();

        UsuarioEntity usuario = respository.getReferenceById(1l);

        assertEquals(false, usuario.isAtivo(),"O usuário deveria estar desativado");
    }

    @Test
    @Transactional
    @DisplayName("Deve atualizar um usuário")
    public void deveAtualizarUsuario() {
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto("Aglemar Reis", "reisAglemar@gmail.com", "Senha123");

        UsuarioEntity usuarioEntity = respository.getReferenceById(1l);
        usuarioEntity.atualizar(dto);
        UsuarioEntity usuario = respository.getReferenceById(1l);

        assertEquals(dto.nome(),usuario.getNome(), "Nome não foi atualizado");
        assertEquals(dto.email(), usuario.getEmail(), "E-mail não foi atualizado");
        assertEquals(dto.senha(), usuario.getSenha(), "Senha não foi atualizada");
        System.out.println(usuario.isAtivo());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar uma página de usuários")
    public void deveRetornarUsuarios() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<ListaUsuarioDto> PageUsuarios = respository.findAllByAtivoTrue(pageable).map(ListaUsuarioDto::new);

        assertFalse(PageUsuarios.isEmpty(),"A página não deveria estar vazia");
        assertEquals(5, PageUsuarios.getSize(), "O tamanho da página está incorreto");
        assertEquals(0, PageUsuarios.getNumber(), "A página deve iniciar em zero 0");
        assertEquals(1, PageUsuarios.getTotalElements(), "Há mais de um usuário na página");
        assertEquals(1, PageUsuarios.getTotalPages(),"Foi criada mais de uma página");

        List<ListaUsuarioDto> ListUsuarios = PageUsuarios.getContent();

        ListaUsuarioDto usuario = ListUsuarios.get(0);

        assertEquals(1L, usuario.id(), "O id não foi gerado");
        assertEquals("Reis", usuario.nome(), "O nome foi retornado de forma errada");
        assertEquals("reis@gmail.com", usuario.email(), "O e-mail foi retornado de forma errada");
    }
}