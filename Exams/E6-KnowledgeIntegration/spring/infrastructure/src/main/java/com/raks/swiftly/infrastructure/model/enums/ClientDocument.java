package com.raks.swiftly.infrastructure.model.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client_documents")
public class ClientDocument extends DomainEnums {
}