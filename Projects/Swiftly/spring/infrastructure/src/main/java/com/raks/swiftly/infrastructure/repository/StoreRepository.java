package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}