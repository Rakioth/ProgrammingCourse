package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}