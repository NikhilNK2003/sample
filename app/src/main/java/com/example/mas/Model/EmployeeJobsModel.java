package com.example.mas.Model;

public class EmployeeJobsModel {
    private String employee_id;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_role() {
        return employee_role;
    }

    public void setEmployee_role(String employee_role) {
        this.employee_role = employee_role;
    }

    public EmployeeJobsModel(String employee_id, String employee_role) {
        this.employee_id = employee_id;
        this.employee_role = employee_role;
    }

    private String employee_role;
}
