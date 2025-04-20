package com.taskmanager.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Value(value = "${spring.application.name:Smart Task Management System}")
	private String title;

	@Value(value = "${swagger.version:3.0}")
	private String swaggerVersion;

	@Bean
	OpenAPI customOpenAPI() {

		StringBuilder sb = new StringBuilder();
		sb.append("APIs for ");
		sb.append(title);
		return new OpenAPI().info(new Info().title(title).version(swaggerVersion).description(sb.toString()))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
				.components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme().name("Authorization")
						.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}

}
