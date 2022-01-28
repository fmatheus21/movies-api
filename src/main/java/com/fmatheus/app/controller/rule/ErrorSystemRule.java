package com.fmatheus.app.controller.rule;

import com.fmatheus.app.model.entity.ErrorSystem;
import com.fmatheus.app.model.service.ErrorSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ErrorSystemRule {

    private static final Logger logger = LoggerFactory.getLogger(ErrorSystemRule.class);

    @Autowired
    private ErrorSystemService errorSystemService;

    public void save(Exception e) {
      var className = Thread.currentThread().getStackTrace()[2].getClassName();
        var methodName = Thread.currentThread().getStackTrace()[2].getMethodName(); //Pega o método que chamou
        var message = e.getMessage();

        var error = ErrorSystem.builder()
                .nameClass(className)
                .nameMethod(methodName)
                .message(message)
                .build();

        logger.info("Registrando erro do sistema.");
        this.errorSystemService.save(error);

    }

}
