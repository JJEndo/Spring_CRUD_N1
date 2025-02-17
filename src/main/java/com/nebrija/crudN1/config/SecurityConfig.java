package com.nebrija.crudN1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Autorizar peticiones
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/","/login", "/css/**", "/js/**", "/proyectos", "/proyectos/**").permitAll()  // Recursos públicos
                        .anyRequest().authenticated()
                )
                // Configuración del formulario de login personalizado
                .formLogin(form -> form
                        .loginPage("/login")// Página de inicio de sesión personalizada
                        .defaultSuccessUrl("/proyectos", true) // Redirigir después de login
                        .permitAll()
                )
                // Configuración de logout
                .logout(logout -> logout
                        .logoutUrl("/")
                        .logoutSuccessUrl("/login?logout")
                );
        return http.build();
    }

}
