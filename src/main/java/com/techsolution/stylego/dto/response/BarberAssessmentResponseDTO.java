package com.techsolution.stylego.dto.response;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarberAssessmentResponseDTO {

    @NotNull(message = "O UUID do barbeiro é obrigatório")
    private String barberUuid;

    @NotNull(message = "O UUID do usuário é obrigatório")
    private String userUuid;

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A avaliação mínima é 1")
    @Max(value = 5, message = "A avaliação máxima é 5")
    private Short assessment;
}
