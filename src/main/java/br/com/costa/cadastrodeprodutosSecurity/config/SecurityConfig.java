package br.com.costa.cadastrodeprodutosSecurity.config;

import br.com.costa.cadastrodeprodutosSecurity.security.service.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
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
                       .requestMatchers(HttpMethod.POST, "/v1/product").hasAuthority("ROLE_ADMIN")
                       .requestMatchers(HttpMethod.GET, "/v1/product").hasAuthority("ROLE_USER")
                       .requestMatchers(HttpMethod.GET, "/v1/product/{id}").hasAuthority("ROLE_USER")
                       .requestMatchers(HttpMethod.PUT, "/v1/product/{id}").hasAuthority("ROLE_ADMIN")
                       .requestMatchers(HttpMethod.DELETE, "/v1/product/{id}").hasAuthority("ROLE_ADMIN")
                       .requestMatchers("/error").permitAll()
                       .anyRequest().authenticated()

               )
               .exceptionHandling(ex -> ex
                       .authenticationEntryPoint(authenticationEntryPoint())
                       .accessDeniedHandler(accessDeniedHandler()))
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
   }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
       return config.getAuthenticationManager();
   }

   @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
       return (request, response, e) -> {
           response.setStatus(HttpStatus.UNAUTHORIZED.value());
           response.setContentType("application/json");
           response.getWriter().write("""
                   {
                    "status" : 401,
                    "error" : "Unauthorized",
                    "message" : "User not allowed to access this resource"
                   }
                   """);
       };
   }

   @Bean
    public AccessDeniedHandler accessDeniedHandler() {
       return (request, response, accessDeniedException) -> {
           response.setStatus(HttpStatus.FORBIDDEN.value());
           response.setContentType("application/json");
           response.getWriter().write("""
                    {
                    "status" : 403,
                    "error" : "Forbidden",
                    "message" : "User dont have permission to access this resource"
                   }
                   """);
       };
   }
}
