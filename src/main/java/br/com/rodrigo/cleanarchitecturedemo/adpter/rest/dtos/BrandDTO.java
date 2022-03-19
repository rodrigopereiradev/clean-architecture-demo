package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

    @Setter
    private Long id;

    @NotBlank(message = "Fantasy name is mandatory.")
    private String fantasyName;

    @NotBlank(message = "Corporate name is mandatory.")
    private String corporateName;

    private Boolean isActive;
    private LocalDateTime createdIn;
    private LocalDateTime updatedIn;

}
