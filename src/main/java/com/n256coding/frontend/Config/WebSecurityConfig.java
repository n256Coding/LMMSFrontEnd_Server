package com.n256coding.frontend.Config;


import com.n256coding.frontend.Services.MongoUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/text-request",
                        "/text-responce",
                        "/video",
                        "/video/processing",
                        "/moodle",
                        "/moodle-result",
                        "/slide",
                        "/report",
                        "/customReportSlide")
//                    .hasAnyAuthority("USER", "ADMIN")
                .authenticated()
                .antMatchers("/adminTemplate")
                    .hasAnyAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                    .loginPage("/loginPage")
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/");
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new MongoUserDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder(11));
    }


}
