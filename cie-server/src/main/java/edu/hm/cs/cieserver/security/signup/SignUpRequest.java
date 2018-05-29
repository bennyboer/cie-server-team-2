package edu.hm.cs.cieserver.security.signup;

import javax.validation.constraints.*;

public class SignUpRequest {

	@NotBlank
	@Size(min = 4, max = 40)
	private String firstName;

	@NotBlank
	@Size(min = 3, max = 15)
	private String lastName;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 5, max = 20)
	private String password;

	private boolean isAdministrator;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(boolean administrator) {
		isAdministrator = administrator;
	}

}
