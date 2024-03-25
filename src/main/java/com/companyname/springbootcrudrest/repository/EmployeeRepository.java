package com.companyname.springbootcrudrest.repository;

import com.companyname.springbootcrudrest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
