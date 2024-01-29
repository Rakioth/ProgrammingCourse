package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.enums.CountryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryTypeRepository extends JpaRepository<CountryType, String> {
}