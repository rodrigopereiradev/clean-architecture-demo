package br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports;

import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;

import java.util.List;

public interface BrandPort {

    void save(Brand brand);

    Brand update(Brand brand);

    Brand findById(Long id);

    List<Brand> findAll();

}
