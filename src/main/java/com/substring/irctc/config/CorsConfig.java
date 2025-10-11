package com.substring.irctc.config;

import com.substring.irctc.dto.TrainImageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;

@Configuration
public class CorsConfig {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")      //allow all origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE")     //allow all methods
                        .allowedHeaders("*")       //allow all headers
                        .allowCredentials(true);   //allow credentials
            }
        };
    }


    @Bean
    public TrainImageResponse trainImageResponse() {
        // Return a default or dummy instance if needed
        return new TrainImageResponse(0L, "", "", "", 0L, LocalDateTime.now());
    }
}
