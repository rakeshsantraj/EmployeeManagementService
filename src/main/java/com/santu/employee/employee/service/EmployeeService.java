package com.santu.employee.employee.service;

import java.util.List;

import com.santu.employee.employee.model.Employee;

public interface EmployeeService {
	public Employee saveEmployee(Employee employee);
	public List<Employee> getAllEmloyee();
	public Employee getEmloyeeById(Long id);
	public Employee updateEmployee(Long id, Employee updatedEmployee);
	public void deleteEmployee(List<Long> ids);
}
