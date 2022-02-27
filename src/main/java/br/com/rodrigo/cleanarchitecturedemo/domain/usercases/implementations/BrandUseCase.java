package br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations;

import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.BrandException;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IBrandUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.BrandPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class BrandUseCase implements IBrandUseCase {

    private final BrandPort port;

    @Override
    public void create(Brand brand) {
        brand.setPropertiesOnCreation();
        port.save(brand);
    }

    @Override
    public Brand findById(Long id) {
        var brand = port.findById(id);

        if (Objects.isNull(brand))
            throw new BrandException("Brand not found.");

        return brand;
    }

    @Override
    public void update(Brand brand) {
        var brandAtDatabase = findById(brand.getId());

        brandAtDatabase.updateProperties(brand);

        port.update(brandAtDatabase);

    }

    @Override
    public List<Brand> findAll() {
        return port.findAll();
    }

    @Override
    public void activate(Long id) {
        var brand = findById(id);
        brand.activate();
        port.update(brand);
    }

    @Override
    public void inactivate(Long id) {
        var brand = findById(id);
        brand.inactivate();
        port.update(brand);
    }
}
