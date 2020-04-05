package com.asheesh.learning.webservices.restfulwebservices.exception;

import java.util.Date;

public class ResponseException {

	private Date timestamp;
	private String errorMsg;
	private String details;

	public ResponseException(Date timestamp, String errorMsg, String details) {
		this.timestamp = timestamp;
		this.errorMsg = errorMsg;
		this.details = details;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getDetails() {
		return details;
	}
}
