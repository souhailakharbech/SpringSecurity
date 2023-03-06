package com.bezkoder.springjwt.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bezkoder.springjwt.models.User;

public class SignupRequestClient {

	    private String username;
	 
	   
	    @Email
	    private String email;
	    private String firstname;
	    private String lastname;
	    private String cin;
	    private String numtel;

	    private String password;
	    
	    
	    private String avocat_id;

	    
	    private Set<String> role;
	    
	    private Set<User> user;


//		public SignupRequestClient(String avocat_id,String username, @Email String email, String password) {
//			super();
//			this.avocat_id = avocat_id;
//			this.username = username;
//			this.email = email;
//			
//			this.password = password;
//		
//		
//		}


		public SignupRequestClient() {
			super();
			// TODO Auto-generated constructor stub
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getAvocat_id() {
			return avocat_id;
		}


		public void setAvocat_id(String avocat_id) {
			this.avocat_id = avocat_id;
		}


		public Set<String> getRole() {
			return role;
		}


		public void setRole(Set<String> role) {
			this.role = role;
		}


		public Set<User> getUser() {
			return user;
		}


		public void setUser(Set<User> user) {
			this.user = user;
		}


		public String getFirstname() {
			return firstname;
		}


		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}


		public String getLastname() {
			return lastname;
		}


		public void setLastname(String lastname) {
			this.lastname = lastname;
		}


		public String getCin() {
			return cin;
		}


		public void setCin(String cin) {
			this.cin = cin;
		}


		public String getNumtel() {
			return numtel;
		}


		public void setNumtel(String numtel) {
			this.numtel = numtel;
		}
	    
	    
	    
	    
	    
	   
	
}
