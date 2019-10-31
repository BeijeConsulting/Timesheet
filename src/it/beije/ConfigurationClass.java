package it.beije;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.beije.erp.timesheet.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories("it.beije.timesheet.repositories")
public class ConfigurationClass extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userDetailsService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Security configuration http");
		http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()//.loginPage("/login")
                .permitAll();        
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Security configuration auth");
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encode(rawPassword).equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};
	}
	
}
