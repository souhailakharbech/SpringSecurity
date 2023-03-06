package com.bezkoder.springjwt.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.OneToMany;

@Entity
@Table(	name = "users", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String firstname;


	private String lastname;

	private String birthday;


	private String country;


	private String zipcode;

	@Email
	private String email;

	private String password;

	private String nomImageidentity;

	private String nomImageselfie;

	private int active;

	private int verifier;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	public User() {

	}

	public User(String username,String email,String password) {
		super();

		this.username = username;
		this.email = email;
		this.password = password;
	}


	public User(Long id, String username, String firstname, String lastname, String birthday, String country,
			String zipcode, @Email String email, String password, String nomImageidentity, String nomImageselfie,
			int active, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.country = country;
		this.zipcode = zipcode;
		this.email = email;
		this.password = password;
		this.nomImageidentity = nomImageidentity;
		this.nomImageselfie = nomImageselfie;
		this.active = active;
		this.roles = roles;
	}

	
	
	public User(Long id, String username, String firstname, String lastname, String birthday, String country,
			String zipcode, @Email String email, String password, String nomImageidentity, String nomImageselfie,
			int active, int verifier, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.country = country;
		this.zipcode = zipcode;
		this.email = email;
		this.password = password;
		this.nomImageidentity = nomImageidentity;
		this.nomImageselfie = nomImageselfie;
		this.active = active;
		this.verifier = verifier;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public String getNomImageidentity() {
		return nomImageidentity;
	}

	public void setNomImageidentity(String nomImageidentity) {
		this.nomImageidentity = nomImageidentity;
	}

	public String getNomImageselfie() {
		return nomImageselfie;
	}

	public void setNomImageselfie(String nomImageselfie) {
		this.nomImageselfie = nomImageselfie;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getVerifier() {
		return verifier;
	}

	public void setVerifier(int verifier) {
		this.verifier = verifier;
	}

}
