package com.vagas.gestao.modules.company.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vagas.gestao.exceptions.UserFoundException;
import com.vagas.gestao.modules.company.Repositories.CompanyRepository;
import com.vagas.gestao.modules.company.entities.CompanyEntity;

import jakarta.validation.Valid;


@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(@Valid CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(
            companyEntity.getUsername(), 
            companyEntity.getEmail()
        ).ifPresent((user) -> {
            throw new UserFoundException("Username já existe or email");
        });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
