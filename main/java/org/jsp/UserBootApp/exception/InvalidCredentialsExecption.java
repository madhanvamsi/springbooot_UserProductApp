package org.jsp.UserBootApp.exception;

public class InvalidCredentialsExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "the id is invalid";
	}

}
