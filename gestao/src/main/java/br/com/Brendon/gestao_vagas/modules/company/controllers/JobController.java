package br.com.Brendon.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Brendon.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.Brendon.gestao_vagas.modules.company.entities.JobEntity;
import br.com.Brendon.gestao_vagas.modules.company.useCases.CreateJobUseCase;
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
@RequestMapping("/company")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/job")
  @PreAuthorize("hasRole('COMPANY')")
  @Tag(name = "VAGAS", description = "Criação de vagas")
  @Operation(description = "Essa funcao e responsavel por cadastrar uma vaga")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = JobEntity.class))
      })
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    try {
      var jobEntity = JobEntity.builder()
          .benefits(createJobDTO.getBenefits())
          .companyId(UUID.fromString(companyId.toString()))
          .description(createJobDTO.getDescription())
          .level(createJobDTO.getLevel())
          .build();

      var result =  this.createJobUseCase.execute(jobEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }
}
