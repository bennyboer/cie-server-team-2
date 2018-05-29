package edu.hm.cs.cieserver.security.login;

import javax.validation.constraints.NotBlank;

/**
 * Request to login using user credentials.
 */
public class LoginRequest {

	/**
	 * E-Mail address of user.
	 */
	@NotBlank
	private String email;

	/**
	 * Password of user.
	 */
	@NotBlank
	private String password;

	/**
	 * Get email address of user.
	 *
	 * @return email address of user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email address of user.
	 *
	 * @param email address of user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get password of user.
	 *
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password of user.
	 *
	 * @param password of user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
