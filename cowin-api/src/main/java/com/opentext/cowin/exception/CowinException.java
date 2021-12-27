package com.opentext.cowin.exception;

import java.io.Serializable;

import com.opentext.cowin.dto.ErrorDTO;



public class CowinException extends Exception implements Serializable {
	private static final long serialVersionUID = 1000L;
	
	private final ErrorDTO errorDTO;

	public CowinException(ErrorDTO errorDTO) {
		super(errorDTO.getMessage());
		this.errorDTO = errorDTO;
	}

	public ErrorDTO getErrorDTO() {
		return errorDTO;
	}
	
}
