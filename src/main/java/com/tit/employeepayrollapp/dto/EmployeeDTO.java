package com.tit.employeepayrollapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;

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

    @NotBlank(message = "Department is required and cannot be empty.")
    private String department;

    @NotBlank(message = "Gender is required and cannot be empty.")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other.")
    private String gender;

    @NotNull(message = "Start date is required.")
    @PastOrPresent(message = "Start date must be in the past or present.")
    @JsonFormat(pattern = "dd MM yyyy")
    private LocalDate startDate;

    @NotBlank(message = "Note cannot be empty.")
    private String note;

    @NotBlank(message = "Profile picture URL is required.")
    private String profilePic;
    public void logDTO() {
        log.info("EmployeeDTO Created: {}", this);
    }
}
