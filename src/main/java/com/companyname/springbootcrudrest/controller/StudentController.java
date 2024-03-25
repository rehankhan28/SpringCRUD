package com.companyname.springbootcrudrest.controller;

import com.companyname.springbootcrudrest.exception.ResourceNotFoundException;
import com.companyname.springbootcrudrest.model.Student;
import com.companyname.springbootcrudrest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
	}

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		return ResponseEntity.ok(students);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws ResourceNotFoundException {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
		return ResponseEntity.ok(student);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent)
			throws ResourceNotFoundException {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
		existingStudent.setName(updatedStudent.getName());
		existingStudent.setRollno(updatedStudent.getRollno());
		Student savedStudent = studentRepository.save(existingStudent);
		return ResponseEntity.ok(savedStudent);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteStudent(@PathVariable Long id) {
		studentRepository.deleteById(id);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Student> updateEmployee(@PathVariable Long id, @RequestBody Student updatedStudent)
			throws ResourceNotFoundException {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

		// Apply partial updates to existingEmployee
		if (updatedStudent.getName() != null) {
			existingStudent.setName(updatedStudent.getName());
		}
		if (updatedStudent.getName() != null) {
			existingStudent.setName(updatedStudent.getName());
		}

		// Save the updated employee
		Student savedEmployee = studentRepository.save(existingStudent);
		return ResponseEntity.ok(savedEmployee);
	}
}