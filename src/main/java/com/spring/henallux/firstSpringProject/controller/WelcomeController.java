package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.MagicKeyDataAccess;
import com.spring.henallux.firstSpringProject.model.MagicKeyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping(value="/azur")
public class WelcomeController {

    private final MagicKeyDataAccess magicKeyDataAccess;

    @Autowired
    public WelcomeController(MagicKeyDataAccess magicKeyDataAccess) {
        this.magicKeyDataAccess = magicKeyDataAccess;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", "Accueil");
        return "integrated:welcome";
    }

    @RequestMapping(value="/send", method = RequestMethod.POST)
    public String getFormData(Model model, @ModelAttribute("magicKeyForm") MagicKeyForm magicKeyForm) {
        ArrayList<String> magicKeys = magicKeyDataAccess.getMagicKeys();
        if (magicKeys.contains(magicKeyForm.getMagicKey())) {
            return "redirect:/hello/userInscription";
        } else {
            return "integrated:keyError";
        }
    }
}