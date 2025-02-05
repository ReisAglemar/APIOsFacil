package com.APIosFacil.usuario.domain.repository;

import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import com.APIosFacil.usuario.domain.model.UsuarioEntity;
import com.APIosFacil.usuario.service.UsuarioService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRespositoryTest {

    @Autowired
    private UsuarioRespository respository;

    @Autowired
    private UsuarioService service;

    @BeforeAll()
    public void setUp() {
        CadastraUsuarioDto[] usuarios = {
            new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "reisSenha1234"),
            new CadastraUsuarioDto("Maria Silva", "12345678909", "maria@gmail.com", "mariaSenha123"),
            new CadastraUsuarioDto("João Souza", "98765432100", "joao@gmail.com", "joaoSenha123"),
            new CadastraUsuarioDto("Carlos Oliveira", "45678912333", "carlos@gmail.com", "carlosSenha123"),
            new CadastraUsuarioDto("Ana Costa", "78912345622", "ana@gmail.com", "anaSenha123"),
            new CadastraUsuarioDto("Fernanda Lima", "32165498744", "fernanda@gmail.com", "fernandaSenha123")
        };

        for (CadastraUsuarioDto usuario : usuarios){
            service.cadastraUsuario(usuario);
        }

        service.apagaUsuario(2L);
        service.apagaUsuario(3L);
        service.apagaUsuario(4L);
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar apenas os usuários com ATIVO = TRUE.")
    public void findAllByAtivoTrue() {
        Pageable page = PageRequest.of(0, 10);
        Page<UsuarioEntity> pageUsuario = respository.findAllByAtivoTrue(page);

        assertEquals(3, pageUsuario.getTotalElements(),
                "O número de usuários ativos deveria ser 3, mas foi encontrado: " + pageUsuario.getTotalElements());

        pageUsuario.forEach(usuario -> {
            assertTrue(usuario.isAtivo());
            System.out.println("ID: " + usuario.getId() + " | Ativo: " + usuario.isAtivo());
        });
    }
}