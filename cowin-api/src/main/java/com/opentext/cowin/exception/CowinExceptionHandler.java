package com.opentext.cowin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opentext.cowin.dto.ErrorDTO;



@ControllerAdvice
public class CowinExceptionHandler {

	@ExceptionHandler(CowinException.class)
	@ResponseBody
	public ResponseEntity<ErrorDTO> handlerExerciseServiceException(CowinException cowinException) {
		ErrorDTO errorDTO = cowinException.getErrorDTO();
		HttpStatus httpStatus = getHttpStatus(errorDTO);
		return new ResponseEntity<>(errorDTO,httpStatus);
	}
	
	private HttpStatus getHttpStatus(ErrorDTO errorDTO) {
		HttpStatus httpStatus = HttpStatus.PARTIAL_CONTENT;
		if(errorDTO.getErrorCode().startsWith("400") || errorDTO.getErrorCode().startsWith("APP")) {
			httpStatus = HttpStatus.BAD_REQUEST;
		} else if(errorDTO.getErrorCode().startsWith("403")) {
			httpStatus = HttpStatus.FORBIDDEN;
		} else if(errorDTO.getErrorCode().startsWith("500")) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return httpStatus;
	}
}
