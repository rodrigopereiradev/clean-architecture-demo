package br.com.rodrigo.cleanarchitecturedemo.domain.usercases;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.BrandException;
import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.ProductException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations.BrandUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations.ProductUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.ProductPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock
    private ProductPort port;

    @Mock
    private BrandUseCase brandUseCase;

    @InjectMocks
    private ProductUseCase useCase;

    @Test
    void shouldCallSaveMethodFromPortWhenCreatingProduct() {
        var brand = Brand.builder().id(1L).build();
        var product = Product.builder().brand(brand).build();

        when(brandUseCase.findById(brand.getId())).thenReturn(brand);
        useCase.create(product);
        verify(port).save(product);
    }

    @Test
    void shouldSetCreationDateOnProductWhenItIsCreated() {
        Brand brand = Brand.builder().id(1L).build();
        var product = Product.builder().brand(brand).build();
        when(brandUseCase.findById(product.getBrand().getId())).thenReturn(brand);
        useCase.create(product);
        assertNotNull(product.getCreatedIn());
    }

    @Test
    void shouldCallSaveMethodFromPortWhenUpdatingProduct() {
        var brand = Brand.builder().id(1L).build();
        var product = Product.builder().id(1L).brand(brand).build();
        when(port.findById(1L)).thenReturn(product);
        when(brandUseCase.findById(product.getBrand().getId())).thenReturn(brand);
        useCase.update(product);
        verify(port).save(product);
    }

    @Test
    void shouldSetUpdateDateOnProductWhenUpdatingProduct() {
        var brand = Brand.builder().id(1L).build();
        var product = Product.builder().id(1L).brand(brand).build();
        when(port.findById(1L)).thenReturn(product);
        when(brandUseCase.findById(product.getBrand().getId())).thenReturn(brand);
        useCase.update(product);
        assertNotNull(product.getUpdatedIn());
    }

    @Test
    void shouldReturnProductThatAlreadyExist() {
        var product = Product.builder().id(1L).build();
        when(port.findById(1L)).thenReturn(product);
        var productFound = useCase.findById(1L);
        assertEquals( 1L, productFound.getId());
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {
        when(port.findById(1L)).thenReturn(null);
        assertThrows(ProductException.class,() -> useCase.findById(1L));
    }

    @Test
    void  shouldSetTrueToIsActiveWhenActivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(product);
        useCase.activate(1L);
        assertTrue(product.getIsActive());
    }

    @Test
    void shouldCallMethodSaveFromPortWhenActivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(product);
        useCase.activate(1L);
        verify(port).save(product);
    }

    @Test
    void shouldSetUpdateDateWhenActivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(product);
        useCase.activate(1L);
        assertNotNull(product.getUpdatedIn());
    }

    @Test
    void  shouldSetFalseToIsActiveWhenInactivatingProduct() {
        var product = Product.builder().isActive(Boolean.TRUE).build();
        when(port.findById(1L)).thenReturn(product);
        useCase.inactivate(1L);
        assertFalse(product.getIsActive());
    }

    @Test
    void shouldCallMethodSaveFromPortWhenInactivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(product);
        useCase.inactivate(1L);
        verify(port).save(product);
    }

    @Test
    void shouldSetUpdateDateWhenInactivatingProduct() {
        var product = Product.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(product);
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
        when(port.findById(1L)).thenReturn(product);
        useCase.increases(1L, 50);
        assertEquals(150, product.getQuantity());
    }

    @Test
    void shouldDecreasesProducts() {
        var product = Product.builder().id(1L).quantity(150).build();
        when(port.findById(1L)).thenReturn(product);
        useCase.decreases(1L, 50);
        assertEquals(100, product.getQuantity());
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExistsWhenUpdating() {
        var product = Product.builder().id(1L).build();
        when(port.findById(1L)).thenReturn(null);
        assertThrows(ProductException.class, () -> useCase.update(product));
    }

}
