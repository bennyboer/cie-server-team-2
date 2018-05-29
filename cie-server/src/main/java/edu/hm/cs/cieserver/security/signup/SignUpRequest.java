package edu.hm.cs.cieserver.security.signup;

import javax.validation.constraints.*;

/**
 * Request for signing up a new user.
 */
public class SignUpRequest {

	/**
	 * First name of the user.
	 */
	@NotBlank
	@Size(max = 40)
	private String firstName;

	/**
	 * Last name of the user.
	 */
	@NotBlank
	@Size(max = 40)
	private String lastName;

	/**
	 * E-Mail address of the user.
	 */
	@NotBlank
	@Size(max = 80)
	@Email
	private String email;

	/**
	 * Password of the new user.
	 */
	@NotBlank
	@Size(min = 5, max = 20)
	private String password;

	/**
	 * Whether the new user is administrator.
	 */
	private boolean isAdministrator;

	/**
	 * Get email of the new user.
	 *
	 * @return email of the new user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email of the new user.
	 *
	 * @param email of the new user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get password of the new user.
	 *
	 * @return password of the new user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password of the new user.
	 *
	 * @param password of the new user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get first name of the new user.
	 *
	 * @return first name of the new user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set first name of the new user.
	 *
	 * @param firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get last name of the new user.
	 *
	 * @return last name of the new user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set last name of the new user.
	 *
	 * @param lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Whether the new user is administrator.
	 *
	 * @return whether the new user is administrator
	 */
	public boolean isAdministrator() {
		return isAdministrator;
	}

	/**
	 * Set whether the new user is an administrator.
	 *
	 * @param administrator set whether user is administrator
	 */
	public void setAdministrator(boolean administrator) {
		isAdministrator = administrator;
	}

}
