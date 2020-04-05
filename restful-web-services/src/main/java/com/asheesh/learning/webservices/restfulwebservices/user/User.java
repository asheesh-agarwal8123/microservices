package com.asheesh.learning.webservices.restfulwebservices.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="This is user object with all its attributes")
@Entity
public class User {

	@Id
	@GeneratedValue
	@ApiModelProperty(notes="This is system generated unique user id", required=false)
	private Integer id;
	
	@Size(min=2, message="Name should be atleast 2 characters")
	@ApiModelProperty(notes="Name should be atleast 2 characters", required=true)
	private String name;
	
	@Past(message="birthDate should be in past")
	@ApiModelProperty(notes="birthDate should always be in past", required=true)
	private Date birthDate;

	// If not present then Post /Users fail due to unable to map request body
	// params
	public User() {
		super();
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
}
