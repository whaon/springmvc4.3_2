package com.mileweb.glb.apiserver.entity;

/**
 * 
 * @author tongh
 *
 */
public class SuccessInfo {
	private int code;
	private String message;
	private int eventId;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	
}
