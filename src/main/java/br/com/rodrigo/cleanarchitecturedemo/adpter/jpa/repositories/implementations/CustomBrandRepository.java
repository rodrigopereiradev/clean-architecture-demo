package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.implementations;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities.BrandEntity;
import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomBrandRepository implements BrandRepository {

    private final EntityManager entityManager;

    @Override
    public void save(BrandEntity brandEntity) {
        entityManager.persist(brandEntity);
    }

    @Override
    public void update(BrandEntity brandEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BrandEntity> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BrandEntity> findAll() {
        throw new UnsupportedOperationException();
    }
}
