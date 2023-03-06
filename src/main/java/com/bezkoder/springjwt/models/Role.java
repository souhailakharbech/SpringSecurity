package com.bezkoder.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}
	

	public Role(Integer id, ERole name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Role(ERole name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}