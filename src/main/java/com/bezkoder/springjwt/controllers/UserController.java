package com.bezkoder.springjwt.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import  java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.messages.ResponseMessage;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.FilesStorageService;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;


@RestController
@RequestMapping("/Users")
@CrossOrigin(origins="*")
@Transactional
public class UserController {

	private final Path root = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/uploads");

	
	@Autowired

	private FilesStorageService storageService;

	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin(origins="*")

	@GetMapping("/list")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
	
	@GetMapping(path = "/avocat/{avocat_id}")
	public ResponseEntity<User> findById(@PathVariable long avocat_id){
	  return userRepository.findById(avocat_id)
	          .map(record -> ResponseEntity.ok().body(record))
	          .orElse(ResponseEntity.notFound().build());
	}
	
	
	@CrossOrigin(origins="*")
	@PutMapping("/cmprofile/{UserId}")
	public User uploadFiles(@PathVariable Long UserId,
			@RequestParam("imageFile") MultipartFile file , 
			@RequestParam("imageFile1") MultipartFile file1 ,
			     @RequestParam ("firstname") String firstname,
			     @RequestParam ("lastname") String lastname,
			     @RequestParam ("birthday") String birthday,
			     @RequestParam ("country") String country,
			     @RequestParam ("zipcode") String zipcode,
				  @RequestParam("imageName") String imageName,
			     @RequestParam("imageName1") String imageName1


) {
		return userRepository.findById(UserId).map(user -> {
			
			// STEP 1 : delete Old Image from server
			String OldImageName = user.getNomImageidentity();
			String OldImageName1 = user.getNomImageselfie();

					
				////////
					try {
						File f = new File(this.root + "/" + OldImageName); 
						File f1 = new File(this.root + "/" + OldImageName1);// file to be delete
						if (f.delete()&&f1.delete()) // returns Boolean value
						{
							System.out.println(f.getName()  + " deleted"); // getting and printing the file name
							System.out.println(f1.getName()  + " deleted"); // getting and printing the file name

						} else {
							System.out.println("failed");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

			 /////// END STEP 1
			
			/// STEP 2 : Upload new image to server
			String newImageName =  getSaltString().concat(file.getOriginalFilename());
			String newImageName1 = getSaltString().concat(file1.getOriginalFilename());

			try {
				Files.copy(file.getInputStream(), this.root.resolve(newImageName));
				Files.copy(file1.getInputStream(), this.root.resolve(newImageName1));

			} catch (Exception e) {
				throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			}
			/// END STEP 2
			
			
			
			user.setBirthday(birthday);
			user.setCountry(country);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setNomImageidentity(newImageName);
			user.setNomImageselfie(newImageName1);
			user.setZipcode(zipcode);


			return userRepository.save(user);
		}).orElseThrow(() -> new IllegalArgumentException("UserId " + UserId + " not found"));
	   
	  }
	
	@CrossOrigin(origins="*")
	 @DeleteMapping("/{RecuId}")
		public ResponseEntity<?> deleteUser(@PathVariable Long UserId) {
			return userRepository.findById(UserId).map(user -> {
				userRepository.delete(user);

				////////
				try {
					File f1 = new File(this.root + "/" + user.getNomImageidentity()); // file to be delete
					File f2 = new File(this.root + "/" + user.getNomImageselfie());
					if (f1.delete() && f2.delete()) // returns Boolean value
					{
						System.out.println(f1.getName() + " deleted"); // getting and printing the file name
					} else {
						System.out.println("failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				///////

				return ResponseEntity.ok().build();
			}).orElseThrow(() -> new IllegalArgumentException("UserId " + UserId + " not found"));
		}
	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {

		Optional<User> p = userRepository.findById(userId);

		return p.get();

	}
	public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }/*from  w  w  w .ja v a2 s . c om*/
        String saltStr = salt.toString();
        return saltStr;

    }

}
