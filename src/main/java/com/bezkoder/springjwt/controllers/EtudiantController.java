package com.bezkoder.springjwt.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.Etudiant;
import java.util.Optional;
import java.util.Random;

import com.bezkoder.springjwt.repository.EtudiantRepository;

@RestController
@RequestMapping({ "/etudiants", "/hom*" })
@CrossOrigin(origins = "*")

public class EtudiantController {

	
	private final Path root = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/uploads");

	@Autowired
	private EtudiantRepository etudiantRepository;

	@GetMapping("/list")
	public List<Etudiant> getAllEtudiants() {
		return (List<Etudiant>) etudiantRepository.findAll();
	}

	@PostMapping("/add")
	public Etudiant uplaodImage(@RequestParam("imageFile") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("prenom") String prenom,@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("option") String option,@RequestParam("classe") String classe,@RequestParam("numTel") String numTel,@RequestParam("imageName") String imageName) throws IOException {

		String newImageName = getSaltString().concat(file.getOriginalFilename());
		try {
			Files.copy(file.getInputStream(), this.root.resolve(newImageName));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

		Etudiant etudiant = new Etudiant(name,prenom, address, email,option,classe,numTel, newImageName);

		etudiantRepository.save(etudiant);

		return etudiant;
	}

	@PutMapping("/{etudiantId}")
	public Etudiant updateEtudiant(@PathVariable Long etudiantId, 
			
			@RequestParam("imageFile") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("prenom") String prenom,@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("option") String option,@RequestParam("classe") String classe,@RequestParam("numTel") String numTel,@RequestParam("imageName") String imageName
			//@Valid @RequestBody Provider providerRequest
			
			
			) {
		return etudiantRepository.findById(etudiantId).map(etudiant -> {
			
			// STEP 1 : delete Old Image from server
			String OldImageName = etudiant.getNomImage();
					
				////////
					try {
						File f = new File(this.root + "/" + OldImageName); // file to be delete
						if (f.delete()) // returns Boolean value
						{
							System.out.println(f.getName() + " deleted"); // getting and printing the file name
						} else {
							System.out.println("failed");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

			 /////// END STEP 1
			
			/// STEP 2 : Upload new image to server
			String newImageName = getSaltString().concat(file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), this.root.resolve(newImageName));
			} catch (Exception e) {
				throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			}
			/// END STEP 2
			
			
			
			etudiant.setName(name);
			etudiant.setPrenom(prenom);
			etudiant.setEmail(email);
			etudiant.setAddress(address);
			etudiant.setClasse(classe);
			etudiant.setOption(option);
			etudiant.setNomImage(newImageName);
			return etudiantRepository.save(etudiant);
		}).orElseThrow(() -> new IllegalArgumentException("etudiantId " + etudiantId + " not found"));
	}

	@DeleteMapping("/{etudiantId}")
	public ResponseEntity<?> deleteEtudiant(@PathVariable Long etudiantId) {
		return etudiantRepository.findById(etudiantId).map(etudiant -> {
			etudiantRepository.delete(etudiant);

			////////
			try {
				File f = new File(this.root + "/" + etudiant.getNomImage()); // file to be delete
				if (f.delete()) // returns Boolean value
				{
					System.out.println(f.getName() + " deleted"); // getting and printing the file name
				} else {
					System.out.println("failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			///////

			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new IllegalArgumentException("etudiantId " + etudiantId + " not found"));
	}

	@GetMapping("/{providerId}")
	public Etudiant getEtudiant(@PathVariable Long etudiantId) {

		Optional<Etudiant> p = etudiantRepository.findById(etudiantId);

		return p.get();

	}

	// rundom string to be used to the image name
	protected static String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
	
}
