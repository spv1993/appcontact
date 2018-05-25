package edu.springproject.appcontact.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.springproject.appcontact.filter.AccessTokenFilter;
import edu.springproject.appcontact.service.AuthService;
import edu.springproject.appcontact.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthService authService;
	
	@Autowired
	UserService userDetailsService;

	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

	
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		http.headers().frameOptions().sameOrigin();
		http.authorizeRequests().antMatchers("/api/contacts/**").hasAnyAuthority("ADMIN", "USER");
		http.authorizeRequests().antMatchers("/api/users/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/auth/**").permitAll().anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(tokenAuthenticationEntryPoint());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

	@Bean
    public AccessTokenFilter tokenAuthenticationFilter() {
        return new AccessTokenFilter(authService);
    }
	
	@Bean
    public AccessTokenAuthenticationEntryPoint tokenAuthenticationEntryPoint() {
        return new AccessTokenAuthenticationEntryPoint();
    }
}
