package edu.hm.cs.cieserver.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * User entity.
 */
@Entity
@Table(name = "\"User\"")
public class User implements UserDetails {

	/**
	 * ID of the user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * First name of the user.
	 */
	private String firstName;

	/**
	 * Last name of the user.
	 */
	private String lastName;

	/**
	 * Email address of the user.
	 */
	private String email;

	/**
	 * Password of the user.
	 */
	@JsonIgnore
	private String password;

	/**
	 * Whether the user is administrator.
	 */
	@JsonIgnore
	private boolean isAdministrator;

	/**
	 * Create new user.
	 */
	public User() {
		// JPA needs a default constructor.
	}

	/**
	 * Get id of the user.
	 *
	 * @return id of the user
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set ID of the user.
	 *
	 * @param id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get password of the user.
	 *
	 * @return password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password of the user.
	 *
	 * @param password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get email address of the user.
	 *
	 * @return email address of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email address of the user.
	 *
	 * @param email address of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get first name of the user.
	 *
	 * @return first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set first name of the user.
	 *
	 * @param firstName of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get last name of the user.
	 *
	 * @return last name of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set last name of the user.
	 *
	 * @param lastName of the user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Check whether the user is administrator.
	 *
	 * @return whether the user is administrator
	 */
	public boolean isAdministrator() {
		return isAdministrator;
	}

	/**
	 * Set user as administrator?
	 *
	 * @param administrator whether to set user as administrator
	 */
	public void setAdministrator(boolean administrator) {
		isAdministrator = administrator;
	}

	@Override
	public String getUsername() {
		return email; // Email acts as username.
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (isAdministrator()) {
			List<GrantedAuthority> authorities = new ArrayList<>();

			authorities.add((GrantedAuthority) () -> "ROLE_ADMIN");

			return authorities;
		}

		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User that = (User) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
