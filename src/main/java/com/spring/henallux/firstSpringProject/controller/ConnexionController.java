package com.spring.henallux.firstSpringProject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/connexion")
public class ConnexionController {

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", "Sign in");
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", false);
        return "integrated:connexion";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkAuthentication(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        response.put("isAuthenticated", authentication != null && authentication.isAuthenticated());
        response.put("username", authentication != null ? authentication.getName() : null);
        return response;
    }
}
