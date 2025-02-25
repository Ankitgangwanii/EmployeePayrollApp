package com.tit.employeepayrollapp.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class EmployeeDTO {
    private String name;
    private double salary;
    private String department;
    public void logDTO() {
        log.info("EmployeeDTO Created: {}", this);
    }
}
