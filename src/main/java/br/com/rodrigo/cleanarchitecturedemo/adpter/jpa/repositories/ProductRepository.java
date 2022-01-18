package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.repositories;

import br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
