package com.raks.psp.example02.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Table(name = "EMP")
@Entity
public class Employee {

    @Id
    @Column(name = "empno")
    private Integer   number;

    @Column(name = "ename")
    private String    name;

    private String    job;

    @Column(name = "mgr")
    private Integer   manager;

    @Column(name = "hiredate")
    private LocalDate hireDate;

    @Column(name = "sal")
    private Float     salary;

    @Column(name = "comm")
    private Float     commision;

    private Integer   deptno;

}
