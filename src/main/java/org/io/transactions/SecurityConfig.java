package org.io.transactions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
				.csrf(AbstractHttpConfigurer::disable).build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		final UserDetails user = User.withUsername("admin")
				.password(encoder().encode("admin"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
