package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.enums.SupplierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierTypeRepository extends JpaRepository<SupplierType, String> {
}