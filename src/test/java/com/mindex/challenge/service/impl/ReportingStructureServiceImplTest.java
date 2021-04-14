/*
 * Aditya Jayanti (adityakalyan95@gmail.com)
 */

package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingStructureIdUrl;
    private String employeeUrl;
    private Employee employee1;
    private Employee employee2;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";
        createEmployees();
    }

    @Test
    public void testReportingStructure() {
        // Post request for creating reporting structure
        ReportingStructure createdReportingPost = restTemplate.getForEntity(reportingStructureIdUrl,
                ReportingStructure.class, employee1.getEmployeeId()).getBody();

        // Check to see if an employee was created with employee Id
        assertNotNull(createdReportingPost.getEmployeeId());

        // Check to see if the number of reports between expected and obtained are equal
        assertEquals("1", createdReportingPost.getNumberOfReports());
    }

    private void createEmployees() {
        // array list to hold employees
        List<Employee> employees = new ArrayList<>();

        // Creating two employees for testing
        employee1 = new Employee();
        employee1.setEmployeeId(UUID.randomUUID().toString());
        employee1.setFirstName("John");
        employee1.setLastName("Oliver");
        employee1.setDepartment("Engineering");
        employee1.setPosition("Manager");

        // Post request for employee1
        employee1 = restTemplate.postForEntity(employeeUrl, employee1, Employee.class).getBody();

        employee2 = new Employee();
        employee2.setEmployeeId(UUID.randomUUID().toString());
        employee2.setFirstName("Travis");
        employee2.setLastName("Sam");
        employee2.setDepartment("Engineering 2");
        employee2.setPosition("Developer");

        // Post request for employee 2
        employee2 = restTemplate.postForEntity(employeeUrl, employee2, Employee.class).getBody();

        employees.add(employee2);

        // Employee 2 works under employee1
        employee1.setDirectReports(employees);

        // Post request after updating direct reports list for employee1
        employee1 = restTemplate.postForEntity(employeeUrl, employee1, Employee.class).getBody();
    }
}
