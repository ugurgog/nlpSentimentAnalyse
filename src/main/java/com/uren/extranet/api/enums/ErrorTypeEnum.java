package com.uren.extranet.api.enums;
public enum ErrorTypeEnum {

	ERROR(1,"ERROR"),
	INVALID_REQUEST(2,"INVALID_REQUEST"),
	DB_ERROR(3,"DB_ERROR"),
	NO_CONTENT(4,"NO_CONTENT"),
	EXCEPTION(5,"EXCEPTION"),
	CACHE_NOT_FOUND(6,"CACHE_NOT_FOUND"),
	NOT_MATCHED(7,"NOT_MATCHED");
	
	private String errorCode;
	private int id;

	private ErrorTypeEnum(int id, String errorCode) {
		this.errorCode = errorCode;
	}
	
	public static ErrorTypeEnum getById(int id) {
		for (ErrorTypeEnum e : values()) {
			if (e.id == id) {
				return e;
			}
		}

		return null;
	}
	
	public static ErrorTypeEnum getByName(String name) {
		for (ErrorTypeEnum e : values()) {
			if (name.equalsIgnoreCase(""+e.name()))
				return e;
		}

		return null;
	}
	
	public String getName() {
		return ""+ name();
	}

	public String getErrorCode() {
		return errorCode;
	}

}