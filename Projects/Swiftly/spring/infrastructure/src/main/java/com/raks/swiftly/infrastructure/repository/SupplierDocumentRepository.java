package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.enums.SupplierDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDocumentRepository extends JpaRepository<SupplierDocument, String> {

}