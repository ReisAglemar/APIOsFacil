package com.APIosFacil.usuario.domain.model;

import com.APIosFacil.usuario.domain.dto.AtualizaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {

    @Test
    public void deveCriarUsuarioRecebendoDto() {
        CadastraUsuarioDto usuarioDto = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);

        assertEquals("Reis", usuario.getNome(), "Nome não foi Atribuído Corretamente");
        assertEquals("35727103088", usuario.getCpf(), "CPF não foi Atribuído Corretamente");
        assertEquals("reis@gmail.com", usuario.getEmail(), "E-mail não foi Atribuído Corretamente");
        assertEquals("ReisSenha3456", usuario.getSenha(), "Senha não foi Atribuído Corretamente");
        assertTrue(usuario.isAtivo(), "Usuário não foi Ativado");
    }

    @Test
    public void deveAtualizarUsuarioRecebendoDto() {
        CadastraUsuarioDto usuarioDto = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);
        AtualizaUsuarioDto atualizaUsuarioDto = new AtualizaUsuarioDto("Atualiza Reis", "AtualizaReis@gmail.com", "AtualizaReisSenha3456");
        usuario.atualizar(atualizaUsuarioDto);

        assertEquals("Atualiza Reis", usuario.getNome(), "Nome Deveria Ter Sido Atualizado");
        assertEquals("AtualizaReis@gmail.com", usuario.getEmail(), "E-mail Deveria Ter Sido Atualizado");
        assertEquals("AtualizaReisSenha3456", usuario.getSenha(), "Senha Deveria Ter Sido Atualizado");
    }

    @Test
    public void naoDeveAtualizarUsuarioRecebendoDto() {
        CadastraUsuarioDto usuarioDto = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);
        List<AtualizaUsuarioDto> possibilidades = preparaPossibilidades();
        realizaTeste(possibilidades, usuario);
    }

    private List<AtualizaUsuarioDto> preparaPossibilidades(){
        //se necessário, adicione novas possibilidades no array entradasPossiveis
        String[] entradasPossiveis = new String[]{"", " ", null};
        List<AtualizaUsuarioDto> possibilidades = Arrays.stream(entradasPossiveis)
                .flatMap(nome -> Arrays.stream(entradasPossiveis)
                        .flatMap(email -> Arrays.stream(entradasPossiveis)
                                .map(senha -> new AtualizaUsuarioDto(nome, email, senha))))
                .toList();
        return possibilidades;
    }

    private void realizaTeste(List<AtualizaUsuarioDto> possibilidades, UsuarioEntity usuario){
        possibilidades.forEach(dto -> {
            usuario.atualizar(dto);

            assertEquals("Reis", usuario.getNome(), "Nome Não Deveria Ter Sido Atualizado");
            assertEquals("reis@gmail.com", usuario.getEmail(), "E-mail Não Deveria Ter Sido Atualizado");
            assertEquals("ReisSenha3456", usuario.getSenha(), "Senha Não Deveria Ter Sido Atualizado");
        });
    }

    @Test
    public void deveConsiderarUsariosIguaisSeIdCpfEmailForemIguais() {
        CadastraUsuarioDto usuarioDto1 = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario1 = new UsuarioEntity(usuarioDto1);
        CadastraUsuarioDto usuarioDto2 = new CadastraUsuarioDto("Reis", "35727103088", "reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario2 = new UsuarioEntity(usuarioDto2);

        assertEquals(usuario1, usuario2);
    }

    @Test
    public void naoDeveConsiderarUsariosIguaisSeIdCpfEmailForemDiferentes() {
        CadastraUsuarioDto usuarioDto1 = new CadastraUsuarioDto("Reis", "35727103088", "Reis@gmail.com", "ReisSenha3456");
        UsuarioEntity usuario1 = new UsuarioEntity(usuarioDto1);
        CadastraUsuarioDto usuarioDto2 = new CadastraUsuarioDto("Reis2", "32707079081", "Reis2@gmail.com", "Reis2Senha3456");
        UsuarioEntity usuario2 = new UsuarioEntity(usuarioDto2);

        assertNotEquals(usuario1, usuario2);
    }

    @Test
    public void deveInativarUsuario() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.inativarUsuario();

        assertFalse(usuario.isAtivo());
    }
}