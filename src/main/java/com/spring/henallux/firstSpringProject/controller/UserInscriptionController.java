package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDAO;
import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.service.HobbiesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.spring.henallux.firstSpringProject.constants.Constants;

import javax.validation.Valid;

@Controller
@RequestMapping(value="hello/userInscription")
@SessionAttributes({Constants.CURRENT_USER})
public class UserInscriptionController {

    private final UserDAO userDAO;
    private final HobbiesServices hobbiesServices;
    private final GiftController giftController;

    @Autowired
    public UserInscriptionController(HobbiesServices hobbiesServices, GiftController giftController, UserDAO userDAO) {
        this.hobbiesServices = hobbiesServices;
        this.giftController = giftController;
        this.userDAO = userDAO;  // Injection de UserDAO
    }

    @ModelAttribute(Constants.CURRENT_USER)
    public User user(){
        return new User();
    }

    @RequestMapping(method=RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", "Inscription page");
        model.addAttribute("hobbies", hobbiesServices.getHobbies());
        model.addAttribute("user", new User());
        return "integrated:userInscription";
    }

    @RequestMapping(value="/sendInscription", method = RequestMethod.POST)
    public String getFormData(Model model, @Valid @ModelAttribute(value=Constants.CURRENT_USER) User user, final BindingResult errors) {
        if(errors.hasErrors()){
            model.addAttribute("hobbies", hobbiesServices.getHobbies());
            return "integrated:userInscription";
        }
        System.out.println(user.getMale());
        System.out.println(userDAO.getChildByAgeAndHobby(10, 30, "Sport"));
        userDAO.save(user);
        return "redirect:/gift";
    }
}
