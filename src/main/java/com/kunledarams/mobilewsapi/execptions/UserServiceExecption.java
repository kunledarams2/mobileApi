package com.kunledarams.mobilewsapi.execptions;

public class UserServiceExecption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserServiceExecption(String errorMessages) {
		super(errorMessages);
	}

}
