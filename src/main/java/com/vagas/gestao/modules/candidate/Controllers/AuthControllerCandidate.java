package com.vagas.gestao.modules.candidate.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vagas.gestao.modules.candidate.UseCase.AuthCandidateUseCase;
import com.vagas.gestao.modules.candidate.dto.AuthCandidateRequestDto;

@RestController
@RequestMapping("/candidate")
public class AuthControllerCandidate {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> authCandidate(@RequestBody AuthCandidateRequestDto authCandidateRequestDto) {
        try {
            var result = this.authCandidateUseCase.execute(authCandidateRequestDto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
