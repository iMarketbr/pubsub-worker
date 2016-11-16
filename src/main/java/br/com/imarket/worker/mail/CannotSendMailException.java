package br.com.imarket.worker.mail;

import java.io.IOException;

public class CannotSendMailException extends RuntimeException {
	private static final long serialVersionUID = -3024633229067102341L;

	public CannotSendMailException(String message) {
		super(message);
	}

	public CannotSendMailException(String message, IOException e) {
		super(message, e);
	}

}
