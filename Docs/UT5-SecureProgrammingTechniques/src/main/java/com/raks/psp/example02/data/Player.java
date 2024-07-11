package com.raks.psp.example02.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer   id;

    @Column
    private String    name;

    @Column
    private LocalDate birthDate;

    @Column
    private String    position;

    @ManyToOne
    private Team      currentTeam;

}