package br.com.Brendon.gestao_vagas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean // sobresscreve uma implementacao que ja existe
    public OpenAPI opeanAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Gestão de Vagas API")
                        .version("1.0.0")
                        .description("API para gerenciamento de vagas de emprego"))
                        .schemaRequirement("jwt_auth", createSecurityScheme());

    }

    private SecurityScheme createSecurityScheme() {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .name("jwt_auth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

}
