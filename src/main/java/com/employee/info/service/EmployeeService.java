package com.employee.info.service;

import com.employee.info.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> allEmployee();
    Employee save(Employee employee);
    Employee findById(Integer id);
    void delete(Employee employee);
}
