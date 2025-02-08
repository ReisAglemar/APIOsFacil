package com.APIosFacil.usuario.config.openApi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI personalizadoOpenAPI() {
        return new OpenAPI()
                .info(new Info()

                        .title("OsFácil | Usuários")
                        .version("V1.0.25")
                        .summary("REST API usuários")
                        .description("Módulo para manter usuário")
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")
                        )
                )

                .externalDocs(new ExternalDocumentation()
                        .description("GitHub")
                        .url("https://github.com/ReisAglemar/APIOsFacil")
                )
                .tags(
                        Arrays.asList(
                                new Tag().name("Api Usuário").description("End points disponíveis para operar usuários")
                        )
                );
    }
}
