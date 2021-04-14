/*
 * Aditya Jayanti (adityakalyan95@gmail.com)
 */

package com.mindex.challenge.data;

import java.time.LocalDate;

public class Compensation {

    // Properties of Compensation
    private String employeeId;
    private Double salary;
    private LocalDate effectiveDate;
    private Employee employee;

    public Compensation() {
        /* Default Constructor*/
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
