package br.com.rodrigo.cleanarchitecturedemo.adpter.mappers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities.BrandEntity;
import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.BrandResponseDTO;
import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.ProductDTO;
import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.ProductResponseDTO;
import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities.ProductEntity;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final BrandMapper brandMapper;

    public ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(brandMapper.toEntity(product.getBrand()))
                .quantity(product.getQuantity())
                .value(product.getValue())
                .isActive(product.getIsActive())
                .createdIn(product.getCreatedIn())
                .updatedIn(product.getUpdatedIn())
                .build();
    }

    public Product fromDto(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .brand(Brand.builder()
                        .id(productDTO.getIdBrand())
                        .build())
                .quantity(productDTO.getQuantity())
                .value(productDTO.getValue())
                .build();
    }

    public Product fromEntity(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .brand(brandMapper.fromEntity(productEntity.getBrand()))
                .quantity(productEntity.getQuantity())
                .value(productEntity.getValue())
                .isActive(productEntity.getIsActive())
                .updatedIn(productEntity.getUpdatedIn())
                .createdIn(productEntity.getCreatedIn())
                .build();
    }

    public ProductResponseDTO fromProductToDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(BrandResponseDTO.builder()
                        .id(product.getBrand().getId())
                        .corporateName(product.getBrand().getCorporateName())
                        .fantasyName(product.getBrand().getFantasyName())
                        .build())
                .quantity(product.getQuantity())
                .value(product.getValue())
                .isActive(product.getIsActive())
                .updatedIn(product.getUpdatedIn())
                .createdIn(product.getCreatedIn())
                .build();
    }
}
