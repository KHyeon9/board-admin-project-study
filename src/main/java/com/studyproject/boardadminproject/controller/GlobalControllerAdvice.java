package com.studyproject.boardadminproject.controller;

import com.studyproject.boardadminproject.dto.security.BoardAdminPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserDetailsToModel(Model model) {
        // 인증이 되었는지 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증이 null이 아니고 인증이 되었는지 확인
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String nickname = ((BoardAdminPrincipal) principal).nickname();
            model.addAttribute("nickname", nickname);
        }
    }
}
