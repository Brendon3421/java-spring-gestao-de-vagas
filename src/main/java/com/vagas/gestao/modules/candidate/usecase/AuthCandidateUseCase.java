package com.vagas.gestao.modules.candidate.usecase;

import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vagas.gestao.modules.candidate.CandidateRepository;
import com.vagas.gestao.modules.candidate.dto.AuthCandidateRequestDto;
import com.vagas.gestao.modules.candidate.dto.AuthcandidateResponseDto;

@Service
public class AuthCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public AuthcandidateResponseDto execute(AuthCandidateRequestDto authCandidateRequestDto) throws AuthenticationException{
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDto.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuario nao encontrado / senha incorreta");
        });

        var passwordMatches = passwordEncoder.matches(authCandidateRequestDto.password(), candidate.getPassword());
        if(!passwordMatches){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expires_in = java.time.Instant.now().plus(java.time.Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(expires_in)
                .sign(algorithm);
                var authcandidateResponseDto = AuthcandidateResponseDto.builder()
                .acess_token(token)
                .expires_in(expires_in.toEpochMilli())
                .build();
                return authcandidateResponseDto;
    }
}
