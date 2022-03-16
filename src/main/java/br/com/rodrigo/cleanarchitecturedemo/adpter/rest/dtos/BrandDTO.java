package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

    private Long id;
    private String fantasyName;
    private String corporateName;
    private Boolean isActive;
    private LocalDateTime createdIn;
    private LocalDateTime updatedIn;

}
