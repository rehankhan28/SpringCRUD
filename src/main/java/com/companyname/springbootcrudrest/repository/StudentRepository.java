package com.companyname.springbootcrudrest.repository;

import com.companyname.springbootcrudrest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}