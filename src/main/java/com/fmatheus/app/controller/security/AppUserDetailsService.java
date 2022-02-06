package com.fmatheus.app.controller.security;

import com.fmatheus.app.controller.rule.MessageResponseRule;
import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class AppUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);

    @Autowired
    private MessageResponseRule messageResponseRule;

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Username: {}", username);
        var user = this.userService.findByUsername(AppUtil.removeAllSpaces(username)).orElseThrow(() -> this.messageResponseRule.usernameNotFoundException());

        if (user.getUserStatus().getId() != 1) {
            logger.error("Usuario inativo: {}", username);
            throw this.messageResponseRule.userInactiveException();
        }
        return new UserSecurity(user, this.authorities(user));
    }

    private Collection<? extends GrantedAuthority> authorities(User user) {
        Set<SimpleGrantedAuthority> authoritys = new HashSet<>();
        user.getRoleCollection().forEach(p -> authoritys.add(new SimpleGrantedAuthority(p.getRoleUser().toUpperCase())));
        return authoritys;
    }

}
