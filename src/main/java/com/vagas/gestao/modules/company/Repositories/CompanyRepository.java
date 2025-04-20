package com.vagas.gestao.modules.company.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vagas.gestao.modules.company.entities.CompanyEntity;

public interface  CompanyRepository  extends  JpaRepository<CompanyEntity, UUID>{

    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

}
