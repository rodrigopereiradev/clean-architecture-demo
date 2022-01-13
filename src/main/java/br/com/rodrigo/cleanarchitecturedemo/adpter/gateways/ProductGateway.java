package br.com.rodrigo.cleanarchitecturedemo.adpter.gateways;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.ProductRepository;
import br.com.rodrigo.cleanarchitecturedemo.adpter.models.mappers.ProductMapper;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Product;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.Ports.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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
    public Product findById(Long id) {
        var productEntity = productRepository.findById(id);

        if (productEntity.isEmpty())
            throw new EntityNotFoundException("Product Entity not found.");

        return productMapper.fromEntity(productEntity.get());
    }

    @Override
    public List<Product> findAll() {
        var products = productRepository.findAll();
        return products.stream().map(productMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }
}
