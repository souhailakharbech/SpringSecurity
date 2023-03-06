package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class Etudiant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "prenom")
	private String prenom;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;
	
	@Column(name = "option")
	private String option;
	
	@Column(name = "classe")
	private String classe;
	
	@Column(name = "NumTel")
	private String NumTel;


	@Column(name = "nomImage")
	private String nomImage;


	public Etudiant() {
	}


	public Etudiant(String name, String prenom, String address, String email, String option, String classe,
			String numTel, String nomImage) {
		super();
		this.name = name;
		this.prenom = prenom;
		this.address = address;
		this.email = email;
		this.option = option;
		this.classe = classe;
		NumTel = numTel;
		this.nomImage = nomImage;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getOption() {
		return option;
	}


	public void setOption(String option) {
		this.option = option;
	}


	public String getClasse() {
		return classe;
	}


	public void setClasse(String classe) {
		this.classe = classe;
	}


	public String getNumTel() {
		return NumTel;
	}


	public void setNumTel(String numTel) {
		NumTel = numTel;
	}


	public String getNomImage() {
		return nomImage;
	}


	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}
	
	
	
	
	
}
