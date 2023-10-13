package cl.ejemplo.spring.security.config.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ConsultaSimple {

    @GetMapping("/consulta")
    @CrossOrigin(origins = "http://localhost:4200") //ruta de ejemplo que representa de manera explisita el origen de consulta permitido
    public String listar(){
        return "respuesta tipo oauth ok";
    }
}
