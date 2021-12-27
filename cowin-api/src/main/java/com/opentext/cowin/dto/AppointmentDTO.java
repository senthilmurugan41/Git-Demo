package com.opentext.cowin.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO implements Serializable {
	
	private static final long serialVersionUID = 201L;

	@JsonProperty("center_id")
	private int centerId;
	
	@JsonProperty("name")
	private String centerName;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("district_name")
	private String districtName;
	
	@JsonProperty("pincode")
	private int pincode;
	
	@JsonProperty("available_capacity_dose1")
	private int availableCapacityDose1;
	
	@JsonProperty("available_capacity_dose2")
	private int availableCapacityDose2;
	
	@JsonProperty("vaccine")
	private String vaccine;
	
	@JsonProperty("slots")
	private List<String> slots;

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getAvailableCapacityDose1() {
		return availableCapacityDose1;
	}

	public void setAvailableCapacityDose1(int availableCapacityDose1) {
		this.availableCapacityDose1 = availableCapacityDose1;
	}

	public int getAvailableCapacityDose2() {
		return availableCapacityDose2;
	}

	public void setAvailableCapacityDose2(int availableCapacityDose2) {
		this.availableCapacityDose2 = availableCapacityDose2;
	}

	public String getVaccine() {
		return vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public List<String> getSlots() {
		return slots;
	}

	public void setSlots(List<String> slots) {
		this.slots = slots;
	}
}
