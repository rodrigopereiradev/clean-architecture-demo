package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.implementations;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities.BrandEntity;
import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        entityManager.merge(brandEntity);
    }

    @Override
    public Optional<BrandEntity> findById(Long id) {

        var criteria = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteria.createQuery(BrandEntity.class);
        var brandRoot = criteriaQuery.from(BrandEntity.class);

        var idPredicate = criteria.equal(brandRoot.get("id"), id);
        criteriaQuery.where(idPredicate);

        var query = entityManager.createQuery(criteriaQuery);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<BrandEntity> findAll() {
        var query = entityManager.createQuery("select b from BrandEntity b");
        return query.getResultList();
    }
}
