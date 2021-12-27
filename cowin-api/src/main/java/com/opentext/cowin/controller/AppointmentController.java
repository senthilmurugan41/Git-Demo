package com.opentext.cowin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opentext.cowin.dto.AppointmentsDTO;
import com.opentext.cowin.exception.CowinException;
import com.opentext.cowin.service.AppointmentService;

@RestController
@RequestMapping(path = "/cowin")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping(path = "/findByDistrictId", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody AppointmentsDTO getAppointmentByDistrictID(@RequestParam(name = "districtId", required = true) int districtId, @RequestParam(name = "date", required = true) String date) throws CowinException {
		return appointmentService.getAppointmentByDistrictId(districtId, date);
	}
	
	@GetMapping(path = "/availability", produces = {MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	public @ResponseBody AppointmentsDTO getFindByPin(@RequestParam(name="pincode",required = true) int pincode,@RequestParam(name="date",required = true)String date) throws CowinException {
		return appointmentService.getFindByPin(pincode,date);
	}
}
