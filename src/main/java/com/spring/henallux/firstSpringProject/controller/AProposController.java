package com.spring.henallux.firstSpringProject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/a-propos")
public class AProposController {
    @RequestMapping(method= RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", "Who we are ?");
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", true);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Customer saved: " + authentication);
        return "integrated:aPropos";
    }
}
