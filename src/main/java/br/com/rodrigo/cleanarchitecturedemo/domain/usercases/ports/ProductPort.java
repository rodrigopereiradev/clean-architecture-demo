package br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports;

import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;

import java.util.List;

public interface ProductPort {

    void save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    boolean productExists(Long id);

}
