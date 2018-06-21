package edu.hm.cs.cieserver.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.hm.cs.cieserver.course.Course;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * User entity.
 */
@Entity
@Table(name = "\"User\"")
public class User implements UserDetails {

	/**
	 * Role for admins.
	 */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

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
	private String password;

	/**
	 * Firebase token used to target this user in firebase cloud messaging notifications.
	 */
	private String firebaseToken;

	/**
	 * Whether the user is administrator.
	 */
	@JsonProperty
	private boolean isAdministrator;

	/**
	 * Set of courses this user selected.
	 */
	@JsonIgnore
	@ManyToMany
	private Set<Course> selectedCourses;

	/**
	 * Set of courses this user favorited.
	 */
	@JsonIgnore
	@ManyToMany
	private Set<Course> favorizedCourses;

	/**
	 * Set of received courses (From the lottery).
	 */
	@JsonIgnore
	@ManyToMany
	private Set<Course> receivedCourses;

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
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * Set password of the user.
	 *
	 * @param password of the user
	 */
	@JsonProperty
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
	public boolean getIsAdministrator() {
		return isAdministrator;
	}

	/**
	 * Set user as administrator?
	 *
	 * @param isAdministrator whether to set user as administrator
	 */
	public void setIsAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	/**
	 * Get selected courses for this user.
	 *
	 * @return all selected courses for this user
	 */
	public Set<Course> getSelectedCourses() {
		return selectedCourses;
	}

	/**
	 * Set selected courses for this user.
	 *
	 * @param selectedCourses to set for this user
	 */
	public void setSelectedCourses(Set<Course> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}

	/**
	 * Get favorited courses for this user.
	 *
	 * @return favorited courses of this user
	 */
	public Set<Course> getFavorizedCourses() {
		return favorizedCourses;
	}

	/**
	 * Set favorited courses for this user.
	 *
	 * @param favorizedCourses of this user
	 */
	public void setFavorizedCourses(Set<Course> favorizedCourses) {
		this.favorizedCourses = favorizedCourses;
	}

	/**
	 * Get received courses (From the lottery).
	 *
	 * @return received courses
	 */
	public Set<Course> getReceivedCourses() {
		return receivedCourses;
	}

	/**
	 * Set received courses (From the lottery).
	 *
	 * @param receivedCourses courses the user received
	 */
	public void setReceivedCourses(Set<Course> receivedCourses) {
		this.receivedCourses = receivedCourses;
	}

	/**
	 * Get users firebase token.
	 *
	 * @return users firebase token
	 */
	public String getFirebaseToken() {
		return firebaseToken;
	}

	/**
	 * Set users firebase token.
	 *
	 * @param firebaseToken users firebase token to set
	 */
	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
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
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (getIsAdministrator()) {
			List<GrantedAuthority> authorities = new ArrayList<>();

			authorities.add((GrantedAuthority) () -> ROLE_ADMIN);

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
