package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.models.mappers.ProductMapper;
import br.com.rodrigo.cleanarchitecturedemo.adpter.models.dtos.ProductDTO;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.interfaces.IProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductUseCase useCase;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ProductDTO productDTO) {
        var product = productMapper.fromDto(productDTO);
        useCase.create(product);
        return ResponseEntity.ok().build();
    }

}
