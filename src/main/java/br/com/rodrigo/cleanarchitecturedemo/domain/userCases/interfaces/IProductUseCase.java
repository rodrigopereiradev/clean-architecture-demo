package br.com.rodrigo.cleanarchitecturedemo.domain.userCases.interfaces;

import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;

import java.util.List;

public interface IProductUseCase {
    void create(Product product);

    void update(Product product);

    Product findById(Long id);

    void activate(Long id);

    void inactivate(Long id);

    List<Product> findAll();

    void increases(Long id, Integer quantityAdditional);

    void decreases(Long id, Integer quantityDecreased);
}
