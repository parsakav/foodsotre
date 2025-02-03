package org.example.sotre.config.security;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean(name = "passwordEncoder")
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authProvider,
			AuthenticationManager authenticationManager, UserDetailsService userDetailsService) throws Exception {

		http.authenticationProvider(authProvider);
http.csrf(AbstractHttpConfigurer::disable);

http.sessionManagement(e->{
	e.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
});
		http.authorizeHttpRequests(e->{
			e.requestMatchers(

					SecurityConstant.SIGN_UP_URL,"/swagger.html"
					,"/swagger-ui/index.html"
					,SecurityConstant.LOGIN_URL
					)

					.permitAll();
/*
			e.requestMatchers(HttpMethod.GET,"/products").hasAnyRole("ADMIN","USER");
*/
			e.requestMatchers("/users/**").permitAll();
			e.requestMatchers("/users").permitAll();
			e.requestMatchers("/swagger-ui/").permitAll();
			e.requestMatchers("/swagger-ui**").permitAll();
			e.requestMatchers("/swagger-ui/**").permitAll();
			e.requestMatchers("/users**").permitAll();
			e.anyRequest().permitAll();


				});

				http.addFilter(getAuthenticationFilter(authenticationManager))
				.addFilter(new AuthorizationFilter(authenticationManager));

		return http.build();

	}

	@Bean
	DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetails, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetails);
		authProvider.setPasswordEncoder(passwordEncoder);

		return authProvider;
	}

	public AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
		final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);

		authenticationFilter.setFilterProcessesUrl(SecurityConstant.LOGIN_URL);
		return authenticationFilter;
	}
}
