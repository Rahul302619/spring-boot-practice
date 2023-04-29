package com.rks.springbootpractice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Practice API",
                version = "2.0",
                description = "Use for practicing Rest API")
)
public class PracticeConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
