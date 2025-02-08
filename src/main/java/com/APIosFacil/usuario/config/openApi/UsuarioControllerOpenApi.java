package com.APIosFacil.usuario.config.openApi;

import com.APIosFacil.usuario.domain.dto.AtualizaUsuarioDto;
import com.APIosFacil.usuario.domain.dto.CadastraUsuarioDto;
import com.APIosFacil.usuario.domain.dto.ListaUsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Api Usuário")
public interface UsuarioControllerOpenApi {

    @Operation(
            summary = "Cadastra Usuário",
            description = "Endpoint para cadastrar um novo usuário no sistema. O corpo da requisição deve seguir o formato especificado no CadastraUsuarioDto.",
            parameters = {
                    @Parameter(name = "nome",
                            description = "Nome do usuário",
                            required = true,
                            in = ParameterIn.QUERY,
                            example = "João da Silva"),
                    @Parameter(name = "cpf",
                            description = "CPF do usuário",
                            required = true, in =
                            ParameterIn.QUERY,
                            example = "357.271.030-88"),
                    @Parameter(name = "email",
                            description = "E-mail do usuário",
                            required = true,
                            in = ParameterIn.QUERY,
                            example = "joao.silva@email.com"),
                    @Parameter(name = "senha",
                            description = "Senha do usuário",
                            required = true,
                            in = ParameterIn.QUERY,
                            example = "SenhaJoao137")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Cadastro de usuário",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "json Válido",
                                    value = "{ " +
                                            "\"nome\": \"João da Silva\", " +
                                            "\"cpf\": \"357.271.030-88\", " +
                                            "\"email\": \"joao.silva@email.com\", " +
                                            "\"senha\": \"SenhaJoao137\" " +
                                            "}"
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cadastra um CadastraUsuarioDto. Retorna response body (DetalharUsuarioDto) e response headers ",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Cadastro realizado",
                                    value = "{ " +
                                            "\"id\": 1, " +
                                            "\"nome\": \"João da Silva\", " +
                                            "\"cpf\": \"357.271.030-88\", " +
                                            "\"email\": \"joao.silva@email.com\" " +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida. O retorno inclui detalhes sobre quais campos não passaram nas validações.",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Nome inválido",
                                            value = "[\n" +
                                                    "\t{\n" +
                                                    "\t\t\"campo\": \"nome\",\n" +
                                                    "\t\t\"mensagem\": \"O nome deve conter no mínimo 3 letras e não pode ter números ou caracteres especiais.\"\n" +
                                                    "\t}\n" +
                                                    "]"
                                    ),
                                    @ExampleObject(
                                            name = "Cpf inválido",
                                            value = "[\n" +
                                                    "\t{\n" +
                                                    "\t\t\"campo\": \"cpf\",\n" +
                                                    "\t\t\"mensagem\": \"O CPF é inválido, informe um CPF válido.\"\n" +
                                                    "\t}\n" +
                                                    "]"
                                    ),
                                    @ExampleObject(
                                            name = "Senha inválida",
                                            value = "[\n" +
                                                    "\t{\n" +
                                                    "\t\t\"campo\": \"senha\",\n" +
                                                    "\t\t\"mensagem\": \"A senha deve conter no mínimo 7 caracteres, com ao menos um número.\"\n" +
                                                    "\t}\n" +
                                                    "]"
                                    )
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Requisição inválida. O retorno inclui detalhes sobres quais campos ocorrem conflitos",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "CPF duplicado",
                                            value = "{\n" +
                                                    "\t\"campo\": \"cpf\",\n" +
                                                    "\t\"mensagem\": \"CPF já cadastrado no sistema.\"\n" +
                                                    "}"
                                    ),
                                    @ExampleObject(
                                            name = "E-mail duplicado",
                                            value = "{\n" +
                                                    "\t\"campo\": \"email\",\n" +
                                                    "\t\"mensagem\": \"E-mail já cadastrado no sistema.\"\n" +
                                                    "}"
                                    )
                            }
                    )
            )
    })
    ResponseEntity cadastraUsuario(@RequestBody @Valid CadastraUsuarioDto usuarioDto,
                                   UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity detalhaUsuarioPorId(@PathVariable Long id);

    ResponseEntity apagaUsuario(@PathVariable Long id);

    ResponseEntity<Page<ListaUsuarioDto>> listaUsuario(@PageableDefault(size = 5, sort = {"id"}) Pageable pagina);

    ResponseEntity atualizaUsuario(@PathVariable Long id, @RequestBody @Valid AtualizaUsuarioDto usuarioDto);
}
