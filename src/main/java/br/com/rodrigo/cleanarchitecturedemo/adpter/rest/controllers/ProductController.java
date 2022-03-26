package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.ProductResponseDTO;
import br.com.rodrigo.cleanarchitecturedemo.adpter.mappers.ProductMapper;
import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.ProductDTO;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductUseCase productUseCase;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody ProductDTO productDTO) {
        var product = productMapper.fromDto(productDTO);
        productUseCase.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        var products = productUseCase.findAll();
        var productsDto = products.stream()
                .map(productMapper::fromProductToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productsDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findOneById(@PathVariable Long id) {
        var product = productUseCase.findById(id);
        return ResponseEntity.ok(productMapper.fromProductToDTO(product));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Validated @RequestBody ProductDTO productDTO, @PathVariable Long id) {
        productDTO.setId(id);
        var product = productMapper.fromDto(productDTO);
        productUseCase.update(product);
        return ResponseEntity.ok().build();
    }


    @PatchMapping(value = "/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        productUseCase.activate(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/inactivate")
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        productUseCase.inactivate(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/increases/{quantity}")
    public ResponseEntity<Void> increases(@PathVariable Long id, @PathVariable Integer quantity) {
        productUseCase.increases(id, quantity);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/decreases/{quantity}")
    public ResponseEntity<Void> decreases(@PathVariable Long id, @PathVariable Integer quantity) {
        productUseCase.decreases(id, quantity);

        return ResponseEntity.ok().build();
    }

}
