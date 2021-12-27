package com.opentext.cowin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.cowin.dto.CoWINDTO;
import com.opentext.cowin.dto.DistrictsDTO;
import com.opentext.cowin.dto.ErrorDTO;
import com.opentext.cowin.dto.StatesDTO;
import com.opentext.cowin.exception.CowinException;
import com.opentext.cowin.service.CoWINService;


@Service
public class CoWINServiceImpl implements CoWINService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${connect.api.baseUrl}")
	private String baseUrl;

	@Override
    public ResponseEntity<String> getOTP(CoWINDTO coWINDTO) throws CowinException {
		ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
		try {
			requestBody = objectMapper.writeValueAsString(coWINDTO);
			HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.set("accept", "application/json");
	        httpHeaders.set("Content-Type", "application/json");
	        httpHeaders.set("user-agent", "Application");
	        String url = baseUrl + "v2/auth/public/generateOTP";
	        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
	        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		} catch (JsonProcessingException e) {
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode("500100");
			errorDTO.setMessage("Server Error");
			throw new CowinException(errorDTO);
		}
    }

	@Override
	public StatesDTO  getStates() throws CowinException {
		String url = baseUrl+"v2/admin/location/states";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("user-agent", "Application");
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		ResponseEntity<StatesDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, StatesDTO.class);
		ErrorDTO errorDTO = new ErrorDTO();
		if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			errorDTO.setErrorCode("400100");
			errorDTO.setMessage("Wrong input");
			throw new CowinException(errorDTO);
		}
		return responseEntity.getBody();
	}
	
	
	@Override
	public DistrictsDTO getDistrictsById(int stateId) throws CowinException {
		String url = baseUrl+"v2/admin/location/districts/"+stateId;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("user-agent", "Application");
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		ResponseEntity<DistrictsDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, DistrictsDTO.class);
		
		ErrorDTO errorDTO = new ErrorDTO();
		if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			errorDTO.setErrorCode("400100");
			errorDTO.setMessage("Wrong input");
			throw new CowinException(errorDTO);
		}
		if(responseEntity.getBody().getDistrictDTOs().isEmpty()) {
			errorDTO.setErrorCode("400102");
			errorDTO.setMessage("Invalid State Id");
			throw new CowinException(errorDTO);
		}
		return responseEntity.getBody();
	}
	

}
