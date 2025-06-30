package com.vagas.gestao.modules.company.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vagas.gestao.modules.company.UseCase.AuthCompanyUseCase;
import com.vagas.gestao.modules.company.dto.AuthCompanyDto;
import com.vagas.gestao.modules.company.dto.AuthCompanyResponseDto;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    public ResponseEntity<AuthCompanyResponseDto> authenticate(@RequestBody AuthCompanyDto authCompanyDto) {
        try {
            var result = authCompanyUseCase.execute(authCompanyDto);
            return ResponseEntity.ok(result);
        } catch (javax.naming.AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

