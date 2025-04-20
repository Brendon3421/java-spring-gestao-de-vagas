package com.vagas.gestao.modules.candidate.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vagas.gestao.exceptions.UserFoundException;
import com.vagas.gestao.modules.candidate.CandidateRepository;
import com.vagas.gestao.modules.candidate.Controllers.CandidateEntity;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException("Usuario ja existe");
        });
        System.out.println("Usuario cadastrado com sucesso");
        return this.candidateRepository.save(candidateEntity);

    }

}
