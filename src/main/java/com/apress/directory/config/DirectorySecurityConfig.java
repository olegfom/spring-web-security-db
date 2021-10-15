package com.apress.directory.config;

import com.apress.directory.repository.PersonRepository;
import com.apress.directory.security.DirectoryUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter {
    private PersonRepository personRepository;

    public DirectorySecurityConfig(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/**")
//                .hasRole("ADMIN")
//                .and()
//                .httpBasic();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.antMatcher("/**")
	    			.authorizeRequests()
					.antMatchers("/persons/**")
						.hasRole("ADMIN")
						.and()
						.httpBasic()
					.and()
        			.authorizeRequests()
        				.antMatchers("/h2-console/**").permitAll();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
   
   
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
            new DirectoryUserDetailsService(this.personRepository));
    }
}