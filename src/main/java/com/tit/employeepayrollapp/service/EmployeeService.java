package com.tit.employeepayrollapp.service;

import com.tit.employeepayrollapp.entity.Employee;
import com.tit.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() { return repository.findAll(); }
    public Employee getEmployeeById(Long id) { return repository.findById(id).orElse(null); }
    public Employee addEmployee(Employee employee) { return repository.save(employee); }
    public Employee updateEmployee(Long id, Employee employee) {
        employee.setId(id);
        return repository.save(employee);
    }
    public void deleteEmployee(Long id) { repository.deleteById(id); }
}
