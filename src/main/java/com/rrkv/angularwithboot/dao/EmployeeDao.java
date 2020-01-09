package com.rrkv.angularwithboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rrkv.angularwithboot.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
