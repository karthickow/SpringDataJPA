package com.karthick.jpaonetomany.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${spring.security.user.name}")
  private String user;
  
  @Value("${spring.security.user.password}")
  private String pass;
  
  @Value("${spring.security.user.roles}")
  private String role;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    String password = passwordEncoder().encode(pass);
    auth.inMemoryAuthentication().withUser(user).password(password).roles(role);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/mystore/**").permitAll();
    http.authorizeRequests().antMatchers("/manage/**").hasRole(role).and().httpBasic();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}
