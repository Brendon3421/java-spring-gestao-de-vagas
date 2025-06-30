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

@RestController
@RequestMapping("/auth")
public class CompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDto AuthCompanyDto) {
       try {
        var result = this.authCompanyUseCase.execute(AuthCompanyDto);
        return ResponseEntity.ok().body(result);
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    } 
       
    }
}
