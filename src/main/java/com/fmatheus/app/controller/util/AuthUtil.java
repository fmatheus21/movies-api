package com.fmatheus.app.controller.util;

import com.fmatheus.app.controller.rule.MessageResponseRule;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public final class AuthUtil {

    private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageResponseRule messageResponseRule;


    public static boolean hasAnyAuthority(Authentication auth, String... roles) {

        if (Objects.isNull(auth)) {
            return false;
        }


        for (GrantedAuthority grant : auth.getAuthorities()) {
            for (String role : roles) {
                if (grant.getAuthority().equalsIgnoreCase(role))
                    return true;
            }
        }
        return false;
    }

    public User validAuthenticationRequest(int id, Authentication auth) {

        logger.warn("Verificando se o usuario tem permissao para esta acao.");

        var user = this.userService.findById(id).orElseThrow(
                () -> this.messageResponseRule.badRequestErrorUserNotfound()
        );

        if (!user.getUsername().equals(auth.getPrincipal())) {
            logger.error("Acesso negado!");
            throw this.messageResponseRule.forbiddenException();
        }

        logger.info("Usuario autorizado.");

        return user;

    }


    public User findByUsername(Authentication auth) {
        return this.userService.findByUsername(auth.getPrincipal().toString()).orElseThrow(
                () -> this.messageResponseRule.badRequestErrorUserNotfound()
        );
    }


}