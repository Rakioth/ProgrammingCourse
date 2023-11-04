package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.domain.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long          id;

    @Column(name = "username", nullable = false, unique = true)
    private String        username;

    @Column(name = "email", nullable = false, unique = true)
    private String        email;

    @Column(name = "password", nullable = false)
    private String        password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role          role;

    @Column(name = "success_auth", nullable = false)
    private Integer       successAuth;

    @Column(name = "failed_auth", nullable = false)
    private Integer       failedAuth;

    @Column(name = "last_connection")
    private LocalDateTime lastConnection;

    @Column(name = "release_block")
    private LocalDateTime releaseBlock;

}