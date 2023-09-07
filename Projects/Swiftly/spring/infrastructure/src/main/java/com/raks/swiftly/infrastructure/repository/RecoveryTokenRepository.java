package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.entity.RecoveryToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecoveryTokenRepository extends JpaRepository<RecoveryToken, String> {

    Optional<RecoveryToken> findByUser_Email(String email);

}