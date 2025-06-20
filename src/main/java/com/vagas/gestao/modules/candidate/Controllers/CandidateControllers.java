package com.vagas.gestao.modules.candidate.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vagas.gestao.modules.candidate.usecase.CreateCandidateUseCase;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vagas.gestao.modules.candidate.usecase.ProfileCandidateUseCase;

import jakarta.servlet.http.HttpServletRequest;


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
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }


    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;
    /**
     * @param id
     * @return
     */


    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try{
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());

        }   
    }
    
}