package edu.hm.cs.cieserver.util;

public class APIResponse<T> {
	private Boolean success;
	private T message;

	public APIResponse(Boolean success, T message) {
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}

}
