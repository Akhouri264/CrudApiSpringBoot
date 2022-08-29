//package com.example.springproject.security;
//
//import java.io.Serializable;
//import java.util.*;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Service
//public class JwtUtil implements Serializable {
//	private static final long serialVersionUID = 1L;
//	private String SecretKey = "secret";
//
//	public String extractUserName(String token) {
//		return extractClaim(token, Claims::getSubject);
//	}
//
//	public Date extractExpiration(String token) {
//		// TODO Auto-generated method stub
//			return extractClaim(token, Claims::getExpiration);
//	}
//
//	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//		final Claims claims = extractAllClaim(token);
//		return claimResolver.apply(claims);
//	}
//
//	private Claims extractAllClaim(String token) {
//		// TODO Auto-generated method stub
//		return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody();
//	}
//
//	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(claims, userDetails.getUsername());
//	}
//
//	private String createToken(Map<String, Object> claims, String username) {
//		
//		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//				.signWith(SignatureAlgorithm.HS256, SecretKey).compact();
//	}
//
//	public boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUserName(token);
//		
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}
//
//	private boolean isTokenExpired(String token) {
//		// TODO Auto-generated method stub
//		return extractExpiration(token).before(new Date());
//	}
//}
