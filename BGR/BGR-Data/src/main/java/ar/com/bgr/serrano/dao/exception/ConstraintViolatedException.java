package ar.com.bgr.serrano.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="No es posible realizar la accion, existe una relacion que no lo permite.")
public class ConstraintViolatedException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}