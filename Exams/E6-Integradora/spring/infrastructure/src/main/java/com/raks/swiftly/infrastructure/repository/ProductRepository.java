package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT c FROM Product c WHERE " +
           "(:model is null or :model='' or c.model LIKE %:model%) and " +
           "(:minPrice is null or c.price >= :minPrice) and " +
           "(:maxPrice is null or c.price <= :maxPrice) and " +
           "(:onSale is null or c.onSale = :onSale) and " +
           "(:cat is null or :cat='' or c.cat = :cat) and " +
           "(:isNew is null or c.isNew = :isNew)")
    List<Product> findProductsByParams(
            @Param("model") String model,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("onSale") Boolean onSale,
            @Param("isNew") Boolean isNew,
            @Param("cat") String cat
    );

}