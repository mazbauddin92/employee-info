package com.employee.info.controller;

import com.employee.info.exception.ResourceException;
import com.employee.info.model.Employee;
import com.employee.info.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> allemployee(Model model) {
        List<Employee> employeeList = employeeService.allEmployee();
//        model.addAttribute("employeeList", employeeList);

        return employeeList;
    }

    @PostMapping("/employee")
    public Employee addemployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
//                .orElseThrow(() -> new ResourceException("Employee not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails){
        Employee employee = employeeService.findById(id);
//                .orElseThrow(() -> new ResourceException("Employee not exist with id :" + id));

        employee.setName(employeeDetails.getName());
        employee.setGender(employeeDetails.getGender());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setCity(employeeDetails.getCity());

        Employee updatedEmployee = employeeService.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer id){
        Employee employee = employeeService.findById(id);
//                .orElseThrow(() -> new ResourceException("Employee not exist with id :" + id));

        employeeService.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
