package com.bezkoder.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

@Entity
@Table(name = "secretaires",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
})
public class Secretaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String firstname;


	private String lastname;

	private String cin;

	private String adress;


	private String birthday;


	private String country;


	private String zipcode;



	@Email
	private String email;

	private String password;
	
	private String avocat_id;



	private int active;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "Avocat_Secretaire", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "secretaire_id"))
	private Set<User> users = new HashSet<>();

	
	
	
	
	




	



	public Secretaire() {
		super();
		// TODO Auto-generated constructor stub
	}







	public Secretaire(Long id, String username, String firstname, String lastname, String cin, String adress,
			String birthday, String country, String zipcode, @Email String email, String password, String avocat_id,
			int active, Set<Role> roles, Set<User> users) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.cin = cin;
		this.adress = adress;
		this.birthday = birthday;
		this.country = country;
		this.zipcode = zipcode;
		this.email = email;
		this.password = password;
		this.avocat_id = avocat_id;
		this.active = active;
		this.roles = roles;
		this.users = users;
	}
	
	
	
	



	public Secretaire(String username, @Email String email, String password, String avocat_id) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.avocat_id = avocat_id;
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
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}




	public Set<User> getUsers() {
		return users;
	}




	public void setUsers(Set<User> users) {
		this.users = users;
	}







	public String getAvocat_id() {
		return avocat_id;
	}







	public void setAvocat_id(String avocat_id) {
		this.avocat_id = avocat_id;
	}

	
	
}
