package com.APIosFacil.usuario.domain.model;

import com.APIosFacil.usuario.domain.dto.AtualizaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {

    @Test
    @DisplayName("Deve criar um usuário entity recebendo um DTO")
    public void deveCriarUsuarioRecebendoDto() {
        CadastraUsuarioDto usuarioDto = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);

        assertEquals(usuario.getClass(), UsuarioEntity.class, "Erro ao converter para entity");
        assertEquals("Reis", usuario.getNome(), "Nome não foi Atribuído Corretamente");
        assertEquals("35727103088", usuario.getCpf(), "CPF não foi Atribuído Corretamente");
        assertEquals("reis@gmail.com", usuario.getEmail(), "E-mail não foi Atribuído Corretamente");
        assertEquals("ReisSenha3456", usuario.getSenha(), "Senha não foi Atribuído Corretamente");
        assertTrue(usuario.isAtivo(), "Usuário não foi Ativado");
    }

    @Test
    @DisplayName("Deve atualizar usuário recebendo um DTO")
    public void deveAtualizarUsuarioRecebendoDto() {
        CadastraUsuarioDto usuarioDto = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);
        AtualizaUsuarioDto atualizaUsuarioDto = new AtualizaUsuarioDto("Atualiza Reis", "AtualizaReis@gmail.com", "AtualizaReisSenha3456");

        usuario.atualizar(atualizaUsuarioDto);

        assertEquals("Atualiza Reis", usuario.getNome(), "Nome Deveria Ter Sido Atualizado");
        assertEquals("AtualizaReis@gmail.com", usuario.getEmail(), "E-mail Deveria Ter Sido Atualizado");
        assertEquals("AtualizaReisSenha3456", usuario.getSenha(), "Senha Deveria Ter Sido Atualizado");
    }

    @ParameterizedTest
    @MethodSource("preparaPossibilidades")
    @DisplayName("Não deve atualizar, massa de dados é inválida")
    void naoDeveAtualizarUsuarioRecebendoDto(AtualizaUsuarioDto dto) {
        CadastraUsuarioDto usuarioDto = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);
        usuario.atualizar(dto);

        assertEquals("Reis", usuario.getNome(), "Nome Não Deveria Ter Sido Atualizado");
        assertEquals("reis@gmail.com", usuario.getEmail(), "E-mail Não Deveria Ter Sido Atualizado");
        assertEquals("ReisSenha3456", usuario.getSenha(), "Senha Não Deveria Ter Sido Atualizado");
    }

    private static Stream<AtualizaUsuarioDto> preparaPossibilidades() {
        String[] entradasPossiveis = {"", " ", null};

        return Stream.of(entradasPossiveis)
                .flatMap(nome -> Stream.of(entradasPossiveis)
                        .flatMap(email -> Stream.of(entradasPossiveis)
                                .map(senha -> new AtualizaUsuarioDto(nome, email, senha))));
    }

    @Test
    @DisplayName("Deve considerar usuário iguais pelo cpf e e-mail")
    public void deveConsiderarUsariosIguaisSeIdCpfEmailForemIguais() {
        CadastraUsuarioDto usuarioDto1 = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario1 = new UsuarioEntity(usuarioDto1);
        CadastraUsuarioDto usuarioDto2 = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario2 = new UsuarioEntity(usuarioDto2);

        assertEquals(usuario1, usuario2);
    }

    @Test
    @DisplayName("Não deve considerar usuário iguais pelo cpf e e-mail")
    public void naoDeveConsiderarUsariosIguaisSeIdCpfEmailForemDiferentes() {
        CadastraUsuarioDto usuarioDto1 = new CadastraUsuarioDto("Reis", "35727103088", "Reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario1 = new UsuarioEntity(usuarioDto1);
        CadastraUsuarioDto usuarioDto2 = new CadastraUsuarioDto("Reis2", "32707079081", "Reis2@gmail.com", "Reis2Senha3456");
        UsuarioEntity usuario2 = new UsuarioEntity(usuarioDto2);

        assertNotEquals(usuario1, usuario2);
    }

    @Test
    @DisplayName("Deve inativar usuário")
    public void deveInativarUsuario() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.inativarUsuario();

        assertFalse(usuario.isAtivo());
    }
}