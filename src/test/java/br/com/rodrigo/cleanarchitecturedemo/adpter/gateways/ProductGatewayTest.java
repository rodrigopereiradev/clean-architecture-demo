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

}