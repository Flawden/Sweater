package ca.flawden.Sweater.controller;

import ca.flawden.Sweater.entity.User;
import ca.flawden.Sweater.repos.UserRepository;
import ca.flawden.Sweater.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationAndLogin {

    private UserRepository userRepository;
    private UserService userService;

    private RegistrationAndLogin(UserRepository userRepository, UserService userService) {

        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if(userService.addUser(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("message", "User is already exist");
            return "registration";
        }
    }

}
