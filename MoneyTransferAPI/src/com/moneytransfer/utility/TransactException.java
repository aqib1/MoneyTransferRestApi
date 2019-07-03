package com.moneytransfer.utility;

public class TransactException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1277654800454268593L;

	public TransactException(String message, Throwable e) {
		super(message, e);
	}

}
