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
    private String description;
    private String brand;
    private Integer quantity;
    private BigDecimal value;
    @Setter
    private Boolean isActive;
    @Setter
    private LocalDateTime createdIn;
    @Setter
    private LocalDateTime updatedIn;

    public void increasesQuantity(Integer quantityAdditional) {
        this.quantity += quantityAdditional;
    }

    public void decreasesQuantity(Integer quantityDecreased) {
        this.quantity -= quantityDecreased;
    }

    public void updateProperties(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.quantity = product.getQuantity();
        this.value = product.getValue();
        this.updatedIn = LocalDateTime.now();
    }
}
