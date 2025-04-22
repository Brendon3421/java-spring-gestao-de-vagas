package com.vagas.gestao.modules.candidate.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vagas.gestao.exceptions.UserFoundException;
import com.vagas.gestao.modules.candidate.CandidateRepository;
import com.vagas.gestao.modules.candidate.Controllers.CandidateEntity;

@Service
public class CreateCandidateUseCase {


    
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException("Usuario ja existe");
        });

        var password = passwordEncoder.encode(candidateEntity.getPassoword());
        candidateEntity.setPassoword(password);

        System.out.println("Usuario cadastrado com sucesso");
        return this.candidateRepository.save(candidateEntity);

    }

}
