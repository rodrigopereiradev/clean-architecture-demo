package br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.ProductException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IBrandUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IProductUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.ProductPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ProductUseCase implements IProductUseCase {

    private final ProductPort port;
    private final IBrandUseCase brandUseCase;

    @Override
    public void create(Product product) {
        var brand = brandUseCase.findById(product.getBrand().getId());

        product.configureProductOnCreation(brand);
        port.save(product);

    }

    @Override
    public void update(Product product) {
        var productExistent = findById(product.getId());
        var brand = brandUseCase.findById(product.getBrand().getId());

        productExistent.updateProperties(product, brand);
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
        product.activate();
        port.save(product);
    }

    @Override
    public void inactivate(Long id) {
        var product = findById(id);
        product.inactivate();
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
        port.save(product);
    }

    @Override
    public void decreases(Long id, Integer quantityDecreased) {
        var product = findById(id);
        product.decreasesQuantity(quantityDecreased);
        port.save(product);
    }
}
