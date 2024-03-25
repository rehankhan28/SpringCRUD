package com.companyname.springbootcrudrest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.springbootcrudrest.exception.ResourceNotFoundException;
import com.companyname.springbootcrudrest.model.Product;
import com.companyname.springbootcrudrest.repository.ProductRepository;

import jakarta.validation.Valid;

@RequestMapping("/app")
@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getUserById(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		return ResponseEntity.ok().body(product);
	}

	@PostMapping("/products")
	public Product createUser(@Valid @RequestBody Product employee) {
		return productRepository.save(employee);
	}

//	@PatchMapping("/{id}")
//	public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
//		Product existingProduct = productRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
//
//		// Update only non-null properties of the updatedEmployee
//		BeanUtils.copyProperties(updatedProduct, existingProduct, "eid");
//		return productRepository.save(existingProduct);
//	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
		Product product = productRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));

		product.setPname(productDetails.getPname());
		product.setPrice(productDetails.getPrice());

		final Product updatedEmployee = productRepository.save(product);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/products/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Product product = productRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}