package com.companyname.springbootcrudrest.repository;

import com.companyname.springbootcrudrest.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Long> {
}
