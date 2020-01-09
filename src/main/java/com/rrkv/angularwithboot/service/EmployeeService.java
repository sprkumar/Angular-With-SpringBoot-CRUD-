package com.rrkv.angularwithboot.service;

import java.util.List;
import java.util.Optional;

import com.rrkv.angularwithboot.model.Employee;


public interface EmployeeService {

	public List<Employee> getEmployees();

	public Optional<Employee> getEmployeeById(int empid);

	public Employee addNewEmployee(Employee emp);

	public Employee updateEmployee(Employee emp);

	public void deleteEmployeeById(int empid);

	public void deleteAllEmployees();
}
