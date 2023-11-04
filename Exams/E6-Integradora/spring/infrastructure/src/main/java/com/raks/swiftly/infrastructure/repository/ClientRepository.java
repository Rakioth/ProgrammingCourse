package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.domain.model.enums.Gender;
import com.raks.swiftly.infrastructure.model.entity.Client;
import com.raks.swiftly.infrastructure.model.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUser_Id(Long id);

    @Query("SELECT c FROM Client c WHERE " +
           "(:surnames is null or :surnames='' or c.surnames LIKE %:surnames%) and " +
           "(:type is null or c.type = :type) and " +
           "(:gender is null or c.gender = :gender) and " +
           "(:startBirthdate is null or c.birthdate >= :startBirthdate) and " +
           "(:endBirthdate is null or c.birthdate <= :endBirthdate) and " +
           "(:startExpenses is null or c.accumulatedExpenses >= :startExpenses) and " +
           "(:endExpenses is null or c.accumulatedExpenses <= :endExpenses)")
    List<Client> findClientsByParams(
            @Param("surnames") String surnames,
            @Param("type") ClientType type,
            @Param("gender") Gender gender,
            @Param("startBirthdate") LocalDate startBirthdate,
            @Param("endBirthdate") LocalDate endBirthdate,
            @Param("startExpenses") BigDecimal startExpenses,
            @Param("endExpenses") BigDecimal endExpenses
    );

}