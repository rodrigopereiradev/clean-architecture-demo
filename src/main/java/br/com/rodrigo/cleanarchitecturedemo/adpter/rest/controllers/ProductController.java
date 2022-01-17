package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.models.dtos.ProductResponseDTO;
import br.com.rodrigo.cleanarchitecturedemo.adpter.models.mappers.ProductMapper;
import br.com.rodrigo.cleanarchitecturedemo.adpter.models.dtos.ProductDTO;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.interfaces.IProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductUseCase useCase;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody ProductDTO productDTO) {
        var product = productMapper.fromDto(productDTO);
        useCase.create(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        var products = useCase.findAll();
        var productsDto = products.stream()
                .map(productMapper::fromProduct)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productsDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findOneById(@PathVariable Long id) {
        if (Objects.isNull(id))
            throw new IllegalArgumentException("The product id is mandatory.");

        var product = useCase.findById(id);

        return ResponseEntity.ok(productMapper.fromProduct(product));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Validated @RequestBody ProductDTO productDTO, @PathVariable Long id) {
        productDTO.setId(id);
        var product = productMapper.fromDto(productDTO);
        useCase.update(product);
        return ResponseEntity.ok().build();
    }


    @PatchMapping(value = "/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        useCase.activate(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/inactivate")
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        useCase.inactivate(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/increases/{quantity}")
    public ResponseEntity<Void> increases(@PathVariable Long id, @PathVariable Integer quantity) {
        useCase.increases(id, quantity);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/decreases/{quantity}")
    public ResponseEntity<Void> decreases(@PathVariable Long id, @PathVariable Integer quantity) {
        useCase.decreases(id, quantity);

        return ResponseEntity.ok().build();
    }

}
