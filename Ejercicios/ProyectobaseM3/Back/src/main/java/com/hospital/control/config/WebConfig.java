package com.hospital.control.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 1. Aplica la regla a TODAS las rutas de nuestra API
        registry.addMapping("/api/**")

                // 2. Define qué orígenes (dominios) tienen licencia para entrar
                .allowedOrigins("http://localhost:5173", "http://127.0.0.1:5500")

                // 3. Define qué verbos HTTP están permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")

                // 4. Permite que el cliente envíe cualquier encabezado (Headers)
                .allowedHeaders("*")

                // 5. (Opcional pero recomendado) Permite el envío de cookies/tokens de sesión
                .allowCredentials(true);
    }

}
