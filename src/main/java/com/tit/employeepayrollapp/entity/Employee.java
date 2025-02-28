package com.tit.employeepayrollapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required and cannot be null")
    @Pattern(regexp = "^[A-Za-z ]{2,50}$", message = "Name must be between 2-50 characters and contain only letters and spaces")
    private String name;

    private double salary;
    private String department;
    private String gender;
    private Date startDate;
    private String note;
    private String profilePic;
    public void logEmployeeDetails() {
        log.info("Employee Created: {}", this);
    }

}