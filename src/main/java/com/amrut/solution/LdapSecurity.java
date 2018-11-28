package com.amrut.solution;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class LdapSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests().antMatchers("/css/**").permitAll()
            .and()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/loginPage")
            .loginProcessingUrl("/loginPage").defaultSuccessUrl("/")
            .usernameParameter("username").passwordParameter("password")
            .permitAll()
            .and()
            .logout().logoutSuccessUrl("/loginPage?logout")
            .logoutUrl("/logout")
            .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("prabhu")
                .password("{noop}prabhu")
                .authorities("-");
    }

    // For Ldap authentication configuration
    // @Override
    // protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    // authenticationManagerBuilder.ldapAuthentication()//
    // .userSearchFilter("uid={0}")
    // .contextSource(contextSource());
    // }

    // For Ldap authentication
    // @Bean
    // public DefaultSpringSecurityContextSource contextSource() {
    // return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8080"), "OU=Employees,O=company,C=Global");
    // }
}