package ee.learn.webstore.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    String constraintError(Model model, ConstraintViolationException e) {
        String message = "";
        for(ConstraintViolation v : e.getConstraintViolations()) {
            message = message + v.getMessage();
        }
        model.addAttribute("message", message);
        return "constraintError";
    }

}
