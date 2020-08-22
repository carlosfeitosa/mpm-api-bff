package com.skull.bff.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import feign.FeignException.NotFound;

/**
 * Advice for controller NoSuchElementException.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-07-18
 *
 */
@ControllerAdvice
public class NotFoundAdvice { // NOPMD by skull on 8/8/20, 7:02 PM

	/**
	 * Set 404 if NoSuchElementException was raised.
	 * 
	 * @param exception generated exception
	 * 
	 * @return exception message
	 */
	@ResponseBody
	@ExceptionHandler(NotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String noSuchElementHandler(final NotFound exception) {

		return exception.getMessage();
	}
}
