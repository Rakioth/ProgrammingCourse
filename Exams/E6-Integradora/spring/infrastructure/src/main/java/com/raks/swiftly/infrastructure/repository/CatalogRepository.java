package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findBySupplier_Id(Long id);

}