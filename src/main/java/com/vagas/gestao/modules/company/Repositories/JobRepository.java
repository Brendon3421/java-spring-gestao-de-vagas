package com.vagas.gestao.modules.company.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vagas.gestao.modules.company.entities.JobEntity;


public interface JobRepository  extends JpaRepository<JobEntity, UUID>{
    
}
