package com.tit.employeepayrollapp.controller;

import com.tit.employeepayrollapp.entity.Employee;
import com.tit.employeepayrollapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAllEmployees() { return service.getAllEmployees(); }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) { return service.getEmployeeById(id); }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) { return service.addEmployee(employee); }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) { return service.updateEmployee(id, employee); }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) { service.deleteEmployee(id); }
}