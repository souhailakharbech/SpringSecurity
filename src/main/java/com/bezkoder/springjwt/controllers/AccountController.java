package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;

import java.io.Console;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import javax.mail.MessagingException;
import java.util.List;

import javax.transaction.Transactional;

@Controller
@RequestMapping("accounts")
@Transactional
public class AccountController {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Autowired(required = true)
    private JavaMailSender javaMailSender;

	@Autowired
    public AccountController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
	
	@GetMapping("list")
    public String listUsers(Model model) {
    	
    	List<User> users = (List<User>) userRepository.findAll();
    	long nbr =  userRepository.count();
    	if(users.size()==0)
    		users = null;
        model.addAttribute("users", users);
        model.addAttribute("nbr", nbr);
        return "user/listUsers";
    }
    
	
	@GetMapping("enable/{id}/{email}")
	@ResponseBody
    public User enableUserAcount(@PathVariable ("id") Long id, 
    		@PathVariable ("email") String email) {
    	
		 sendEmail(email, true);
		 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
	     user.setActive(1);
	     return userRepository.save(user);
	   
  
    }
	
	@GetMapping("disable/{id}/{email}")
	@ResponseBody
	public User disableUserAcount(@PathVariable ("id") Long id, 
    		@PathVariable ("email") String email) {
    	
		sendEmail(email, false);
		 
		 User user = userRepository.findById( id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
	     user.setActive(0);
	     return userRepository.save(user);
//    	return "redirect:../../list";
    }

	
//	@PostMapping("updateRole")
//	//@ResponseBody
//	public String UpdateUserRole(@RequestParam ("id") Long id,
//			@RequestParam ("newrole")String newRole
//			) {
//    	
//		 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
//	     
//		 Role userRole = roleRepository.findByName(newRole);
//		 
//	     user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//	     
//	     userRepository.save(user);
//    	return "redirect:list";
//    }
	
	
	  void sendEmail(String email, boolean state) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(email);
	        System.out.println(email);
	        if(state == true)
	        {
	     
	        msg.setSubject("Account Has Been Activated");
	        msg.setText("Hello, Your account has been activated. "
	        		+ 
	        		"You can log in : http://127.0.0.1:81/login"
	        		+ " \n Best Regards!");
	        }
	        else
	        {
	        	msg.setTo(email);
	        	msg.setSubject("Account Has Been disactivated");
	            msg.setText("Hello, Your account has been disactivated.");
	        }
	        javaMailSender.send(msg);

	    }
		
	    
	}