package com.tit.employeepayrollapp.service;

import com.tit.employeepayrollapp.dto.EmployeeDTO;
import com.tit.employeepayrollapp.entity.Employee;
import com.tit.employeepayrollapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        log.info("Fetching all employees");
        List<EmployeeDTO> employees = repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }

    public ResponseEntity<EmployeeDTO> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        Optional<Employee> employee = repository.findById(id);

        return employee.map(emp -> {
            log.debug("Employee found: {}", emp);
            return ResponseEntity.ok(convertToDTO(emp));
        }).orElseGet(() -> {
            log.warn("Employee with ID {} not found!", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        });
    }

    public ResponseEntity<EmployeeDTO> addEmployee(EmployeeDTO dto) {
        log.info("Adding new employee: {}", dto);
        Employee employee = repository.save(convertToEntity(dto));
        log.info("Employee added successfully: {}", employee);
        return new ResponseEntity<>(convertToDTO(employee), HttpStatus.CREATED);
    }

    public ResponseEntity<EmployeeDTO> updateEmployee(Long id, EmployeeDTO dto) {
        log.info("Updating employee with ID: {}", id);
        Optional<Employee> employeeOpt = repository.findById(id);

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            log.debug("Before Update: {}", employee);

            employee.setName(dto.getName());
            employee.setSalary(dto.getSalary());
            employee.setDepartment(dto.getDepartment());

            repository.save(employee);
            log.info("Updated Employee: {}", employee);
            return ResponseEntity.ok(convertToDTO(employee));
        } else {
            log.warn("Employee with ID {} not found!", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.info("Employee deleted successfully.");
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Employee with ID {} not found!", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
