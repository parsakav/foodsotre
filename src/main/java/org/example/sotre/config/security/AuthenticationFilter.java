package org.example.sotre.config.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.*;

import jakarta.servlet.http.*;
import org.example.sotre.config.SpringApplicationContext;
import org.example.sotre.dto.UserDto;
import org.example.sotre.request.UserLoginRequestModel;
import org.example.sotre.service.UserService;
import org.springframework.security.core.userdetails.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;


import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(),
					UserLoginRequestModel.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
			String username=((User) authResult.getPrincipal()).getUsername();
			String token = Jwts.builder().setSubject(username)

					.setExpiration(dateAfterNow(SecurityConstant.EXPIRATION_TIME))
					.signWith(SecurityConstant.getSigningKey())
					.compact();
			UserService userService=(UserService) SpringApplicationContext.getBean("userServiceImpl");
			UserDto user=userService.getUserByUsername(username);
			response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX+token);
			response.addHeader("UserId", user.getEmail());
			response.setContentType("application/json");
			PrintWriter pw=new PrintWriter(response.getOutputStream(),true);

			pw.println(String.format("""
					{"token":"%s",
					"email":"%s",
					"userId":"%s",
					"role":"%s"
					}
					""",SecurityConstant.TOKEN_PREFIX+token,user.getEmail(),user.getId(),user.getRole()));

			pw.flush();
			pw.close();
	}


	private Date dateAfterNow(long millis) {
		return new Date(System.currentTimeMillis()+millis);
	}
}
