package com.rrkv.angularwithboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rrkv.angularwithboot.dao.EmployeeDao;
import com.rrkv.angularwithboot.model.Employee;

/**
 * @author ravindra
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public List<Employee> getEmployees() {
		return employeeDao.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(int empid) {
		return employeeDao.findById(empid);
	}

	@Override
	public Employee addNewEmployee(Employee emp) {
		return employeeDao.save(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		return employeeDao.save(emp);
	}

	@Override
	public void deleteEmployeeById(int empid) {
		employeeDao.deleteById(empid);
	}

	@Override
	public void deleteAllEmployees() {
		employeeDao.deleteAll();
	}

}
