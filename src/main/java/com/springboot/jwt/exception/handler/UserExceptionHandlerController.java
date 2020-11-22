package com.springboot.jwt.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserExceptionHandlerController {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorBean> handleUserNotFoundException(UserNotFoundException userNotFoundException,
			WebRequest webRequest) {
		ErrorBean errorBean = new ErrorBean();
		errorBean.setMessage(userNotFoundException.getMessage());
		errorBean.setLocalDateTime(LocalDateTime.now());
		errorBean.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<ErrorBean>(errorBean, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorBean> handleGlobalException(Exception exception, WebRequest webRequest) {
		ErrorBean errorBean = new ErrorBean();
		errorBean.setMessage(exception.getMessage());
		errorBean.setLocalDateTime(LocalDateTime.now());
		errorBean.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<ErrorBean>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
