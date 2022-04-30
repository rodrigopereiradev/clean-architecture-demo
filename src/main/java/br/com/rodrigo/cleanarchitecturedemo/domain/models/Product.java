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
    private Boolean isActive;
    private LocalDateTime createdIn;
    private LocalDateTime updatedIn;

    public void setInformationsAtCreation() {
        this.isActive = Boolean.FALSE;
        this.createdIn = LocalDateTime.now();
    }

    public void activate() {
        this.isActive = Boolean.TRUE;
        this.updatedIn = LocalDateTime.now();
    }

    public void inactivate() {
        this.isActive = Boolean.FALSE;
        this.updatedIn = LocalDateTime.now();
    }

    public void increasesQuantity(Integer quantityAdditional) {
        this.quantity += quantityAdditional;
        this.updatedIn = LocalDateTime.now();
    }

    public void decreasesQuantity(Integer quantityDecreased) {
        this.quantity -= quantityDecreased;
        this.updatedIn = LocalDateTime.now();
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
