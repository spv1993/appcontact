package edu.springproject.appcontact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class MainController {
 
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("greeting", "Hello Spring MVC");
        return "helloworld";
    }

}
