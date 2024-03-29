package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.enums.ViaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaTypeRepository extends JpaRepository<ViaType, String> {
}