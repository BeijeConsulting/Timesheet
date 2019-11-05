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
		http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()//.loginPage("/login")-> de-commentare quando il login custom sarà agganciato a security
                .permitAll();        
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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

//CREATE TABLE `user` (
//		  `id_user` int(11) NOT NULL AUTO_INCREMENT,
//		  `first_name` varchar(30) NOT NULL,
//		  `last_name` varchar(30) NOT NULL,
//		  `work_email` varchar(70) NOT NULL,
//		  `personal_email` varchar(80) DEFAULT NULL,
//		  `phone` varchar(20) DEFAULT NULL,
//		  `fiscal_code` varchar(16) DEFAULT NULL,
//		  `birth_date` date DEFAULT NULL,
//		  `document` varchar(50) DEFAULT NULL,
//		  `id_skype` varchar(30) DEFAULT NULL,
//		  `password` varchar(50) NOT NULL,
//		  `admin` bit(1) DEFAULT NULL,
//		  `archived` date DEFAULT NULL,
//		  `note` varchar(255) DEFAULT NULL,
//		  PRIMARY KEY (`id_user`),
//		  UNIQUE KEY `work_email` (`work_email`),
//		  UNIQUE KEY `fiscal_code` (`fiscal_code`),
//		  UNIQUE KEY `document` (`document`)
//		) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
