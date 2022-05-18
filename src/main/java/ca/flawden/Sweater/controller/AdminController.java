package ca.flawden.Sweater.controller;

import ca.flawden.Sweater.entity.Message;
import ca.flawden.Sweater.entity.Role;
import ca.flawden.Sweater.entity.User;
import ca.flawden.Sweater.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String userList(Model model, Principal principal) {
        model.addAttribute("users", userRepository.findAll());
        if(principal != null) {
            model.addAttribute("name", principal.getName());
        }

        return "user_list";
    }

    @GetMapping("users/{user}")
    public String userEditForm(@PathVariable User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        if(principal != null) {
            model.addAttribute("name", principal.getName());
        }
        for(Role role: Role.values()) {
            System.out.println(role);
            if (user.getRoles().contains(role)) {
                System.out.println("Role: " + role + " is exists for this user");
            } else
            {
                System.out.println("Role: " + role + " isn't exists for this user");
            }

        }

        return "user_edit";
    }

//    @PostMapping("admin/user/user_filter")
//    public String userFilter(@RequestParam String filter, Model model) {
//
//        List<User> messages;
//
//        if(filter != null && !filter.isEmpty()) {
//            messages = userRepository.findAllByByUsername(filter);
//        } else {
//            messages = userRepository.findAll();
//        }
//        model.addAttribute("messages", messages);
//
//        return "user_edit";
//    }

    @PostMapping("/users/{user}/edit")
    public String user2Save(@ModelAttribute("user") User user, @RequestParam(value = "userRoles", required = false)List<String> roles) {

        user.getRoles().clear();

        if (roles != null) {
            for(String str: roles) {
                if (roles.contains(str)) {
                    user.getRoles().add(Role.valueOf(str));
                }
            }
        }

        userRepository.save(user);
        return "redirect:/";
    }

}
