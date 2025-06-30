package com.vagas.gestao.modules.company.Controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vagas.gestao.modules.company.UseCase.CreateJobUseCase;
import com.vagas.gestao.modules.company.dto.CreateJobDto;
import com.vagas.gestao.modules.company.entities.JobEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
@PreAuthorize("hasRole('COMPANY')")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDto createJobDto , HttpServletRequest request) {
        try {
            
            var company_id = request.getAttribute("company_id");

           var entity = JobEntity.builder().
                    benefits(createJobDto.getBenefits())
                    .description(createJobDto.getDescription())
                    .level(createJobDto.getLevel())
                    .company_id(UUID.fromString(company_id.toString()))
                    .build();
            
            return ResponseEntity.ok().body(entity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
