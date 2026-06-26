package br.com.costa.cadastrodeprodutosSecurity.config;

import br.com.costa.cadastrodeprodutosSecurity.security.service.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

   @Bean
   SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http ) throws Exception {
       return http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                       .requestMatchers(HttpMethod.POST, "/v1/users").permitAll()
                       .requestMatchers(HttpMethod.POST, "/v1/product").hasAuthority("ROLE_USER")
                       .requestMatchers("/error").permitAll()
                       .anyRequest().authenticated()
               )
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
   }

   @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
       return config.getAuthenticationManager();
   }
}
