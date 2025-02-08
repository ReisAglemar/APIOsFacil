package com.APIosFacil.usuario.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestControllerAdvice
public class TratamentoDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity dadoNaoEncontradoStatus404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> InternalServerError(HttpServerErrorException.InternalServerError ex) {
        String message = "Erro interno no servidor. Tente novamente mais tarde.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity dadoInvalidoStatus400(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(tratamentoStatus400::new).toList());
    }

    private record tratamentoStatus400(String campo, String mensagem) {
        public tratamentoStatus400(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflitoDadoStatus409(DataIntegrityViolationException ex) {
        String rootCauseMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        String campo;
        String mensagem;

        if (rootCauseMessage.contains("USUARIOS(CPF")) {
            campo = "cpf";
            mensagem = "CPF já cadastrado no sistema.";
        } else if (rootCauseMessage.contains("USUARIOS(EMAIL")) {
            campo = "email";
            mensagem = "E-mail já cadastrado no sistema.";
        } else {
            campo = "desconhecido";
            mensagem = "Erro de integridade de dados. Verifique as informações fornecidas.";
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new TratamentoStatus409(campo, mensagem));
    }

    private record TratamentoStatus409(String campo, String mensagem) {
    }
}
