package com.broker.service.app.pojo;

public class ResponseMessage {
	
	private boolean valid = true;
	private String message;
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	@Override
	public String toString() {
		return "ResponseMessage [valid=" + valid + ", message=" + message + "]";
	}
	
	

}
