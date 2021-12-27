package com.opentext.cowin.service;

import org.springframework.stereotype.Service;

import com.opentext.cowin.dto.AppointmentsDTO;
import com.opentext.cowin.exception.CowinException;

@Service
public interface AppointmentService {

	public AppointmentsDTO getAppointmentByDistrictId(int districtId, String date) throws CowinException;
	
	public AppointmentsDTO getFindByPin(int pinCode,String date) throws CowinException;
}
