package com.opentext.cowin.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentsDTO implements Serializable {
	
	private static final long serialVersionUID = 202L;
	
	@JsonProperty("sessions")
	private List<AppointmentDTO> appointmentDTOs;

	public List<AppointmentDTO> getAppointmentDTOs() {
		return appointmentDTOs;
	}

	public void setAppointmentDTOs(List<AppointmentDTO> appointmentDTOs) {
		this.appointmentDTOs = appointmentDTOs;
	}

	
}
