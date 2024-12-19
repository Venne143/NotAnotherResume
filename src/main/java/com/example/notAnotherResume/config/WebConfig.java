package com.example.notAnotherResume.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Allow requests from your Vercel frontend URL
                registry.addMapping("/**")
                        .allowedOrigins("https://not-another-resume-ui.vercel.app") // Replace with your Vercel URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow necessary HTTP methods
                        .allowedHeaders("*"); // Allow all headers
            }
        };
    }
}