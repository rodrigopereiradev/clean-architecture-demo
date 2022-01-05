package br.com.rodrigo.cleanarchitecturedemo.adpter.repositories.jpa.mappers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.repositories.jpa.models.ProductEntity;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;

public class ProductMapper {

    public ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .quantity(product.getQuantity())
                .value(product.getValue())
                .isActive(product.getIsActive())
                .createdIn(product.getCreatedIn())
                .updatedIn(product.getUpdatedIn())
                .build();
    }

}
