package it.beije.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.transaction.PlatformTransactionManager;

import it.beije.mgmt.JpaEntityManager;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(value = {"it.beije.mgmt.repository"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Primary
    @Bean(name="transactionManager")
    public PlatformTransactionManager dbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(JpaEntityManager.getInstance());
        return transactionManager;
    }
	
	//CLASSE PER operazioni di generazione, validazione e parsing del token
	
	    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
	            new AntPathRequestMatcher("/api/register"),
	            new AntPathRequestMatcher("/api/login"),
	            new AntPathRequestMatcher("/register"),
	            new AntPathRequestMatcher("/login")
	    );
	    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

	    @Autowired
	    private TokenAuthenticationProvider authenticationProvider;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(authenticationProvider);
	    }

	    @Override
	    public void configure(WebSecurity web) {
	        web.ignoring().requestMatchers(PUBLIC_URLS);
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .sessionManagement().sessionCreationPolicy(STATELESS)
	                .and()
	                .exceptionHandling()
	                .defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
	                .and()
	                .authenticationProvider(authenticationProvider)
	                .addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter.class)
	                .authorizeRequests()
	                .anyRequest()
	                .authenticated()
	                .and()
	                .csrf().disable()
	                .formLogin().disable()
	                .httpBasic().disable()
	                .logout().disable();
	    }

	    @Bean
	    TokenAuthenticationFilter restAuthenticationFilter() throws Exception {
	        TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS);
	        filter.setAuthenticationManager(authenticationManager());
	        filter.setAuthenticationSuccessHandler(successHandler());
	        return filter;
	    }

	    @Bean
	    SimpleUrlAuthenticationSuccessHandler successHandler() {
	        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
	        successHandler.setRedirectStrategy(new NoRedirectStrategy());
	        return successHandler;
	    }

//	    @Bean
//	    FilterRegistrationBean disableAutoRegistration(TokenAuthenticationFilter filter) {
//	        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//	        registration.setEnabled(false);
//	        return registration;
//	    }

	    @Bean
	    AuthenticationEntryPoint forbiddenEntryPoint() {
	        return new HttpStatusEntryPoint(FORBIDDEN);
	    }
	
	
	
	

}
