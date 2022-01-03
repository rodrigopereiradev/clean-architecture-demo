package br.com.rodrigo.cleanarchitecturedemo.domain.userCases;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.ProductException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.Ports.ProductPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductUserCaseTest {

    @Mock
    private ProductPort port;

    @InjectMocks
    private ProductUserCase userCase;

    @Test
    void shouldCallSaveMethodFromPortWhenCreatingProduct() {
        var product = Product.builder().build();
        userCase.create(product);
        verify(port).save(product);
    }

    @Test
    void shouldSetCreationDateOnProduct() {
        var product = Product.builder().build();
        userCase.create(product);
        assertNotNull(product.getCreatedIn());
    }

    @Test
    void shouldCallSaveMethodFromPortWhenUpdatingProduct() {
        var product = Product.builder().build();
        userCase.update(product);
        verify(port).save(product);
    }

    @Test
    void shouldSetUpdateDateOnProductWhenUpdatingProduct() {
        var product = Product.builder().build();
        userCase.update(product);
        assertNotNull(product.getUpdatedIn());
    }

    @Test
    void shouldReturnProductThatAlreadyExist() {
        var product = Product.builder().id(1L).build();
        when(port.findById(1L)).thenReturn(Optional.of(product));
        var productFound = userCase.findById(1L);
        assertEquals(productFound.getId(), 1L);
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {
        when(port.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductException.class,() -> userCase.findById(1L));

    }

}