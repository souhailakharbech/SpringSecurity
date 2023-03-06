package com.bezkoder.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Client;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SignupRequestClient;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.ClientRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client")
@Transactional
public class ClientController {
	@Autowired
    ClientRepository clientRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	

	@Autowired
	RoleRepository roleRepository;
	

//	@GetMapping(path = "/allclient") Client AfficherClient(){
//		  return (Client) clientRepository.findAll();
//		}
	
	@CrossOrigin(origins="*")

	@GetMapping("/list")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
	
	@CrossOrigin(origins = "*", maxAge = 3600)

	@PostMapping("/signupClient")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestClient signupRequestClient) {
		
		
		if (clientRepository.existsByUsername(signupRequestClient.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (clientRepository.existsByEmail(signupRequestClient.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new client's account
		Client client = new Client(signupRequestClient.getUsername(),
				signupRequestClient.getFirstname(),	signupRequestClient.getLastname(),
				signupRequestClient.getCin(),signupRequestClient.getNumtel(),	signupRequestClient.getEmail(), encoder.encode(signupRequestClient.getPassword()),signupRequestClient.getAvocat_id()
						);

		Set<String> strRolesClient = signupRequestClient.getRole();
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

    	client.setRoles(roles);
    	clientRepository.save(client);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)

	@PutMapping("/{clientId}")
    public Client updateClient(@PathVariable Long clientId, @Valid @RequestBody Client clientRequest) {
        return clientRepository.findById(clientId).map(client -> {
        	client.setUsername(clientRequest.getUsername());
        	client.setFirstname(clientRequest.getFirstname());
        	client.setLastname(clientRequest.getLastname());
        	client.setCin(clientRequest.getCin());
        	client.setNumtel(clientRequest.getNumtel());
        	client.setEmail(clientRequest.getEmail());
        	client.setPassword(encoder.encode(clientRequest.getPassword()));
        	return clientRepository.save(client);
        }).orElseThrow(() -> new IllegalArgumentException("ProviderId " + clientId + " not found"));
    }

	
	@DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        return clientRepository.findById(clientId).map(client -> {
        	clientRepository.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("clientId " + clientId + " not found"));
    }

	
	
}
