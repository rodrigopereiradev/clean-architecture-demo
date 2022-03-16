package br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.ProductException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IProductUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.ProductPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ProductUseCase implements IProductUseCase {

    private final ProductPort port;

    @Override
    public void create(Product product) {
        product.setCreatedIn(LocalDateTime.now());
        product.setIsActive(Boolean.FALSE);
        port.save(product);
    }

    @Override
    public void update(Product product) {
        var productExistent = findById(product.getId());
        productExistent.updateProperties(product);
        port.save(productExistent);
    }

    @Override
    public Product findById(Long id) {
        var product = port.findById(id);

        if (Objects.isNull(product))
            throw new ProductException("Product not found.");

        return product;
    }

    @Override
    public void activate(Long id) {
        var product = findById(id);
        product.setIsActive(Boolean.TRUE);
        product.setUpdatedIn(LocalDateTime.now());
        port.save(product);
    }

    @Override
    public void inactivate(Long id) {
        var product = findById(id);
        product.setIsActive(Boolean.FALSE);
        product.setUpdatedIn(LocalDateTime.now());
        port.save(product);
    }

    @Override
    public List<Product> findAll() {
        return port.findAll();
    }

    @Override
    public void increases(Long id, Integer quantityAdditional) {
        var product = findById(id);
        product.increasesQuantity(quantityAdditional);
        product.setUpdatedIn(LocalDateTime.now());
        port.save(product);
    }

    @Override
    public void decreases(Long id, Integer quantityDecreased) {
        var product = findById(id);
        product.decreasesQuantity(quantityDecreased);
        product.setUpdatedIn(LocalDateTime.now());
        port.save(product);
    }
}