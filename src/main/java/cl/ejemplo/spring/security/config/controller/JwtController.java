package cl.ejemplo.spring.security.config.controller;

import cl.ejemplo.spring.security.config.configuration.JwtUtils;
import cl.ejemplo.spring.security.config.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class JwtController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Autowired
    public JwtController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
        //creamos un user y pas a partir del request que nos llega del login
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        // creamos un objeto de autenticacacion que su valor es el llamado al autenticate del autentication mnager
        Authentication authentication = this.authenticationManager.authenticate(login);

        // creamos el token
        String jwt = this.jwtUtils.crear(loginDto.getUsername());

        // asignamos el token como respuesta
        return  ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
