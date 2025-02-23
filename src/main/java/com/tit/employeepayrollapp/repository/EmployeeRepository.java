package com.tit.employeepayrollapp.repository;


import com.tit.employeepayrollapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}