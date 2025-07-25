package com.santu.employee.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santu.employee.employee.model.Employee;
import com.santu.employee.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository= employeeRepository;
	}
	@Override
	public Employee saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}
	@Override
	public List<Employee> getAllEmloyee() {
		return employeeRepository.findAll();
	}
	@Override
	public Employee getEmloyeeById(Long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee not found, Burra!"));
	}
	@Override
	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		Employee employee= getEmloyeeById(id);
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());
		employee.setSalary(updatedEmployee.getSalary());
		employee.setDate(updatedEmployee.getDate());
		employeeRepository.save(employee);
		return employee;
	}
	@Override
	public void deleteEmployee(List<Long> ids) {
		// TODO Auto-generated method stub
		employeeRepository.deleteAllById(ids);
	}

	
}
