package com.vagas.gestao.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vagas.gestao.modules.candidate.usecase.AuthCandidateUseCase;
import com.vagas.gestao.providers.JWTCandidateProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

    private final AuthCandidateUseCase authCandidateUseCase;

    @Autowired
    private JWTCandidateProvider jwtProvider;

    SecurityCandidateFilter(AuthCandidateUseCase authCandidateUseCase) {
        this.authCandidateUseCase = authCandidateUseCase;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Por enquanto, só deixa passar a requisição
        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/candidate/")) {

            if (header != null) {
                var token = this.jwtProvider.validateToken(header);
                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                request.setAttribute("candidate_id", token.getSubject());
                var roles = token.getClaim("roles").asList(String.class);

                var grants = roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).toList();
            }
            filterChain.doFilter(request, response);

        }
    }
}