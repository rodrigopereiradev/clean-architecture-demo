package br.com.rodrigo.cleanarchitecturedemo.domain.userCases;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.ProductException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.Ports.ProductPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock
    private ProductPort port;

    @InjectMocks
    private ProductUseCase useCase;

    @Test
    void shouldCallSaveMethodFromPortWhenCreatingProduct() {
        var product = Product.builder().build();
        useCase.create(product);
        verify(port).save(product);
    }

    @Test
    void shouldSetCreationDateOnProductWhenItIsCreated() {
        var product = Product.builder().build();
        useCase.create(product);
        assertNotNull(product.getCreatedIn());
    }

    @Test
    void shouldCallSaveMethodFromPortWhenUpdatingProduct() {
        var product = Product.builder().id(1L).build();
        when(port.productExists(1L)).thenReturn(true);
        useCase.update(product);
        verify(port).save(product);
    }

    @Test
    void shouldSetUpdateDateOnProductWhenUpdatingProduct() {
        var product = Product.builder().id(1L).build();
        when(port.productExists(1L)).thenReturn(true);
        useCase.update(product);
        assertNotNull(product.getUpdatedIn());
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExistsWhenUpdating() {
        var product = Product.builder().id(1L).build();
        when(port.productExists(1L)).thenReturn(false);
        assertThrows(ProductException.class, () -> useCase.update(product));
    }

    @Test
    void shouldReturnTrueWheTheProductAlreadyExists() {
        when(port.productExists(1L)).thenReturn(true);
        boolean productExists = useCase.productExists(1L);
        assertTrue(productExists);
    }

    @Test
    void shouldReturnProductThatAlreadyExist() {
        var product = Product.builder().id(1L).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        var productFound = useCase.findById(1L);
        assertEquals(productFound.getId(), 1L);
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {
        when(port.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductException.class,() -> useCase.findById(1L));
    }

    @Test
    void  shouldSetTrueToIsActiveWhenActivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        useCase.activate(1L);
        assertTrue(product.getIsActive());
    }

    @Test
    void shouldCallMethodSaveFromPortWhenActivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        useCase.activate(1L);
        verify(port).save(product);
    }

    @Test
    void shouldSetUpdateDateWhenActivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        useCase.activate(1L);
        assertNotNull(product.getUpdatedIn());
    }

    @Test
    void  shouldSetFalseToIsActiveWhenInactivatingProduct() {
        var product = Product.builder().isActive(Boolean.TRUE).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        useCase.inactivate(1L);
        assertFalse(product.getIsActive());
    }

    @Test
    void shouldCallMethodSaveFromPortWhenInactivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        useCase.inactivate(1L);
        verify(port).save(product);
    }

    @Test
    void shouldSetUpdateDateWhenInactivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        useCase.inactivate(1L);
        assertNotNull(product.getUpdatedIn());
    }

    @Test
    void shouldBringAtLeastOneProductWhenFindAll() {
        var product = Product.builder().build();
        when(port.findAll()).thenReturn(Collections.singletonList(product));
        var products = useCase.findAll();
        assertFalse(products.isEmpty());
    }

    @Test
    void shouldBringEmptyListWhenProductsNotFound() {
        var product = Product.builder().build();
        when(port.findAll()).thenReturn(new ArrayList<>());
        var products = useCase.findAll();
        assertTrue(products.isEmpty());
    }

    @Test
    void shouldIncreasesProducts() {
        var product = Product.builder().id(1L).quantity(100).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        useCase.increases(1L, 50);
        assertEquals(150, product.getQuantity());
    }

    @Test
    void shouldDecreasesProducts() {
        var product = Product.builder().id(1L).quantity(150).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        useCase.decreases(1L, 50);
        assertEquals(100, product.getQuantity());
    }

}