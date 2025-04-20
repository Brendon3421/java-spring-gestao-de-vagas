package com.vagas.gestao.modules.company.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vagas.gestao.modules.company.Repositories.JobRepository;
import com.vagas.gestao.modules.company.entities.JobEntity;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){
       return this.jobRepository.save(jobEntity);
    }
}
