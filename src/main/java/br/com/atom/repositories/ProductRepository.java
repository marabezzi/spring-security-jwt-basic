package br.com.atom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.atom.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	
}
