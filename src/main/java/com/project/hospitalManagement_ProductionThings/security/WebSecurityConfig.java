package com.project.hospitalManagement_ProductionThings.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        httpSecurity
                // now here we are saying spring dont store any session now, jwt will handle it, dont store anything on server sidde
                // bcoz by default spring store sessions in memmoery, now we want stateless session
                // firstly we will disable csrf
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig ->
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/public/**", "/auth/**").permitAll()      // Allow this type of request
                                .anyRequest().authenticated()
                )    // Humne jo filter banaya tha usse chain me add karna hai aaise
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }





    // In order to make a new USer InMemoryUserDetailsManager we can use this method
//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("Rahul").password(passwordEncoder.encode("pass")).roles("ADMIN").build();
//
//        UserDetails user2 = User.withUsername("Raj").password(passwordEncoder.encode("pass2")).roles("DOCTOR").build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}
