package com.uren.extranet.api.model;

import java.io.Serializable;

public class BaseResponseModel implements Serializable{

	private static final long serialVersionUID = -2378173488433189023L;

	private boolean success;
	private String errorCode;
	private String errorMessage;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
