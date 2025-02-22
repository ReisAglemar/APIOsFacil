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

    @Operation(
            summary = "Detalha Usuário Por id",
            description = "Endpoint para detalhar um usuário no sistema. O corpo da requisição deve informar um id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Apresenta o usuário localizado",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Usuário encontrado",
                                            value = "{\n" +
                                                    "\t\"id\": 1,\n" +
                                                    "\t\"nome\": \"João da Silva\",\n" +
                                                    "\t\"cpf\": \"357.271.030-88\",\n" +
                                                    "\t\"email\": \"joao.silva@email.com\",\n" +
                                                    "\t\"senha\": \"SenhaJoao137\",\n" +
                                                    "\t\"ativo\": true\n" +
                                                    "}"
                                    )
                            })
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado para o id fornecido",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Usuário não encontrado",
                                            value = "Nessa situação não haverá body na reposta, retorna apenas 404"
                                    )
                            })
            )
    })
    ResponseEntity detalhaUsuarioPorId(@PathVariable Long id);

    @Operation(
            summary = "Apaga usuário por id",
            description = "Endpoint para apagar usuário cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário terá seu atributo ativo modificado para false",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Usuário apagado",
                                            value = "Nessa situação não haverá body na reposta. " +
                                                    "O atributo ATIVO será modificado para FALSE e retorna 204"
                                    )
                            })
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado para o id fornecido",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Usuário não encontrado",
                                            value = "Nessa situação não haverá body na reposta, retorna apenas 404"
                                    )
                            })
            )
    })
    ResponseEntity apagaUsuario(@PathVariable Long id);

    @Operation(
            summary = "Lista todos os usuário",
            description = "Endpoint para listar todos os usuários cadastrados no sistema, retorna uma página com 5 usuários " +
                    "ordenados por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retorna uma página contendo informações da página e no máximo 5 usuários ordenados por id",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Lista Usuários",
                                            value = "{\n" +
                                                    "\t\"content\": [\n" +
                                                    "\t\t{\n" +
                                                    "\t\t\t\"id\": 1,\n" +
                                                    "\t\t\t\"nome\": \"João da Silva\",\n" +
                                                    "\t\t\t\"email\": \"joao.silva@email.com\"\n" +
                                                    "\t\t}\n" +
                                                    "\t],\n" +
                                                    "\t\"pageable\": {\n" +
                                                    "\t\t\"pageNumber\": 0,\n" +
                                                    "\t\t\"pageSize\": 5,\n" +
                                                    "\t\t\"sort\": {\n" +
                                                    "\t\t\t\"sorted\": true,\n" +
                                                    "\t\t\t\"unsorted\": false,\n" +
                                                    "\t\t\t\"empty\": false\n" +
                                                    "\t\t},\n" +
                                                    "\t\t\"offset\": 0,\n" +
                                                    "\t\t\"paged\": true,\n" +
                                                    "\t\t\"unpaged\": false\n" +
                                                    "\t},\n" +
                                                    "\t\"totalElements\": 1,\n" +
                                                    "\t\"totalPages\": 1,\n" +
                                                    "\t\"last\": true,\n" +
                                                    "\t\"first\": true,\n" +
                                                    "\t\"size\": 5,\n" +
                                                    "\t\"number\": 0,\n" +
                                                    "\t\"sort\": {\n" +
                                                    "\t\t\"sorted\": true,\n" +
                                                    "\t\t\"unsorted\": false,\n" +
                                                    "\t\t\"empty\": false\n" +
                                                    "\t},\n" +
                                                    "\t\"numberOfElements\": 1,\n" +
                                                    "\t\"empty\": false\n" +
                                                    "}"
                                    )
                            }
                    )
            )
    })
    ResponseEntity<Page<ListaUsuarioDto>> listaUsuario(@Parameter(hidden = true) @PageableDefault(size = 5, sort = {"id"}) Pageable pagina);

    ResponseEntity atualizaUsuario(@PathVariable Long id, @RequestBody @Valid AtualizaUsuarioDto usuarioDto);
}
