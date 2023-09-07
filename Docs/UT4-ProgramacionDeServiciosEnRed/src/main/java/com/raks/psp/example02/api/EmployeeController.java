package com.raks.psp.example02.api;

import com.raks.psp.example02.data.Employee;
import com.raks.psp.example02.data.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeRepository _employeeRepository;

    @Autowired
    EmployeeController(EmployeeRepository employeeRepository) {
        _employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public Iterable<Employee> getAllEmployees() {
        return _employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer employeeNumber) {
        return ResponseEntity.of(_employeeRepository.findById(employeeNumber));
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return _employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable(value = "id") Integer employeeNumber,
            @Valid @RequestBody Employee employeeDetails) {
        Optional<Employee> employee = _employeeRepository.findById(employeeNumber);

        if (employee.isEmpty())
            return ResponseEntity.notFound().build();

        if (employeeDetails.getSalary() != null)
            employee.get().setSalary(employeeDetails.getSalary());

        final Employee updatedEmployee = _employeeRepository.save(employee.get());
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Integer employeeNumber) {
        Optional<Employee> employee = _employeeRepository.findById(employeeNumber);

        if (employee.isEmpty())
            return ResponseEntity.notFound().build();

        _employeeRepository.deleteById(employee.get().getNumber());
        return ResponseEntity.noContent().build();
    }
}
