package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories;

import br.com.rodrigo.cleanarchitecturedemo.adpter.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
