package br.com.costa.cadastrodeprodutosSecurity.security;

import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;
import br.com.costa.cadastrodeprodutosSecurity.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = authHeader.substring(7);
        final String email = jwtService.extractEmail(jwtToken);

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserEntity userEntity = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if(jwtService.isTokenValid(jwtToken, userEntity)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userEntity.getEmail(),
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + userEntity.getStatus().name()))
                );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

    }
}
