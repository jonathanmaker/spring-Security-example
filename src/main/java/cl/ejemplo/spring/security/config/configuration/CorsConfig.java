package cl.ejemplo.spring.security.config.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        /*
        dentro del siguiente metodo definimos las reglas globales que afecraran al proyecto
        */
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        /*
        en la siguiente seccion autorizamos todos los llamados que provengan desde el origen x
        */
        corsConfiguration.setAllowedOrigins(Arrays.asList("http:localhost:4200"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        /*
        creamos un objeto URL... y le registramos una configuracopn de cors para todo el proyecto
        */
        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
