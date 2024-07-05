package temp.desafio.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIDocSwagger() {

        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
                .title("API - Clima Tempo Brasil")
                .contact(new Contact()
                        .name("Francis Santos")
                        .email("colaborador@db.empresa.br")
                        .url("https://github.com/clickfrancis/desafio-clima")
                )
                .description("Api com informações de dados climaticos das cidades em território brasileiro ")
                .version("v0.0.1")
        );
    }
}
