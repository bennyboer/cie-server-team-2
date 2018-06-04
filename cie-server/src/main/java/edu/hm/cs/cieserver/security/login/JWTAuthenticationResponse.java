package edu.hm.cs.cieserver.security.login;

/**
 * Response of an authentication request.
 */
public class JWTAuthenticationResponse {

	/**
	 * Json web token access token.
	 */
	private String accessToken;

	/**
	 * Whether the authenticated user is admin.
	 */
	private boolean isAdmin;

	/**
	 * Type of the token.
	 */
	private String tokenType = "Bearer";

	/**
	 * Create new authentication response.
	 *
	 * @param accessToken to pass to client
	 * @param isAdmin     whether the user is admin
	 */
	public JWTAuthenticationResponse(String accessToken, boolean isAdmin) {
		this.accessToken = accessToken;
		this.isAdmin = isAdmin;
	}

	/**
	 * Get the access token.
	 *
	 * @return access token (JWT)
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Set the access token.
	 *
	 * @param accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Get the token type.
	 *
	 * @return token type
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * Set the token type.
	 *
	 * @param tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * Whether the authenticated user is admin.
	 *
	 * @return whether the authenticated user is admin
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * Set whether the authenticated user is an admin.
	 *
	 * @param admin whether user is admin or not
	 */
	public void setIsAdmin(boolean admin) {
		isAdmin = admin;
	}
}
