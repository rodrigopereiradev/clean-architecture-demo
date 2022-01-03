package br.com.rodrigo.cleanarchitecturedemo.domain.userCases.Ports;

import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductPort {

    void save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void inactivate(Long id);

    void activated(Long id);

}
