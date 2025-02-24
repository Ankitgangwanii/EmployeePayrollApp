package com.tit.employeepayrollapp.service;

import com.tit.employeepayrollapp.dto.EmployeeDTO;

import com.tit.employeepayrollapp.entity.Employee;
import com.tit.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    // Convert Employee -> EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    // Convert EmployeeDTO -> Employee
    private Employee convertToEntity(EmployeeDTO dto) {
        return new Employee(dto.getName(), dto.getSalary());
    }

    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        return repository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public EmployeeDTO addEmployee(EmployeeDTO dto) {
        Employee employee = repository.save(convertToEntity(dto));
        return convertToDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = repository.findById(id).orElse(null);
        if (employee != null) {
            employee.setName(dto.getName());
            employee.setSalary(dto.getSalary());
            repository.save(employee);
            return convertToDTO(employee);
        }
        return null;
    }

    public void deleteEmployee(Long id) { repository.deleteById(id); }
}
