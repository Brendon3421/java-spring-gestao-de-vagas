package com.vagas.gestao.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class JWTCandidateProvider {

    @Value("${security.token.secret.candidate}") 
    private String secretKey;

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

       try {
        var tokenDecoded = JWT.require(algorithm)
            .build()
            .verify(token);
            return tokenDecoded;
       } catch (JWTVerificationException e) {
        e.printStackTrace();
        System.out.println("Erro ao validar o token: " + e.getMessage());
        return null;
       }
    }

}
