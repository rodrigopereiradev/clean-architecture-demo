package br.com.rodrigo.cleanarchitecturedemo.domain.userCases;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.ProductException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.Ports.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUserCase {

    private final ProductPort port;

    public void create(Product product) {
        product.setCreatedIn(LocalDateTime.now());
        port.save(product);
    }


    public void update(Product product) {
        if (!productExists(product.getId()))
            throw new ProductException("Product not found to be updated.");

        product.setUpdatedIn(LocalDateTime.now());
        port.save(product);
    }

    public Product findById(Long id) {
        var product = port.findById(id);

        if (product.isEmpty())
            throw new ProductException("Product not found.");

        return product.get();
    }

    public boolean productExists(Long id) {
        return port.productExists(id);
    }

    public void activate(Long id) {
        var product = findById(id);
        product.setIsActive(Boolean.TRUE);
        product.setUpdatedIn(LocalDateTime.now());
        port.save(product);
    }

    public void inactivate(Long id) {
        var product = findById(id);
        product.setIsActive(Boolean.FALSE);
        product.setUpdatedIn(LocalDateTime.now());
        port.save(product);
    }

    public List<Product> findAll() {
        return port.findAll();
    }
}
