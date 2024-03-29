package cl.ejemplo.spring.security.config.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

     @Bean
     public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                  .csrf().disable()
                  .cors().and()
                  .authorizeHttpRequests()
                  .requestMatchers("/api/auth/**").permitAll()
                  .requestMatchers(HttpMethod.GET, "/api/*").permitAll()
                  .requestMatchers(HttpMethod.POST, "/api/guardar*").hasRole("DEVSECOPS")
                  .requestMatchers(HttpMethod.PUT).denyAll()
                  .anyRequest()
                  .authenticated()
                  .and()
                  .httpBasic();

          return http.build();
     }


     /*
     A continuación se muestra la forma de crear usuarios  en memoria
     para no usar el por defecto de SpringSecurity.
     */
     @Bean
     public UserDetailsService memoryUser(){
          UserDetails admin = User.builder()
                  .username("BancoChile")
                  .password(passwordEncoder().encode("Moneda03"))
                  .roles("ADMIN")
                  .build();

          UserDetails customer = User.builder()
                  .username("BancoChile2")
                  .password(passwordEncoder().encode("Moneda04"))
                  .roles("DEVSECOPS")
                  .build();

          return  new InMemoryUserDetailsManager(admin,customer);
     }

     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
          return configuration.getAuthenticationManager();
     }


     @Bean
     public PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder();
     }



}
