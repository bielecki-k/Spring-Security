package com.bielecki.Spring.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class SecurityConfig  {

    @Order(0)
    @Configuration
    public static class HttpBasicConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/secured-basic").authorizeRequests()
                    .anyRequest().authenticated()
                    .and().httpBasic()
                    .and().csrf().disable();
        }
    }

    @Order(1)
    @Configuration
    public static class FormLoginConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/secured-form/**")
                    .authorizeRequests()
                    .antMatchers("/secured-form/login")
                    .permitAll()
                    .anyRequest().authenticated()   //to this step - all requests to /secured-form/
                                                    // without /secured-form/login
                                                    // must be authorized
                    .and().formLogin().loginPage("/secured-form/login")
                    .loginProcessingUrl("/secured-form/login")
                    .and().csrf().disable();
        }
    }

    @Configuration
    public static class SecurityContextHolderConfig{
        @Bean
        public SecurityContextHolderStrategy securityContextHolderStrategy(){
            return SecurityContextHolder.getContextHolderStrategy();
        }
    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new AuthenticationProvider() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return new UsernamePasswordAuthenticationToken("any principal", "any credentials",
//                        List.of(new SimpleGrantedAuthority("USER")));
//            }
//
//            @Override
//            public boolean supports(Class<?> authentication) {
//                return true;
//            }
//        });
//    }
}
