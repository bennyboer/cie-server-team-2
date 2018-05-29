package edu.hm.cs.cieserver.util;

/**
 * General object returned as API response.
 *
 * @param <T> object held by the response
 */
public class APIResponse<T> {

	/**
	 * Whether the response signs success.
	 */
	private Boolean success;

	/**
	 * Message to deliver to the clients.
	 */
	private T message;

	/**
	 * Create new API response.
	 *
	 * @param success whether the response signs success.
	 * @param message to deliver to the clients
	 */
	public APIResponse(Boolean success, T message) {
		this.success = success;
		this.message = message;
	}

	/**
	 * Whether the response signs success.
	 *
	 * @return whether the response signs success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * Set whether the response signs success.
	 *
	 * @param success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * Get message to deliver to the client.
	 *
	 * @return message to deliver
	 */
	public T getMessage() {
		return message;
	}

	/**
	 * Set message to deliver to the client.
	 *
	 * @param message to deliver
	 */
	public void setMessage(T message) {
		this.message = message;
	}

}
