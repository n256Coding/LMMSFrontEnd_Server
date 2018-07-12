package com.n256coding.frontend.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @GetMapping("/")
    public String loadDefaultIndexPage() {
        return "index";
    }

    @GetMapping({"/text-request",
            "/text-response",
            "/video",
            "/video/processing",
            "/moodle",
            "/moodle-result",
            "/slide",
            "/report",
            "/customReportSlide",
            "/adminTemplate",
            "/home",
            "/loginPage",
            "/register"})
    public String loadIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    private String loadLoginPage(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return "redirect:/login?logout";
        }
        return "home";
    }



}
