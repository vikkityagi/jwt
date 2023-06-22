package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import com.example.demo.service.UserService;

@EnableWebSecurity
public class ApplicationSecurityConfig {


  @Autowired
  private UserService userService;


  @Bean
  public PasswordEncoder customPasswordEncoder() {


      // Map<String, PasswordEncoder> encoders = new HashMap<>();
      //   encoders.put("SSHA-512", new Hmac512PasswordEncoder("salt"));
      //   // encoders.put("bcrypt", new BCryptPasswordEncoder());
      //   return new DelegatingPasswordEncoder("SSHA-512", encoders);
    return new Hmac512PasswordEncoder("");
    // DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories
    //     .createDelegatingPasswordEncoder();

    // delegatingPasswordEncoder
    //     .setDefaultPasswordEncoderForMatches(new CustomPasswordEncoder());

    // return delegatingPasswordEncoder;

  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(customPasswordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userService);
    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(daoAuthenticationProvider())
        .build();
  }

  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) {
  // auth.authenticationProvider(daoAuthenticationProvider());

  // //We can register as many providers as we may have
  // //auth.authenticationProvider(customProviderTwo);
  // //auth.authenticationProvider(customProviderThree);
  // }

  // ...
}