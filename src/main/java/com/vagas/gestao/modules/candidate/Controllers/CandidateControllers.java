package com.vagas.gestao.modules.candidate.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vagas.gestao.modules.candidate.UseCase.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateControllers {


    
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
    /**
     * @param candidateEntity
     * @return
     */
    @PostMapping ("/")
    public  ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    
}