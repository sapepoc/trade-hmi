package com.sapient.hmi.dto;

public class ResponseEntity {

	private boolean success;
	private Object data;
	
	public ResponseEntity(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}


	@Override
	public String toString() {
		return "ResponseEntity [success=" + success + ", data=" + data + "]";
	}
	
	
	
}
