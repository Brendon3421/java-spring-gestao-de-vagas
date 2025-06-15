package com.vagas.gestao.modules.candidate.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vagas.gestao.modules.candidate.CandidateRepository;
import com.vagas.gestao.modules.candidate.dto.ProfileCandidateResponseDto;

@Service
public class ProfileCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDto execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
            .orElseThrow(() -> new UsernameNotFoundException("Candidato não encontrado"));

        var candidateDto = ProfileCandidateResponseDto.builder()
            .id(candidate.getId())
            .name(candidate.getName())
            .email(candidate.getEmail())
            .username(candidate.getUsername())
            .description(candidate.getDescripiton())
            .build();

        return candidateDto;
    }
}
