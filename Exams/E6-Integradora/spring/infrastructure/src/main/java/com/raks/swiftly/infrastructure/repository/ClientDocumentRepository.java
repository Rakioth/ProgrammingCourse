package com.raks.swiftly.infrastructure.repository;

import com.raks.swiftly.infrastructure.model.enums.ClientDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDocumentRepository extends JpaRepository<ClientDocument, String> {
}