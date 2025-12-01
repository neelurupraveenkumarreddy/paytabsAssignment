package com.poc.bank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails customer= User.withUsername("customer1")
                .password(passwordEncoder().encode("userPassword"))
                .roles("CUSTOMER")
                .build();
        UserDetails admin=User.withUsername("Admin")
                .password(passwordEncoder().encode("adminpassword"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(customer,admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
