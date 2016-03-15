package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Address {
	
	@Id
	@GeneratedValue
	public Long addressId;
	
	public Double lati, longi;
	
	public String locality, subLocality, postalCode, buildingNo, doorNo;
	
	public Long homebaseId; //id of the client for which this address is the homebase for

	public Address(String locality, String subLocality, String postalCode, String buildingNo,
			String doorNo, Long homebaseId, Double lati, Double longi) {
		super();
		
		this.locality = locality;
		this.subLocality = subLocality;
		this.postalCode = postalCode;
		this.buildingNo = buildingNo;
		this.doorNo = doorNo;
		this.homebaseId = homebaseId;
		this.lati=lati;
		this.longi=longi;
	}
	Address()
	{
		//Address
		
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getSubLocality() {
		return subLocality;
	}

	public void setSubLocality(String subLocality) {
		this.subLocality = subLocality;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public long getHomebaseId() {
		return homebaseId;
	}

	public Double getLati() {
		return lati;
	}

	public void setLati(Double lati) {
		this.lati = lati;
	}

	public Double getLongi() {
		return longi;
	}

	public void setLongi(Double longi) {
		this.longi = longi;
	}

	public void setHomebaseId(Long homebaseId) {
		this.homebaseId = homebaseId;
	}
	
	
	
	

	

}
