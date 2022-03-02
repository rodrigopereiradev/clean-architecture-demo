package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities.BrandEntity;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {

    void save(BrandEntity brandEntity);

    void update(BrandEntity brandEntity);

    Optional<BrandEntity> findById(Long id);

    List<BrandEntity> findAll();
}
