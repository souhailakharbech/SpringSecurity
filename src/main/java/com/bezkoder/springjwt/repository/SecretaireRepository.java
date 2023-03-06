package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.Secretaire;


public interface SecretaireRepository  extends JpaRepository<Secretaire, Long>{
	
	
	Optional<Secretaire> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
