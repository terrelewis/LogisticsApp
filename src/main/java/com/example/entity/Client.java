package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Client {

	@Id
	@GeneratedValue
	public Long ID;
	
	
	public String username, password;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.REMOVE )
	
	private Set<User> user = new HashSet<>();
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE )
	
	private Set<Task> taskowner = new HashSet<>();
	
	@OneToMany(mappedBy="assigned", cascade=CascadeType.REMOVE )
	
	private Set<Task> taskassigned = new HashSet<>();

	

	
	public Set<Task> getTaskowner() {
		return taskowner;
	}

	public void setTaskowner(Set<Task> taskowner) {
		this.taskowner = taskowner;
	}

	public Set<Task> getTaskassigned() {
		return taskassigned;
	}

	public void setTaskassigned(Set<Task> taskassigned) {
		this.taskassigned = taskassigned;
	}

	Client(){
		//JPA
		
	}
	
	public Client(String username, String pass) {
		super();
		
		this.username=username;
		this.password=pass;
	}
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public Set<User> getUser() {
		return user;
	}
	
	public void setUser(Set<User> user) {
		this.user = user;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
