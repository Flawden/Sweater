package ca.flawden.Sweater.controller;

import ca.flawden.Sweater.entity.User;
import ca.flawden.Sweater.repos.UserRepository;
import ca.flawden.Sweater.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }

}
