package com.opentext.cowin.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatesDTO implements Serializable {

	private static final long serialVersionUID = 203L;

	@JsonProperty("states")
	private List<StateDTO> stateDTOs;

	public List<StateDTO> getStateDTOs() {
		return stateDTOs;
	}

	public void setStateDTOs(List<StateDTO> stateDTOs) {
		this.stateDTOs = stateDTOs;
	}
}
