package br.com.rodrigo.cleanarchitecturedemo.domain.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class Product {

    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal value;
    @Setter
    private Boolean isActive;
    @Setter
    private LocalDateTime createdIn;
    @Setter
    private LocalDateTime updatedIn;

}
