package com.tit.employeepayrollapp.service;

import com.tit.employeepayrollapp.dto.EmployeeDTO;

import com.tit.employeepayrollapp.entity.Employee;
import com.tit.employeepayrollapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    // Convert Employee -> EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        log.debug("Converting Employee Entity to DTO: {}", employee);
        return new EmployeeDTO(employee.getName(), employee.getSalary(), employee.getDepartment());
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        log.debug("Converting EmployeeDTO to Entity: {}", dto);
        return new Employee(null, dto.getName(), dto.getSalary(), dto.getDepartment());
    }


    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return repository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public EmployeeDTO addEmployee(EmployeeDTO dto) {
        log.info("Adding new employee: {}", dto);
        dto.logDTO();
        Employee employee = repository.save(convertToEntity(dto));
        employee.logEmployeeDetails();
        return convertToDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        log.info("Updating employee with ID: {}", id);
        Employee employee = repository.findById(id).orElse(null);

        if (employee != null) {
            log.debug("Before Update: {}", employee);
            employee.setName(dto.getName());
            employee.setSalary(dto.getSalary());
            employee.setDepartment(dto.getDepartment()); // Handling department
            repository.save(employee);
            log.info("Updated Employee: {}", employee);
            return convertToDTO(employee);
        }
        else{
            log.warn("Employee with ID {} not found!", id);
        }

        return null;
    }

    public void deleteEmployee(Long id) {   log.info("Deleting employee with ID: {}", id);
        repository.deleteById(id);
        log.info("Employee deleted successfully."); }
}
