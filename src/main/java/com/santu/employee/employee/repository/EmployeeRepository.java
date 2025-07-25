package com.santu.employee.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santu.employee.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
