package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@JsonIgnore
	@ManyToOne
	public Client client;	
 
	public User(String first_name, String last_name, String user_name, String mobile_num,
			String default_location, Client client) {
		super();
		
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.mobile_num = mobile_num;
		this.default_location = default_location;
		this.client=client;
	}

	
	
	@Id
	@GeneratedValue
	public Long ID;
	public String first_name, last_name, user_name, mobile_num, default_location;

	User()
	{
		//JPA
		
		
	}
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMobile_num() {
		return mobile_num;
	}

	public void setMobile_num(String mobile_num) {
		this.mobile_num = mobile_num;
	}

	public String getDefault_location() {
		return default_location;
	}

	public void setDefault_location(String default_location) {
		this.default_location = default_location;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

}