package br.com.atom.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.atom.dtos.RequestResponse;
import br.com.atom.entities.Product;
import br.com.atom.repositories.ProductRepository;

@RestController
public class AdminUsers {

	private ProductRepository productRepository;

	public AdminUsers(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping("/public/product")
	public ResponseEntity<Object> getAllProduct() {
		return ResponseEntity.ok(productRepository.findAll());
    }
	
	@PostMapping("/admin/saveproduct")
	public ResponseEntity<Object> signUp(@RequestBody RequestResponse productRequest){
		Product productToSave = new Product();
		productToSave.setName(productRequest.getName());
		return ResponseEntity.ok(productRepository.save(productToSave));
	}
	
	@GetMapping("/user/alone")
	public ResponseEntity<Object> getAlone() {
		return ResponseEntity.ok("User alone can access this API only");
    }
	
	@GetMapping("/adminuser/both")
	public ResponseEntity<Object> bothAdminAndUserApi() {
		return ResponseEntity.ok("Both Admin and Users Can alone access the API ");
    }
	
}
