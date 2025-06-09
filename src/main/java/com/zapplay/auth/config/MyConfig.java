package com.zapplay.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class MyConfig {
//
//	@Bean
//	public UserDetailsService userDetailsService() {
//
//		UserDetails user1 = User.builder().username("kunal").password(passwordEncoder().encode("kunal")).roles("ADMIN")
//				.build();
//		UserDetails user2 = User.builder().username("nishant").password(passwordEncoder().encode("nishant"))
//				.roles("ADMIN").build();
//
//		return new InMemoryUserDetailsManager(user1, user2);
//	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {

		return builder.getAuthenticationManager();
	}

}
