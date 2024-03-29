package com.unmeshc.security.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by uc on 10/9/2019
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("anup")
                    .password(passwordEncoder.encode("anup"))
                    .roles("USER")
                    .and()
                .withUser("khokon")
                    .password(passwordEncoder.encode("khokon"))
                    .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(PUBLIC).permitAll()
            .anyRequest()
            .authenticated()
            .and()
//            .httpBasic(); // browser provided login form
            .formLogin() // spring security provided login form
                .loginPage("/login").permitAll() // custom login form
            .and()
            .logout().permitAll();

        // for accessing h2 web console
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    private String[] PUBLIC = {
        "/h2-console/**"
    };
}
