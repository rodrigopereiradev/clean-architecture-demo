package br.com.rodrigo.cleanarchitecturedemo.domain.userCases;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.ProductException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.Ports.ProductPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProductUserCase {

    private final ProductPort port;

    public void create(Product product) {
        product.setCreatedIn(LocalDateTime.now());
        port.save(product);
    }


    public void update(Product product) {
        product.setUpdatedIn(LocalDateTime.now());
        port.save(product);
    }

    public Product findById(Long id) {
        var product = port.findById(id);

        if (product.isEmpty())
            throw new ProductException("Product not found.");

        return product.get();
    }
}
