package com.hospital.control;

// Importes necesarios para ejecutar la aplicación Spring Boot.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * 
 * @SpringBootApplication es una anotación que combina tres anotaciones importantes:
 * - @Configuration: Indica que esta clase contiene configuraciones de Spring.
 * - @EnableAutoConfiguration: Habilita la auto-configuración de Spring Boot.
 * - @ComponentScan: Escanea todos los componentes (@Service, @Controller, @Repository) en el paquete.
 * 
 * NOTA: Una vez la aplicación esté en ejecución, puede acceder a la documentación interactiva de Swagger en:
 * http://localhost:8080/swagger-ui.html
 * 
 * También puede acceder a la especificación OpenAPI en formato JSON en:
 * http://localhost:8080/v3/api-docs
 */
@SpringBootApplication
public class ControlApplication {

	/**
	 * Método principal de la aplicación.
	 * Es el punto de entrada cuando ejecutamos la aplicación.
	 * 
	 * @param args - Argumentos de línea de comandos (no usados en este caso).
	 */
	public static void main(String[] args) {
		// SpringApplication.run() inicia la aplicación Spring Boot.
		// Recibe la clase de la aplicación y los argumentos de línea de comandos.
		// Esto inicializa el contenedor de Spring, carga todos los componentes,
		// configura la base de datos y levanta el servidor web (Tomcat por defecto).
		SpringApplication.run(ControlApplication.class, args);
	}
}
