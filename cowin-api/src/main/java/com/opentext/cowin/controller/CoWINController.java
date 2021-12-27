package com.opentext.cowin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opentext.cowin.dto.CoWINDTO;
import com.opentext.cowin.dto.DistrictsDTO;
import com.opentext.cowin.dto.StatesDTO;
import com.opentext.cowin.exception.CowinException;
import com.opentext.cowin.service.CoWINService;

@RestController
@RequestMapping(path = "/cowin")
public class CoWINController {
	
	@Autowired
	private CoWINService cowinService;

	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> getOTP(@RequestBody CoWINDTO coWINDTO) throws CowinException {
		return cowinService.getOTP(coWINDTO);
	}
	
	@GetMapping(path = "/states", produces = {MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	public @ResponseBody StatesDTO getStates() throws CowinException {
		return cowinService.getStates();
	}
	
	@GetMapping(path = "/districts/{stateId}", produces = {MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	public @ResponseBody DistrictsDTO getDistrictsById(@PathVariable(name="stateId",required = true)int stateId) throws CowinException {
		return cowinService.getDistrictsById(stateId);
	}
	
}
