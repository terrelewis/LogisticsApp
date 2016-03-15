package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Task {
	
	
	@Id
	@GeneratedValue
	public Long internalId;
	
	@JsonIgnore
	@ManyToOne
	public Client owner;
	
	@JsonIgnore
	@ManyToOne
	public Client assigned;	
	
	
	@JsonIgnore
	@ManyToOne
	public Address toAddress;
	
	@JsonIgnore
	@ManyToOne
	public Address fromAddress;
	
	public Long externalId;

	public Task(Client owner, Client assigned, Address toAddress, Address fromAddress,
			Long externalId) {
		super();
		
		this.owner = owner;
		this.assigned = assigned;
		this.toAddress = toAddress;
		this.fromAddress = fromAddress;
		this.externalId = externalId;
	}
	Task()
	{
		//JPA
		
	}

	public Long getInternalId() {
		return internalId;
	}

	public void setInternalId(Long internalId) {
		this.internalId = internalId;
	}

	public Client getOwner() {
		return owner;
	}

	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public Client getAssigned() {
		return assigned;
	}

	public void setAssigned(Client assigned) {
		this.assigned = assigned;
	}

	public Address getToAddress() {
		return toAddress;
	}

	public void setToAddress(Address toAddress) {
		this.toAddress = toAddress;
	}

	public Address getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(Address fromAddress) {
		this.fromAddress = fromAddress;
	}

	public Long getExternalId() {
		return externalId;
	}

	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}
	
	
	
	
	

}
