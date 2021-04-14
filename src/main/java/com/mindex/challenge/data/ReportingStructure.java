/*
 * Aditya Jayanti (adityakalyan95@gmail.com)
 */

package com.mindex.challenge.data;

public class ReportingStructure {

    // Properties of Reporting Structure
    private String employeeId;
    private String employeeName;
    private String numberOfReports;

    public ReportingStructure() {/* default constructor*/}

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(String numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}