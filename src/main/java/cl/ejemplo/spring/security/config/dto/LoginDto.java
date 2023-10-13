package cl.ejemplo.spring.security.config.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String username;
    private String password;
}
