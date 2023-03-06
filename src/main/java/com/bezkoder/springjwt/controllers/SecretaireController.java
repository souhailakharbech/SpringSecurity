package com.bezkoder.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Client;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.Secretaire;
import com.bezkoder.springjwt.payload.request.SignupRequestSecretaire;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.SecretaireRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/secretaire")
@Transactional
public class SecretaireController {
	
	@Autowired
	SecretaireRepository secretaireRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	

	@Autowired
	RoleRepository roleRepository;
	

	@GetMapping(path = "/allsecretaire") Secretaire AfficherSecretaire(){
		  return (Secretaire) secretaireRepository.findAll();
		}
	@GetMapping("/list")
    public List<Secretaire> getAllSecretaire() {
        return secretaireRepository.findAll();
    }
	@CrossOrigin(origins = "*", maxAge = 3600)

	@PostMapping("/signupsecretaire")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestSecretaire signupRequestSecretaire) {
		
		
		if (secretaireRepository.existsByUsername(signupRequestSecretaire.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (secretaireRepository.existsByEmail(signupRequestSecretaire.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new client's account
		Secretaire secretaire = new Secretaire(signupRequestSecretaire.getUsername(),
				signupRequestSecretaire.getEmail(),
							 encoder.encode(signupRequestSecretaire.getPassword()),signupRequestSecretaire.getAvocat_id()
						);

		Set<String> strRolesClient = signupRequestSecretaire.getRole();
		Set<Role> roles = new HashSet<>();
 
		if (strRolesClient == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
		strRolesClient.forEach(role -> {
				switch (role) {
			
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		secretaire.setRoles(roles);
    	secretaireRepository.save(secretaire);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	
	

}
