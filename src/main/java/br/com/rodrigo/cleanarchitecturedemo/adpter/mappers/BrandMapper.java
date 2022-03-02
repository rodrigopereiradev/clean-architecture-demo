package br.com.rodrigo.cleanarchitecturedemo.adpter.mappers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities.BrandEntity;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;

public class BrandMapper {

    public BrandEntity toEntity(Brand brand) {

        return BrandEntity
                .builder()
                .id(brand.getId())
                .fantasyName(brand.getFantasyName())
                .corporateName(brand.getCorporateName())
                .isActive(brand.getIsActive())
                .createdIn(brand.getCreatedIn())
                .updatedIn(brand.getUpdatedIn())
                .build();

    }

    public Brand fromEntity(BrandEntity brandEntity) {
        return Brand.builder()
                .id(brandEntity.getId())
                .fantasyName(brandEntity.getFantasyName())
                .corporateName(brandEntity.getCorporateName())
                .isActive(brandEntity.getIsActive())
                .createdIn(brandEntity.getCreatedIn())
                .updatedIn(brandEntity.getUpdatedIn())
                .build();
    }
}
