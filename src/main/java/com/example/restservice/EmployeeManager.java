package com.example.restservice;
import org.springframework.stereotype.Service;


@Service
public class EmployeeManager {

    private Employees list = new Employees();
    

    {
        Employee emp1 = new Employee("1", "John", "Doe", "john.doe@example.com", "Software Engineer");
        Employee emp2 = new Employee("2", "Jane", "Smith", "jane.smith@example.com", "Project Manager");
        Employee emp3 = new Employee("3", "Alice", "Johnson", "alice.johnson@example.com", "Data Analyst");
        Employee emp4 = new Employee("4", "Bob", "Brown", "bob.brown@example.com", "Product Manager");

        list.getEmployeeList().add(emp1);
        list.getEmployeeList().add(emp2);
        list.getEmployeeList().add(emp3);
        list.getEmployeeList().add(emp4);
    }

    public Employees getAllEmployees() {
        return list;
    }

    public void addEmployee(Employee employee)
    {
        list.getEmployeeList().add(employee);
    }
}

