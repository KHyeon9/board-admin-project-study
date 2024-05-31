package com.studyproject.boardadminproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String errorHandler(HttpServletResponse response, HttpServletRequest request, Model model) {
        int status = response.getStatus();
        model.addAttribute("status", status);
        model.addAttribute("requestURI", request.getRequestURI());

        if (status >= 400 && status < 500) {
            return "error/4xx";
        } else if (status >= 500) {
            return "error/5xx";
        }
        return "forward:/management/articles";
    }
}
