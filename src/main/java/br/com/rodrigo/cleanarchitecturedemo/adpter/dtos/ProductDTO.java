package br.com.rodrigo.cleanarchitecturedemo.adpter.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Builder
public class ProductDTO {

    @Setter
    private Long id;

    @NotBlank(message = "The product's name is mandatory.")
    @Size(max = 100, message = "The product's name should have a maximum of 100 characters")
    private String name;

    @Size(max = 100, message = "The product's description should have a maximum of 250 characters")
    private String description;

    @NotBlank(message = "The product's name is mandatory.")
    @Size(max = 100, message = "The product's name should have a maximum of 100 characters")
    private String brand;

    @NotNull(message = "The product's quantity is mandatory.")
    private Integer quantity;

    @NotNull(message = "The product's value is mandatory.")
    private BigDecimal value;
}
