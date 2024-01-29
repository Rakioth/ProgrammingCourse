package com.raks.swiftly.infrastructure.model.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "supplier_types")
public class SupplierType extends DomainEnums {
}