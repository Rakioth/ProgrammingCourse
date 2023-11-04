package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, String> {
}