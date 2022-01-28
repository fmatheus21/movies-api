package com.fmatheus.app.config;

import com.fmatheus.app.controller.constant.ResourceConstant;
import com.fmatheus.app.controller.constant.RoleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServeConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        } catch (Exception ex) {
            Logger.getLogger(ResourceServeConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/csrf",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()
                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, ResourceConstant.MOVIES).hasAnyAuthority(RoleConstant.LIST_MOVIES)
                .antMatchers(HttpMethod.GET, ResourceConstant.MOVIES + ResourceConstant.ID_RESOURCE).hasAnyAuthority(RoleConstant.VIEW_MOVIES)
                .antMatchers(HttpMethod.POST, ResourceConstant.MOVIES).hasAnyAuthority(RoleConstant.CREATE_MOVIES)
                .antMatchers(HttpMethod.PUT, ResourceConstant.MOVIES + ResourceConstant.ID_RESOURCE).hasAnyAuthority(RoleConstant.UPDATE_MOVIES)
                .antMatchers(HttpMethod.DELETE, ResourceConstant.MOVIES + ResourceConstant.ID_RESOURCE).hasAnyAuthority(RoleConstant.DELETE_MOVIES)

                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.stateless(true);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }


}
