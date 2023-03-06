package com.bezkoder.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "clients",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
})
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idclient;

	private String username;

	private String firstname;

	private String lastname;

	private String cin;
	
	private String numtel;


	private String adress;

	private String birthday;
	@Email
	private String email;

	private String password;

	private int active;
	
	
	private String avocat_id;




	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "Avocat_Client", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "client_id"))
	private Set<User> users = new HashSet<>();


	public Client(Long idclient, String username, String firstname, String lastname, String cin, String numtel,String adress,
			String birthday, @Email String email, String password, int active, Set<Role> roles) {
		super();
		this.idclient = idclient;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.cin = cin;
		this.numtel = numtel;

		this.adress = adress;
		this.birthday = birthday;
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String username, @Email String email, String password, String avocat_id) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.avocat_id = avocat_id;
	}



	public Client(String username, String firstname, String lastname, String cin, String numtel, @Email String email,
			String password, String avocat_id) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.cin = cin;
		this.numtel = numtel;
		this.email = email;
		this.password = password;
		this.avocat_id = avocat_id;
	}

	public Client(Long idclient, String username, @Email String email, String password, User user) {
		super();
		this.idclient = idclient;
		this.username = username;
		this.email = email;
		this.password = password;

	}


	public Client(Long idclient, String username, String firstname, String lastname, String cin, String adress,
			String birthday, @Email String email, String password, int active, String avocat_id, Set<Role> roles,
			Set<User> users) {
		super();
		this.idclient = idclient;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.cin = cin;
		this.adress = adress;
		this.birthday = birthday;
		this.email = email;
		this.password = password;
		this.active = active;
		this.avocat_id = avocat_id;
		this.roles = roles;
		this.users = users;
	}
	

	public Long getIdclient() {
		return idclient;
	}


	public void setIdclient(Long idclient) {
		this.idclient = idclient;
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


	public String getAvocat_id() {
		return avocat_id;
	}


	public void setAvocat_id(String avocat_id) {
		this.avocat_id = avocat_id;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}
	

}
