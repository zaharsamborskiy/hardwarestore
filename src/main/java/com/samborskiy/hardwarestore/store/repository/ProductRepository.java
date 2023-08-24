package com.samborskiy.hardwarestore.store.repository;

import com.samborskiy.hardwarestore.store.model.Product;
import com.samborskiy.hardwarestore.store.model.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    List<Product> findAllByShowcase(Showcase id);
}
