package br.com.Brendon.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Brendon.gestao_vagas.exceptions.UserNotFoundException;
import br.com.Brendon.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.Brendon.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

        @Autowired
        private  CandidateRepository candidateRepository;

        @Autowired
        private JobRepository jobRepository;


    public void execute(UUID idCandidate, UUID idJob) {

        
        //Validar se o usuario existe 
       this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> new UserNotFoundException());

        //validar se a vaga existe

        //Candidato se Inscrever na Vaga
    }

}
