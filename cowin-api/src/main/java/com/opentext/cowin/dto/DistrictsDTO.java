package com.opentext.cowin.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistrictsDTO implements Serializable {

	private static final long serialVersionUID = 205L;
	
	@JsonProperty("districts")
	private List<DistrictDTO> districtDTOs;

	public List<DistrictDTO> getDistrictDTOs() {
		return districtDTOs;
	}

	public void setDistrictDTOs(List<DistrictDTO> districtDTOs) {
		this.districtDTOs = districtDTOs;
	}
}
