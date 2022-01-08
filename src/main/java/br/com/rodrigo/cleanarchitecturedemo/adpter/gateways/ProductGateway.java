package br.com.rodrigo.cleanarchitecturedemo.adpter.gateways;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.ProductRepository;
import br.com.rodrigo.cleanarchitecturedemo.adpter.models.mappers.ProductMapper;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.Ports.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductGateway implements ProductPort {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public void save(Product product) {
        var productEntity = productMapper.toEntity(product);
        productRepository.save(productEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public boolean productExists(Long id) {
        return false;
    }
}
