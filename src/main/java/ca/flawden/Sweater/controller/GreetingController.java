package ca.flawden.Sweater.controller;

import ca.flawden.Sweater.entity.Message;
import ca.flawden.Sweater.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class GreetingController {
    
    @GetMapping("/")
    public String greeting(Principal principal, Model model) {
        if (principal == null) {
            model.addAttribute("name", "World");
        }
        else {
            model.addAttribute("name", principal.getName());
        }
        return "greeting";
    }

}
