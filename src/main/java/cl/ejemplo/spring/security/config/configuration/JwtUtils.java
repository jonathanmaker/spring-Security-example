package cl.ejemplo.spring.security.config.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {

    private static String SECRET_KEY = "En_mi_local_funciona";
    private static Algorithm ALGORORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String crear(String username){
        return JWT.create()
                .withSubject(username)  // le pasamos el usuario
                .withIssuer("bff-oauth") // quien esta creando el token
                .withIssuedAt(new Date()) // le pasaomos la fecha de creacion
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(5)))  //le indicamos la duracion del token
                .sign(ALGORORITHM);
    }
}
