package org.example.sotre.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import jakarta.servlet.*;

import jakarta.servlet.http.*;
import org.example.sotre.config.SpringApplicationContext;
import org.example.sotre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

public class AuthorizationFilter extends BasicAuthenticationFilter {


private UserService userService;
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.userService=((UserService) SpringApplicationContext.getBean("userServiceImpl"));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String requestURI = request.getRequestURI();
/*
		if (requestURI.equals(SecurityConstant.SIGN_UP_URL) ||
				requestURI.equals(SecurityConstant.LOGIN_URL) ||
				requestURI.startsWith("/swagger")) {
			chain.doFilter(request, response);
			return;
		}	*/	String header = request.getHeader(SecurityConstant.HEADER_STRING);
		System.out.println(header);
		if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			System.out.println("Error");
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken uToken = getAuthetication(request);
		if (uToken != null) {
			System.out.println(uToken);
			SecurityContextHolder.getContext().setAuthentication(uToken);
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthetication(HttpServletRequest request) {


		String header = request.getHeader(SecurityConstant.HEADER_STRING);
		try {
			if (header != null) {
				Date d = Jwts.parserBuilder().setSigningKey(SecurityConstant.getSigningKey()).build()
						.parseClaimsJws(header.replace("Bearer", "").trim()).getBody().getExpiration();
				String username = Jwts.parserBuilder().setSigningKey(SecurityConstant.getSigningKey()).build()
						.parseClaimsJws(header.replace("Bearer", "").trim()).getBody().getSubject();
				if(new Date().after(d)){

					throw new IllegalStateException("The token has expired");
				}
				System.out.println(username);
				// TODO Auto-generated method stub
				UserDetails user=userService.loadUserByUsername(username);
			if (username != null) {

					return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
				}
			}


		} catch (Exception e) {
e.printStackTrace();
		}
		return null;
	}
}
