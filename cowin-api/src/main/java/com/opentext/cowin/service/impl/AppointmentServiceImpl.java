package com.opentext.cowin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.cowin.dto.AppointmentsDTO;
import com.opentext.cowin.dto.ErrorDTO;
import com.opentext.cowin.exception.CowinException;
import com.opentext.cowin.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${connect.api.baseUrl}")
	private String baseUrl;

	@Override
	public AppointmentsDTO  getAppointmentByDistrictId(int districtId, String date) throws CowinException {
		String url = UriComponentsBuilder.fromHttpUrl(baseUrl+"v2/appointment/sessions/public/findByDistrict")
						.queryParam("district_id", districtId)
						.queryParam("date", date).toUriString();
		ErrorDTO errorDTO = new ErrorDTO();
		try {
			AppointmentsDTO appointmentsDTO =  getResponseAppointmentsDTO(url);
			if(appointmentsDTO.getAppointmentDTOs().isEmpty()) {
				errorDTO.setErrorCode("400103");
				errorDTO.setMessage("Invalid District Id or Date");
				throw new CowinException(errorDTO);
			}
			return appointmentsDTO;
		} catch (JsonProcessingException e) {
			errorDTO.setErrorCode("500100");
			errorDTO.setMessage("Server Error");
			throw new CowinException(errorDTO);
		}
	}
	
	@Override
	public AppointmentsDTO getFindByPin(int pinCode,String date) throws CowinException {
		ErrorDTO errorDTO = new ErrorDTO();
		String url = UriComponentsBuilder.fromHttpUrl(baseUrl+"v2/appointment/sessions/public/findByPin")
				.queryParam("pincode", pinCode)
				.queryParam("date", date).toUriString();
		try {
			AppointmentsDTO appointmentsDTO =  getResponseAppointmentsDTO(url);
			if(appointmentsDTO.getAppointmentDTOs().isEmpty()) {
				errorDTO.setErrorCode("400104");
				errorDTO.setMessage("Invalid Date");
				throw new CowinException(errorDTO);
			}
			return appointmentsDTO;
		} catch (JsonProcessingException e) {
			errorDTO.setErrorCode("500100");
			errorDTO.setMessage("Server Error");
			throw new CowinException(errorDTO);
		}
	}
	
	public AppointmentsDTO getResponseAppointmentsDTO(String url) throws CowinException, JsonProcessingException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("user-agent", "Application");
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		ErrorDTO errorDTO = new ErrorDTO();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ResponseEntity<AppointmentsDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, AppointmentsDTO.class);
			if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				errorDTO.setErrorCode("400100");
				errorDTO.setMessage("Wrong input");
				throw new CowinException(errorDTO);
			}
			return responseEntity.getBody();
		} catch (HttpClientErrorException e) {
			errorDTO = objectMapper.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
			throw new CowinException(errorDTO);
		}
	}
}
