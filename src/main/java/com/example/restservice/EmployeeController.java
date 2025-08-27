package com.example.restservice;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class EmployeeController {
    private final EmployeeManager employeeManager;

    public EmployeeController() {
        this.employeeManager = new EmployeeManager(); 
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeManager.getAllEmployees().getEmployeeList();
    }

    @PostMapping("/employees")
    public ResponseEntity<String> addEmployee(@RequestBody Employee newEmployee){
	employeeManager.addEmployee(newEmployee);
	return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
    }
}

