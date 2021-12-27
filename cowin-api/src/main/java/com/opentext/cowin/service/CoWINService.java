package com.opentext.cowin.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opentext.cowin.dto.CoWINDTO;
import com.opentext.cowin.dto.DistrictsDTO;
import com.opentext.cowin.dto.StatesDTO;
import com.opentext.cowin.exception.CowinException;



@Service
public interface CoWINService {

	public ResponseEntity<String> getOTP(CoWINDTO coWINDTO) throws CowinException;

	public StatesDTO getStates() throws CowinException;
	
	public DistrictsDTO getDistrictsById(int stateId) throws CowinException;
}