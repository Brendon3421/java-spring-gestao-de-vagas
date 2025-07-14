package br.com.Brendon.gestao_vagas.modules.candidate.UseCases;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.Brendon.gestao_vagas.exceptions.UserNotFoundException;
import br.com.Brendon.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.Brendon.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.Brendon.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.Brendon.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.Brendon.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.Brendon.gestao_vagas.modules.company.entities.JobEntity;
import br.com.Brendon.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply to job if candidate does not found")
    public void shouldNotBeAbleToApplyToJobIfCandidateDoesNotFound() {
        try {
            applyJobCandidateUseCase.execute(null, null);

        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void shouldBeAbleToCreateAnewApplyJob() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJobCreated = ApplyJobEntity.builder()
                .id(UUID.randomUUID())
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        when(candidateRepository.findById(idCandidate))
                .thenReturn(Optional.of(new CandidateEntity()));

        when(jobRepository.findById(idJob))
                .thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(any(ApplyJobEntity.class)))
                .thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}