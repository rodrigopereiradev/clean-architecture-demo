package br.com.rodrigo.cleanarchitecturedemo.domain.usercases;

import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;

import java.util.List;

public interface IBrandUseCase {

    void create(Brand brand);

    Brand findById(Long id);

    void update(Brand brand);

    List<Brand> findAll();

    void activate(Long id);

    void inactivate(Long id);
}
