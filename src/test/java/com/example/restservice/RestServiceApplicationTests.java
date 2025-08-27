package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class RestServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

	@MockBean
	private EmployeeManager employeeManager;

    @Test
    public void testGetEmployees() throws Exception {
        Employees employees = new Employees();
        Employee emp1 = new Employee("1", "John", "Doe", "john.doe@example.com", "Software Engineer");
        Employee emp2 = new Employee("2", "Jane", "Smith", "jane.smith@example.com", "Project Manager");
        employees.getEmployeeList().add(emp1);
        employees.getEmployeeList().add(emp2);

        Mockito.when(employeeManager.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.employeeList").isArray())
                .andExpect(jsonPath("$.employeeList[0].id").value("1"))
                .andExpect(jsonPath("$.employeeList[1].id").value("2"));
    }

    @Test
    public void testAddEmployee() throws Exception {
        Employee newEmployee = new Employee("5", "Sarah", "Connor", "sarah.connor@example.com", "Team Lead");

        Mockito.doNothing().when(employeeManager).addEmployee(Mockito.any(Employee.class));

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}