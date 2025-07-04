package br.com.Brendon.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
  @Schema(example = "Desenvolved Java Developer with 5 years of experience in building scalable applications.")
  private String description;
  @Schema(example = "brendon123")
  private String username;
  @Schema(example = "brendon@gmail.com")
  private String email;
  private UUID id;
  @Schema(example = "Brendon")
  private String name;
}
