package com.rrkv.angularwithboot.contrller;

import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rrkv.angularwithboot.model.Employee;
import com.rrkv.angularwithboot.service.EmployeeService;

/**
 * @author ravindra
 *
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	@PostMapping(path = "/add")
	public ResponseEntity<?> addEmplyee(@RequestBody Employee employee) {
		logger.info("inside addEmplyee() " + employee);
		try {
			employeeService.addNewEmployee(employee);
			return new ResponseEntity<>(Collections.singletonMap("message", "Employee added succefully"),
					HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/allEmployee")
	public ResponseEntity<?> getEmployees() {
		logger.info("inside getEmployees() ");
		try {
			return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id) throws Exception {
		logger.info("inside getEmployees() " + id);
		try {
			Optional<Employee> emp = employeeService.getEmployeeById(id);
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee updemp, @PathVariable int id) throws Exception {
		logger.info("inside updateEmployee() " + id);
		try {
			Optional<Employee> emp = employeeService.getEmployeeById(id);
			if (!emp.isPresent())
				throw new Exception("Could not find employee with id- " + id);

			if (updemp.getName() == null || updemp.getName().isEmpty())
				updemp.setName(emp.get().getName());
			if (updemp.getDepartment() == null || updemp.getDepartment().isEmpty())
				updemp.setDepartment(emp.get().getDepartment());
			if (updemp.getEmail() == null || updemp.getEmail().isEmpty())
				updemp.setEmail(emp.get().getEmail());
			if (updemp.getSalary() == 0)
				updemp.setSalary(emp.get().getSalary());

			updemp.setId(id);
			employeeService.updateEmployee(updemp);
			return new ResponseEntity<>(Collections.singletonMap("message", "update succefully"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "updated succefully"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/deletebyid/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable int id) {
		logger.info("inside deleteEmployeeById() ");
		try {
			employeeService.deleteEmployeeById(id);
			return new ResponseEntity<>(Collections.singletonMap("message", "Employee deleted succefully"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/deleteAll")
	public ResponseEntity<?> deleteAll() {
		logger.info("inside deleteAll() ");
		try {
			employeeService.deleteAllEmployees();
			return new ResponseEntity<>(Collections.singletonMap("message", "Employee deleted succefully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
}
