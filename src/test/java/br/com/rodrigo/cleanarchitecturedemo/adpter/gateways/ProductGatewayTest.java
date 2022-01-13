package br.com.rodrigo.cleanarchitecturedemo.adpter.gateways;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.ProductRepository;
import br.com.rodrigo.cleanarchitecturedemo.adpter.models.entities.ProductEntity;
import br.com.rodrigo.cleanarchitecturedemo.adpter.models.mappers.ProductMapper;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductGatewayTest {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductGateway productGateway;

    @Test
    void shouldCallMethodToEntityFromMapperWhenCreatingProduct() {
        var product = Product.builder().build();
        var productEntity = ProductEntity.builder().build();
        when(productMapper.toEntity(product)).thenReturn(productEntity);
        productGateway.save(product);
        verify(productMapper).toEntity(product);
    }

    @Test
    void shouldCallMethodToSaveFromRepositoryWhenCreatingProduct() {
        var product = Product.builder().build();
        var productEntity = ProductEntity.builder().build();
        when(productMapper.toEntity(product)).thenReturn(productEntity);
        productGateway.save(product);
        verify(productRepository).save(productEntity);
    }

    @Test
    void shouldReturnProductById() {
        var productEntity = ProductEntity.builder().id(1L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(productMapper.fromEntity(productEntity)).thenReturn(Product.builder().id(1L).build());
        var productRetrieved = productGateway.findById(1L);
        assertNotNull(productRetrieved);
    }

    @Test
    void shouldReturnAtListOneProjectWhenFindingAllProjects() {
        var productEntities = Collections.singletonList(ProductEntity.builder().id(1L).build());
        when(productRepository.findAll()).thenReturn(productEntities);
        var products = productGateway.findAll();
        assertFalse(products.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenProductAlreadyExists() {
        when(productRepository.existsById(1L)).thenReturn(true);
        var productExists = productGateway.productExists(1L);
        assertTrue(productExists);

    }

    @Test
    void shouldReturnFalseWhenProductAlreadyExists() {
        when(productRepository.existsById(1L)).thenReturn(false);
        var productExists = productGateway.productExists(1L);
        assertFalse(productExists);

    }

}