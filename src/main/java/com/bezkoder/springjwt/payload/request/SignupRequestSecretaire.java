package com.bezkoder.springjwt.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;

import com.bezkoder.springjwt.models.User;

public class SignupRequestSecretaire {
	
	 private String username;
	 
	   
	    @Email
	    private String email;
	   
	    private String password;
	    
	    
	    private String avocat_id;

	    
	    private Set<String> role;
	    
	    private Set<User> user;

		public SignupRequestSecretaire() {
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

		
}
