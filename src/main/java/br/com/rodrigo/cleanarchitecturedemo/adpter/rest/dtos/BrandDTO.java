package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

    private Long id;

    @NotBlank(message = "Fantasy name is mandatory.")
    private String fantasyName;

    @NotBlank(message = "Corporate name is mandatory.")
    private String corporateName;

}
