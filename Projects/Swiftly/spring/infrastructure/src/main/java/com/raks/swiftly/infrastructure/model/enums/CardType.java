package com.raks.swiftly.infrastructure.model.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card_types")
public class CardType extends DomainEnums {

}