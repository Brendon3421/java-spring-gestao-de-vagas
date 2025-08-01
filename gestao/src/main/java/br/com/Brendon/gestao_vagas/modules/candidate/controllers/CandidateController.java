package br.com.Brendon.gestao_vagas.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Brendon.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.Brendon.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.Brendon.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.Brendon.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.Brendon.gestao_vagas.modules.candidate.useCases.ListAllJobsbyFilterUseCase;
import br.com.Brendon.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.Brendon.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate3", description = "Get candidate profile")
public class CandidateController {
  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsbyFilterUseCase listAllJobsbyFilterUseCase;


  @Autowired
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @PostMapping("/")
  @Operation(summary = "cadastro de candidato", description = "This endpoint allows the creation of a new candidate profile.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = CandidateEntity.class))
      }),
      @ApiResponse(responseCode = "400", description = "Invalid input provided, such as missing required fields or invalid data format.")
  })
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")

  @Operation(summary = "Perfil ", description = "This endpoint allows candidates to list all jobs based on a specific filter.")

  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
      })
  })
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var idCandidate = request.getAttribute("candidate_id");

    try {
      var profile = this.profileCandidateUseCase
          .execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/Job")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "List all jobs by filter", description = "This endpoint allows candidates to list all jobs based on a specific filter.", responses = {
      @ApiResponse(responseCode = "200", description = "Jobs listed successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid filter provided")
  })
  @SecurityRequirement(name = "jwt_auth")
  public List<JobEntity> findByJobByFilter(@RequestBody String filter) {
    return this.listAllJobsbyFilterUseCase.execute(filter);
  }

  @PostMapping("/job/apply")
  @PreAuthorize("hasRole('CANDIDATE')")
  @SecurityRequirement(name = "jwt_auth")
  @Operation(summary = "Apply for a job", description = "This endpoint allows candidates to")
  public ResponseEntity<Object> applyJob(HttpServletRequest request,@RequestBody UUID jobId) {
   
    var idCandidate = request.getAttribute("candidate_id");

    try {
      var result = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), jobId);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }
}
