package com.tit.employeepayrollapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j

public class EmployeeDTO {
    @NotBlank(message = "Name is required and cannot be null")
    @Pattern(regexp = "^[A-Za-z ]{2,50}$", message = "Name must be between 2-50 characters and contain only letters and spaces")
    private String name;
    private double salary;
    private String department;
    public void logDTO() {
        log.info("EmployeeDTO Created: {}", this);
    }
}
