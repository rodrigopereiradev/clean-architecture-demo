package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.mappers.BrandMapper;
import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.BrandDTO;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IBrandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandUseCase useCase;
    private final BrandMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody BrandDTO brandDTO) {
        var brand = mapper.fromDTO(brandDTO);
        useCase.create(brand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
