package com.aceleracion.ecommercegyl.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfiguration {
    @Value("${openapi.title}")
    private String title;
    @Value("${openapi.description}")
    private String description;
    @Value("${openapi.version}")
    private String version;
    @Value("${aws.apiGateway.url}")
    private String apiGatewayUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                )
                .servers(Collections.singletonList(new Server().url(apiGatewayUrl)));
    }
}