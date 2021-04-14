/*
 * Aditya Jayanti (adityakalyan95@gmail.com)
 */

package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation createCompensation(Compensation compensation) {

        String id = compensation.getEmployeeId();

        // Find EmployeeId from Employee Repository
        Employee employee = employeeRepository.findByEmployeeId(id);

        // Condition to check if employee record does not exist or is invalid
        if(employee==null) {
            throw new RuntimeException("Employee ID is invalid or does not exist");
        }

        // Set Compensation for employee
        compensation.setEmployee(employee);
        compensation.setSalary(compensation.getSalary());
        compensation.setEffectiveDate(compensation.getEffectiveDate());
        return compensationRepository.insert(compensation);
    }

    @Override
    public Compensation readCompensation(String id) {

        if(employeeRepository.findByEmployeeId(id)==null) {
            throw new RuntimeException("Employee ID is invalid");
        }

        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if(compensation==null) {
            throw new RuntimeException("Compensation not found for employee");
        }
        return compensation;
    }
}
