package edu.hm.cs.cieserver.security;

import edu.hm.cs.cieserver.user.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JSON web token provider.
 */
@Component
public class JWTTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JWTTokenProvider.class);

	/**
	 * Expiration of a token in milliseconds.
	 */
	@Value("${app.cie-server.jwt-expiration}")
	private int expiration;

	/**
	 * Secret used with the json web token.
	 */
	@Value("${app.cie-server.jwt-secret}")
	private String secret;

	/**
	 * Generate a new JSON web token.
	 *
	 * @param authentication to generate token for
	 * @return new JSON web token
	 */
	public String generateToken(Authentication authentication) {
		User userPrincipal = (User) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration);

		return Jwts.builder()
				.setSubject(Long.toString(userPrincipal.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	/**
	 * Get user id from JSON web token.
	 *
	 * @param token to extract user id from
	 * @return user id extracted from JWT
	 */
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();

		return Long.parseLong(claims.getSubject());
	}

	/**
	 * Validate the passed JSON web token.
	 *
	 * @param authToken token to validate
	 * @return whether the passed token is valid
	 */
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);

			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}

		return false;
	}

}
