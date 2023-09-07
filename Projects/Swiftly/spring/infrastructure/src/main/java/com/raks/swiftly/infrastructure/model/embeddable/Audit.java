package com.raks.swiftly.infrastructure.model.embeddable;

import com.raks.swiftly.infrastructure.model.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class Audit {

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "admin_creation")
    private User adminCreation;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @ManyToOne
    @JoinColumn(name = "admin_modification")
    private User adminModification;

    @Column(name = "deletion_date")
    private LocalDateTime deletionDate;

    @ManyToOne
    @JoinColumn(name = "admin_deletion")
    private User adminDeletion;

}