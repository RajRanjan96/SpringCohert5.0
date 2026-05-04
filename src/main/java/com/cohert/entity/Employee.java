package com.cohert.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name ="employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Long empid;
    private String name;
    private String mail;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;



}
