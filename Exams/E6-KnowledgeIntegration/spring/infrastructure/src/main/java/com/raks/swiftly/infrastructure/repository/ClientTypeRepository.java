package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, String> {
}