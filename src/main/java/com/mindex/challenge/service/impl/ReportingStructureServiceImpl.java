/*
 * Aditya Jayanti (adityakalyan95@gmail.com)
 */

package com.mindex.challenge.service.impl;

import ch.qos.logback.classic.Logger;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure readStructure(String id) {
        LOG.debug("Read employee structure with id [{}] --Implementation", id);
        // Create an instance of reporting structure to access getters/setters
        ReportingStructure reportingStructure = new ReportingStructure();

        //Extract employee information from employee repository
        Employee employee = employeeRepository.findByEmployeeId(id);

        // Set current employee Id (parameter) in structure
        reportingStructure.setEmployeeId(id);

        // Set current employee name (parameter) in structure
        reportingStructure.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());

        reportingStructure.setNumberOfReports(Integer.toString(generateDirectReports(employee)));
        return reportingStructure;
    }

    private int generateDirectReports(Employee employee) {
        // Initialize list - number of directs reports for an employee Id
        List<Employee> directReports = employee.getDirectReports();
        if(directReports==null) {
            return 0;
        }
        // Number of directs is equal to the size of a list at minimum
        int numberOfDirectReports = directReports.size();

        for(int i=0; i < directReports.size(); i++) {
            // Find direct reports of current employee Id
            String current = directReports.get(i).getEmployeeId();
            employee.getDirectReports().set(i, employeeRepository.findByEmployeeId(current));

            // Update the number of direct reports by adding recursively
            numberOfDirectReports += generateDirectReports(employee.getDirectReports().get(i));
        }
        return numberOfDirectReports;
    }
}