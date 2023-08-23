package com.samborskiy.hardwarestore.store.repository;

import com.samborskiy.hardwarestore.store.model.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShowcaseRepository extends JpaRepository<Showcase, UUID> {
}
