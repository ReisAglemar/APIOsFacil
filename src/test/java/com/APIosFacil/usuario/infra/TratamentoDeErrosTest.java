package com.APIosFacil.usuario.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TratamentoDeErrosTest {

    @InjectMocks
    private TratamentoDeErros tratamentoDeErros;


    @Test
    @DisplayName("Deve retornar status 404")
    public void deveRetornarErro404() {
        ResponseEntity<?> response = tratamentoDeErros.dadoNaoEncontradoStatus404();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Deveria retornar 404");
    }

    @Test
    @DisplayName("Deve retornar status 400 mais lista de erros")
    public void deveRetornarErro400() {
        BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "usuarioDto");
        bindingResult.addError(new FieldError("usuarioDto", "email", "{email.invalid}"));
        bindingResult.addError(new FieldError("usuarioDto", "senha", "{senha.invalid}"));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<?> response = tratamentoDeErros.dadoInvalidoStatus400(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Deveria retornar 400");

        List<?> erros = (List<?>) response.getBody();
        assertEquals(2, erros.size(), "Deveria retornar 2 erros");
    }
}