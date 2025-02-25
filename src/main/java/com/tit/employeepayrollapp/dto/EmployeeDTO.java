package com.tit.employeepayrollapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private String name;
    private double salary;
    private String department;
}
