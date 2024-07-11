package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> getShoppingCartByClient_User_Id(Long id);

}