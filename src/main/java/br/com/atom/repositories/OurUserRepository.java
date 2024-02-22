package br.com.atom.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.atom.entities.OurUsers;

public interface OurUserRepository extends JpaRepository<OurUsers, Integer>{

	Optional<OurUsers> findByEmail(String email);
}
