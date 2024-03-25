package com.companyname.springbootcrudrest.controller;

import com.companyname.springbootcrudrest.exception.ResourceNotFoundException;
import com.companyname.springbootcrudrest.model.Student;
import com.companyname.springbootcrudrest.model.Test;
import com.companyname.springbootcrudrest.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/app/tests")
@RestController
public class TestController {
    @Autowired
    TestRepository testRepository;
    @PostMapping
    public ResponseEntity<Test> createStudent(@RequestBody Test test) {
        Test saved = testRepository.save(test);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Read
    @GetMapping
    public ResponseEntity<List<Test>> getAllStudents() {
        List<Test> students = testRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getStudentById(@PathVariable Long id) throws ResourceNotFoundException {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return ResponseEntity.ok(test);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Test> updateStudent(@PathVariable Long id, @RequestBody Test updatedStudent) throws ResourceNotFoundException {
        Test existingTest = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        existingTest.setName(updatedStudent.getName());
        existingTest.setAddress(updatedStudent.getAddress());

        Test savedTest = testRepository.save(existingTest);
        return ResponseEntity.ok(savedTest);
    }

    // Delete
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable Long id) {
        testRepository.deleteById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Test> updateEmployee(@PathVariable Long id, @RequestBody Student updatedStudent) throws ResourceNotFoundException {
        Test existingStudent = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        // Apply partial updates to existingEmployee
        if (updatedStudent.getName() != null) {
            existingStudent.setName(updatedStudent.getName());
        }
        if (updatedStudent.getName() != null) {
            existingStudent.setName(updatedStudent.getName());
        }

        // Save the updated employee
        Test savedTest = testRepository.save(existingStudent);
        return ResponseEntity.ok(savedTest);
    }
}
