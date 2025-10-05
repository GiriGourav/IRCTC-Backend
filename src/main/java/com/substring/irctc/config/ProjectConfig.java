package com.substring.irctc.config;

import com.substring.irctc.dto.TrainImageResponse;
import com.substring.irctc.interceptors.MyCustomInterceptors;
import com.substring.irctc.interceptors.TimeLoggerInterceptor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
    @Autowired
    private MyCustomInterceptors myCustomInterceptors;

    @Autowired
    private TimeLoggerInterceptor timeLoggerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeLoggerInterceptor )
                .addPathPatterns("/trains/**","/stations/**")
                .excludePathPatterns("/trains/list");
    }

    @Bean
    public TrainImageResponse trainImageResponse() {
        // Return a default or dummy instance if needed
        return new TrainImageResponse(0L, "", "", "", 0L, LocalDateTime.now());
    }

}
