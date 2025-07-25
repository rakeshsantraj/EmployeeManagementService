package com.santu.employee.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santu.employee.employee.model.Employee;
import com.santu.employee.employee.repository.EmployeeRepository;
import com.santu.employee.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService= employeeService;
	}
	
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEMployee(){
		return employeeService.getAllEmloyee();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") Long id) {
		return employeeService.getEmloyeeById(id);
	}
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee existingEmployee) {
		return employeeService.updateEmployee(id, existingEmployee);
	}
	
	@DeleteMapping("employee")
	public void deleteEmployee(@RequestBody List<Long> ids) {
		employeeService.deleteEmployee(ids);;
	}
}
