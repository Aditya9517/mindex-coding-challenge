/*
 * Aditya Jayanti (adityakalyan95@gmail.com)
 */


package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationIdUrl;
    private String compensationUrl;
    private String employeeUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
        compensationUrl = "http://localhost:" + port + "/compensation";
    }

    @Test
    public void testCreateReadCompensation() {

        Compensation newCompensation = new Compensation();
        Compensation create;
        Compensation read;

        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        testEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        // Check to see if post request was successful
        assertNotNull(testEmployee);

        newCompensation.setEmployeeId(testEmployee.getEmployeeId());
        newCompensation.setSalary(45678349.5034);
        newCompensation.setEffectiveDate(LocalDate.of(2017, 5, 19));

        // Post request for compensation
        create = restTemplate.postForEntity(compensationUrl, newCompensation, Compensation.class).getBody();

        // Check to see if employee was created with employeeID
        assertNotNull(create.getEmployeeId());

        // Get request for compensation
        read = restTemplate.getForEntity(compensationIdUrl, Compensation.class, create.getEmployeeId()).getBody();

        // Check to see if created salary and obtained salary are the same
        assertEquals(create.getSalary(), read.getSalary());
    }
}
