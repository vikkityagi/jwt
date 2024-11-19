// package com.example.demo.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.BeanIds;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.example.demo.service.UserDetailsCustomService;
// import com.example.demo.service.UserService;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private UserService myAuthDetailsService;

//     // @Autowired
//     // private JwtRequestFilter jwtRequestFilter;
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(myAuthDetailsService);
//     }

    
//     // @Override
//     // protected void configure(HttpSecurity http) throws Exception {
//     //     // TODO Auto-generated method stub
//     //     http.csrf().disable().authorizeRequests().antMatchers("/auth").permitAll().anyRequest().authenticated()
//     //     .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//     //     http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        

//     // }



//     // @Bean
//     // @Override
//     // public AuthenticationManager authenticationManagerBean() throws Exception {
//     //     // TODO Auto-generated method stub
//     //     return super.authenticationManagerBean();
//     // }


//     // @Bean
//     // public PasswordEncoder passwordEncoder() {
//     //     return NoOpPasswordEncoder.getInstance();
//     // }

// }