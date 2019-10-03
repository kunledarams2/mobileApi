package com.kunledarams.mobilewsapi.ui.model.response;

public enum ErrorMessages {

	MISSING_REEQUIRED_FIELD("Missing Required Field; Please check the documentation for required field"),
	RECORD_ALREADY_EXIST("Record already exist"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with provided id not found"),
	AUTHENTICATION_FAILED("Authenication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"),
	COULD_NOT_DELECT_RECORD("Could not delect record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");
	
	
	private String errorMessage;

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
