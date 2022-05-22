package br.com.rodrigo.cleanarchitecturedemo.adpter.gateways;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.BrandRepository;
import br.com.rodrigo.cleanarchitecturedemo.adpter.mappers.BrandMapper;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.BrandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandGateway implements BrandPort {

    private final BrandMapper mapper;

    private final BrandRepository repository;

    @Override
    public void save(Brand brand) {
        var brandEntity = mapper.toEntity(brand);
        repository.save(brandEntity);
    }

    @Override
    public void update(Brand brand) {
        var brandEntity = mapper.toEntity(brand);
        repository.update(brandEntity);
    }

    @Override
    public Brand findById(Long id) {
        var brandEntity = repository.findById(id);

        if (brandEntity.isEmpty())
            throw new EntityNotFoundException("Brand Entity not found.");

        return mapper.fromEntity(brandEntity.get());
    }

    @Override
    public List<Brand> findAll() {
         var brandsEntity = repository.findAll();

        return brandsEntity.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

}
