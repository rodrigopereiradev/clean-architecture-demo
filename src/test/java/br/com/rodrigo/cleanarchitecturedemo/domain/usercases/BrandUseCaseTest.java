package br.com.rodrigo.cleanarchitecturedemo.domain.usercases;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.BrandException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations.BrandUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.BrandPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {

    @Mock
    private BrandPort port;


    private IBrandUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new BrandUseCase(port);
    }

    @Test
    void shouldSaveBrandWithCreationDate() {

        var brand = Brand.builder().build();

        useCase.create(brand);

        assertNotNull(brand.getCreatedIn());

    }

    @Test
    void shouldCallMethodSaveFromPort() {

        var brand = Brand.builder().build();

        useCase.create(brand);

        verify(port).save(brand);

    }

    @Test
    void shouldCreateInactiveBrand() {
        var brand = Brand.builder().build();

        useCase.create(brand);

        assertNotNull(brand.getIsActive());
    }

    @Test
    void shouldReturnBrandById() {
        var brand = Brand.builder().id(1L).fantasyName("Test").corporateName("Teste LTDA").createdIn(LocalDateTime.now()).isActive(Boolean.TRUE).build();

        when(port.findById(1L)).thenReturn(brand);

        assertNotNull(useCase.findById(1L));

    }

    @Test
    void shouldThrowBrandExceptionWhenBrandNotFound() {

        when(port.findById(1L)).thenReturn(null);

        assertThrows(BrandException.class, () -> useCase.findById(1L));

    }

    @Test
    void shouldCallUpdateMethodFromPort() {
        var brand = Brand.builder().id(1L).build();

        when(port.findById(1L)).thenReturn(brand);

        useCase.update(brand);

        verify(port).update(brand);
    }

    @Test
    void shouldBringAtLeastOneBrandWhenFindingAll() {
        var brands = Collections.singletonList(Brand.builder().id(1L).build());
        when(port.findAll()).thenReturn(brands);

       var brandsRecovered = useCase.findAll();

       assertFalse(brandsRecovered.isEmpty());
    }

    @Test
    void shouldActivateAnInactiveBrand() {
        var brand = Brand.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(brand);
        useCase.activate(1L);
        assertTrue(brand.getIsActive());
    }

    @Test
    void shouldSetUpdateDateWhenActivatingBrand() {
        var brand = Brand.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(brand);
        useCase.activate(1L);
        assertNotNull(brand.getUpdatedIn());
    }

    @Test
    void shouldCallUpdateMethodFromPortWhenActivatingBrand() {
        var brand = Brand.builder().isActive(Boolean.FALSE).build();
        when(port.findById(1L)).thenReturn(brand);
        useCase.activate(1L);
        verify(port).update(brand);
    }

    @Test
    void shouldInactivateAnActiveBrand() {
        var brand = Brand.builder().isActive(Boolean.TRUE).build();
        when(port.findById(1L)).thenReturn(brand);
        useCase.inactivate(1L);
        assertFalse(brand.getIsActive());
    }

    @Test
    void shouldCallUpdateMethodFromPortWhenInactivatingBrand() {
        var brand = Brand.builder().isActive(Boolean.TRUE).build();
        when(port.findById(1L)).thenReturn(brand);
        useCase.inactivate(1L);
        verify(port).update(brand);
    }

    @Test
    void shouldSetUpdateDateWhenInactivatingBrand() {
        var brand = Brand.builder().isActive(Boolean.TRUE).build();
        when(port.findById(1L)).thenReturn(brand);
        useCase.inactivate(1L);
        assertNotNull(brand.getUpdatedIn());
    }

}
