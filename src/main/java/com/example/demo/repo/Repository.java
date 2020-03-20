package com.example.demo.repo;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByEmplType(Employee.EmployeeType emplType);

}
