package br.com.Brendon.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateJobDTO {

  @Schema(example= "Vaga para pessoal de TI")
  private String description;
  @Schema(example= "Benefícios incluem vale transporte, vale alimentação e plano de saúde")
  private String benefits;
  @Schema(example= "PLENO")
  private String level;
}
