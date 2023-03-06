package com.bezkoder.springjwt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Etudiant;
@Repository

public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {

}
