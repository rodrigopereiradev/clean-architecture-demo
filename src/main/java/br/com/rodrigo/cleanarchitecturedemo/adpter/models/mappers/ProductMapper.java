package br.com.rodrigo.cleanarchitecturedemo.adpter.models.mappers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.models.dtos.ProductDTO;
import br.com.rodrigo.cleanarchitecturedemo.adpter.models.entities.ProductEntity;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import org.springframework.stereotype.Component;


@Component
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

    public Product fromDto(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .Brand(productDTO.getBrand())
                .quantity(productDTO.getQuantity())
                .value(productDTO.getValue())
                .build();
    }

}
