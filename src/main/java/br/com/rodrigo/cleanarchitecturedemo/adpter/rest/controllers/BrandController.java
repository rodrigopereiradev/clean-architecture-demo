package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.mappers.BrandMapper;
import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.BrandDTO;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IBrandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<BrandDTO>> findall() {
        var brands = useCase.findAll();
        var brandsDto = brands.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(brandsDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BrandDTO> findById(@PathVariable Long id) {
        var brand = useCase.findById(id);
        var brandDto = mapper.toDto(brand);
        return ResponseEntity.ok(brandDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        brandDTO.setId(id);
        var brand = mapper.fromDTO(brandDTO);
        useCase.update(brand);
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

}
