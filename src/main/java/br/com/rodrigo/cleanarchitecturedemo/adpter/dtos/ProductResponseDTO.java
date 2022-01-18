package br.com.rodrigo.cleanarchitecturedemo.adpter.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private Integer quantity;
    private BigDecimal value;
    private Boolean isActive;
    private LocalDateTime createdIn;
    private LocalDateTime updatedIn;
}
