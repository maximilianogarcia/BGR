package ar.com.bgr.serrano.dao.exception;

public class EntityDontMatchException extends RuntimeException {

	private static final long serialVersionUID = -2088037463426353079L;

	public EntityDontMatchException(String message) {
		super(message);
	}
}
