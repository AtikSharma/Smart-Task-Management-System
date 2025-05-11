package com.taskmanager.common.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.taskmanager.common.constants.CommonConstants;
import com.taskmanager.common.constants.JwtConstants;
import com.taskmanager.common.model.JwtToken;
import com.taskmanager.common.model.UserBase;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.secretkey}")
	private String SECRET_KEY;

	@Value("${jwt.access.expiration:120}")
	private long accessTokenExpiration;

	@Value("${jwt.refresh.expiration:300}")
	private long refreshTokenExpiration;

	public JwtToken generateJwt(UserBase userBase) {

		Instant now = Instant.now();
		Date currentDate = Date.from(now);
		Date expiryDate = Date.from(now.plus(accessTokenExpiration, ChronoUnit.SECONDS));
		final String randomUUID = UUID.randomUUID().toString();

		String accessToken = Jwts.builder().header().add(JwtConstants.TYPE, JwtConstants.JWT).and()
				.issuer(JwtConstants.JWT_ISSUER).subject(JwtConstants.SUBJECT).id(randomUUID).expiration(expiryDate)
				.issuedAt(currentDate).claim(JwtConstants.ID, userBase.getId())
				.claim(JwtConstants.USERNAME, userBase.getUsername()).claim(JwtConstants.EMAIL, userBase.getEmail())
				.claim(JwtConstants.ROLE, userBase.getRole()).signWith(getKey()).compact();

		String refreshToken = generateRefreshToken(now, userBase);

		return JwtToken.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}

	private String generateRefreshToken(Instant now, UserBase userBase) {
		Date expiryDate = Date.from(now.plus(refreshTokenExpiration, ChronoUnit.SECONDS));
		final String randomUUID = UUID.randomUUID().toString();
		return Jwts.builder().header().add(JwtConstants.TYPE, JwtConstants.REFRESH).and()
				.issuer(JwtConstants.JWT_ISSUER).subject(JwtConstants.SUBJECT).id(randomUUID).expiration(expiryDate)
				.issuedAt(Date.from(now)).claim(JwtConstants.ID, userBase.getId()).signWith(getKey()).compact();
	}

	private Key getKey() {
		byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public Date getExpirationDate(String token) {
		return extractClaims(token).getExpiration();
	}

	public Date getCreatedDate(String token) {
		return extractClaims(token).getIssuedAt();
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token).getPayload();
	}

	public void validateToken(String accessToken) {
		Claims claims = null;
		try {
			if (Objects.isNull(accessToken) || accessToken.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
			claims = extractClaims(accessToken.split(CommonConstants.SPACE)[1]);
			Date issuedAt = claims.getIssuedAt();

			if (Objects.nonNull(claims.getExpiration())) {
				Date expiration = claims.getExpiration();
				long difference = Math.subtractExact(expiration.getTime(), issuedAt.getTime()) / 1000;
				int x = 60;
				if (difference > (accessTokenExpiration) + x) {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
				}
			}

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

}
