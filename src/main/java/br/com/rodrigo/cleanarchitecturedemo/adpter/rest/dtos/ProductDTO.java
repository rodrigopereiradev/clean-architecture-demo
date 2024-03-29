package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "The product's name is mandatory.")
    @Size(max = 100, message = "The product's name should have a maximum of 100 characters")
    private String name;

    @Size(max = 100, message = "The product's description should have a maximum of 250 characters")
    private String description;

    @NotNull(message = "The product's brand id is mandatory.")
    private Long idBrand;

    @NotNull(message = "The product's quantity is mandatory.")
    private Integer quantity;

    @NotNull(message = "The product's value is mandatory.")
    @DecimalMin(value = "0.0", message = "The product's value needs to be highest or equal 0.0")
    @Digits(integer = 16, fraction = 2, message = "Value needs to be a decimal (17,2)")
    private BigDecimal value;
}
