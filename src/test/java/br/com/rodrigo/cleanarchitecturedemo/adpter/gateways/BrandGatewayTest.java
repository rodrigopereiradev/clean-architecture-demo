package br.com.rodrigo.cleanarchitecturedemo.adpter.gateways;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities.BrandEntity;
import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.BrandRepository;
import br.com.rodrigo.cleanarchitecturedemo.adpter.mappers.BrandMapper;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BrandGatewayTest {

    @Mock
    private BrandMapper mapper;

    @Mock
    private BrandRepository repository;

    @InjectMocks
    private BrandGateway gateway;

    @Test
    void shouldCallMethodToEntityFromMapperWhenCreatingBrand() {
        var brand = Brand.builder().build();
        var brandEntity = BrandEntity.builder().build();
        when(mapper.toEntity(brand)).thenReturn(brandEntity);

        gateway.save(brand);

        verify(mapper).toEntity(brand);
    }

    @Test
    void shouldCallMethodSaveFromRepositoryWhenCreatingBrand() {
        var brand = Brand.builder().build();
        var brandEntity = BrandEntity.builder().build();
        when(mapper.toEntity(brand)).thenReturn(brandEntity);

        gateway.save(brand);

        verify(repository).save(brandEntity);
    }

    @Test
    void shouldCallMethodToEntityFromMapperWhenUpdatingBrand() {
        var brand = Brand.builder().build();
        var brandEntity = BrandEntity.builder().build();
        when(mapper.toEntity(brand)).thenReturn(brandEntity);

        gateway.update(brand);

        verify(mapper).toEntity(brand);
    }

    @Test
    void shouldCallMethodUpdateFromRepositoryWhenCreatingBrand() {
        var brand = Brand.builder().build();
        var brandEntity = BrandEntity.builder().build();
        when(mapper.toEntity(brand)).thenReturn(brandEntity);

        gateway.update(brand);

        verify(repository).update(brandEntity);
    }

    @Test
    void shouldCallMethodFromEntityFromMapperWhenFindingById() {
        var brand = Brand.builder().id(1L).build();
        var brandEntity = BrandEntity.builder().id(1L).build();
        when(mapper.fromEntity(brandEntity)).thenReturn(brand);
        when(repository.findById(1L)).thenReturn(Optional.of(brandEntity));

        gateway.findById(1L);

        verify(mapper).fromEntity(brandEntity);

    }

    @Test
    void shouldFindBrandById() {
        var brandEntity = BrandEntity.builder().id(1L).build();
        var brand = Brand.builder().id(1L).build();
        when(repository.findById(1L)).thenReturn(Optional.of(brandEntity));
        when(mapper.fromEntity(brandEntity)).thenReturn(brand);

        brand = gateway.findById(1L);

        assertNotNull(brand);

    }

    @Test
    void shouldThrowExceptionWhenBrandNotFound() {
        var brandEntity = BrandEntity.builder().id(1L).build();
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> gateway.findById(1L));

    }

    @Test
    void shouldReturnAtLeastOneBrandWhenFindingAll() {
        var brandsEntity = Collections.singletonList(BrandEntity.builder().build());
        when(repository.findAll()).thenReturn(brandsEntity);

        var brands = gateway.findAll();

        assertFalse(brands.isEmpty());
    }



}
