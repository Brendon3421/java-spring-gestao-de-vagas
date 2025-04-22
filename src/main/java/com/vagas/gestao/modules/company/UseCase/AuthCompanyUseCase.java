package com.vagas.gestao.modules.company.UseCase;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vagas.gestao.modules.company.Repositories.CompanyRepository;
import com.vagas.gestao.modules.company.dto.AuthCompanyDto;


@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CompanyRepository companyRepository;

    public  String execute(AuthCompanyDto authCompanyDto) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDto.getUsername()).orElseThrow(
        () ->  {
             throw new UsernameNotFoundException("Username ou senha incorretos");
            });

            //Verificar a senha 
          var passwordMatches = this.passwordEncoder .matches(authCompanyDto.getPassword(), company.getPassword()
            );

            if(!passwordMatches) {
                throw new AuthenticationException("Username ou senha incorretos");
            }
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            var token = JWT.create().withExpiresAt(Instant.now().plus(Duration.ofHours(2))).withIssuer("javagas").withSubject(company.getId().toString()).sign(algorithm);
            return token;
    }
}
