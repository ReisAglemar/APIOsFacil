package com.APIosFacil.usuario.service;

import com.APIosFacil.usuario.domain.dto.AtualizaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import com.APIosFacil.usuario.domain.dto.DetalhaUsuarioDto;
import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import com.APIosFacil.usuario.domain.repository.UsuarioRespository;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        CadastraUsuarioDto cadastraUsuarioDto = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "reisSenha1234");

        service.cadastraUsuario(cadastraUsuarioDto);
    }

    @Test
    @Transactional
    @DisplayName("Deve haver um usuário cadastrado")
    public void verificaSeCadastrouUsuario() {
        assertNotNull(respository.getReferenceById(1L), "Não foi possivel cadastrar um usuario");
    }

    @Test
    @Transactional
    @DisplayName("Deve detalhar usuário o usuário cadastrado")
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
    @DisplayName("Deve inativar usuário")
    public void deveInativarUsuario() {
        UsuarioEntity usuarioEntity = respository.getReferenceById(1l);
        usuarioEntity.inativarUsuario();

        UsuarioEntity usuario = respository.getReferenceById(1l);

        assertEquals(false, usuario.isAtivo(),"O usuário deveria estar desativado");
    }

//    @Transactional
//    public DetalhaUsuarioDto atualizaUsuario(Long id, @Valid AtualizaUsuarioDto usuarioDto) {
//        UsuarioEntity usuarioEntity = repository.getReferenceById(id);
//        usuarioEntity.atualizar(usuarioDto);
//        return new DetalhaUsuarioDto(usuarioEntity);
//    }
}