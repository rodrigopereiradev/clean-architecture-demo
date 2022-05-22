package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponseDTO {

    private Long id;
    private String fantasyName;
    private String corporateName;
    private Boolean isActive;
    private LocalDateTime createdIn;

}
