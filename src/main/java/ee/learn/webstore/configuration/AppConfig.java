package ee.learn.webstore.configuration;

import ee.learn.webstore.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//You want some page to be protected by passwords, some not.

@Configuration
@EnableWebSecurity //This means that there are security config done in this class
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Override //setting which page needs login and which not
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/product/all").permitAll() ///or/product/* means everything is permit
                .antMatchers("/user/create").permitAll()
                .antMatchers("/product/create").hasRole("ADMIN") //ROLE_nameofrole
                /*.antMatchers("/category/create").hasRole("admin")*/
                .antMatchers(HttpMethod.GET, "/api/login/verify").authenticated()
                .anyRequest().permitAll()/*authenticated()*/ //any other page requires login
                .and()
                .formLogin()
                .and().httpBasic(); //this means that we want http tokens.


    }

    @Override//giving usernames and roles manually
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }
    //other ways:
        /*auth.inMemoryAuthentication()
        .withUser("user1").password(passwordEncoder().encode("pass1")).roles("USER")
        .and()
        .withUser("user2").password(passwordEncoder().encode("pass2")).roles("ADMIN");
    }*/

    @Bean //this is something that S Security is using - what are the user details and password encoder we want to use
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }

    @Bean //saves encoded password to database, NEVER USE UNCODED passwords
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
