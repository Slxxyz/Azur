package com.spring.henallux.firstSpringProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/panier")
public class PanierController {

    @RequestMapping(method = RequestMethod.GET)
    public String panier(Model model) {
        model.addAttribute("title", "Panier");
        return "integrated:panier";
    }
}
