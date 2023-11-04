package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.domain.model.enums.OrderState;
import com.raks.swiftly.infrastructure.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByClient_User_Id(Long id);

    @Query("SELECT c FROM Order c WHERE " +
           "(c.client.user.id = :userId) and " +
           "(:startDate is null or c.date >= :startDate) and " +
           "(:endDate is null or c.date <= :endDate) and " +
           "(:orderState is null or c.state = :orderState) and " +
           "(:minPrice is null or c.totalPrice >= :minPrice) and " +
           "(:maxPrice is null or c.totalPrice <= :maxPrice)")
    List<Order> findProductsByParams(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("orderState") OrderState orderState,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );

}