package it.beije.mgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

import it.beije.mgmt.security.JwtConfigurer;
import it.beije.mgmt.security.JwtTokenProvider;
import it.beije.mgmt.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(value = {"it.beije.mgmt.repository"})
public class ConfigurationClass extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
    @Primary
    @Bean(name="transactionManager")
    public PlatformTransactionManager dbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(JpaEntityManager.getInstance());
        return transactionManager;
    }
    
    @Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	/*@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()//.loginPage("/login")-> de-commentare quando il login custom sarà agganciato a security
                .permitAll();
    }*/
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
               /* 
                .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
                .anyRequest().authenticated()
                */
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }
	
	@Override
	public void configure(WebSecurity web) {
		//Questo Override serve per isolare l'autenticazione dalle API
		super.configure(web);
//		web.ignoring().antMatchers("/api/**");
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
