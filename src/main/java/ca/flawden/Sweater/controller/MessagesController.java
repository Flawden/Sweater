package ca.flawden.Sweater.controller;

import ca.flawden.Sweater.entity.Message;
import ca.flawden.Sweater.entity.User;
import ca.flawden.Sweater.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/messages")
public class MessagesController {

    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public MessagesController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("")
    public String messages(@ModelAttribute("message") Message message, Model model) {
        model.addAttribute("file");
        return "addMessages";
    }

    @PostMapping("")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @ModelAttribute("message") Message message,
            @RequestParam("file") MultipartFile file) {

        System.out.println(file.getOriginalFilename());

        if (file != null) {
            File uploadDir = new File(uploadPath + "/" + user.getUsername());
            System.out.println(uploadDir.getAbsolutePath());
            if(!uploadDir.exists()) {
                boolean a = uploadDir.mkdir();
                System.out.println(a);
            }
            System.out.println(uploadDir.getAbsolutePath());
            String uuidFile = UUID.randomUUID().toString();
            String filename = uuidFile + "_" + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + user.getUsername() + "/" + filename));
            } catch (IOException e) {
                System.out.println("Uups");
            }

            message.setFilename(filename);
        }

        message.setAuthor(user);
        messageRepository.save(message);
        return "redirect:/messages/messagesList";
    }

    @PostMapping("/filter")
    public String findByTag(@RequestParam String filter, Model model) {

        List<Message> messages;

        if(filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        return "message_list";
    }

    @GetMapping("/messagesList")
    public String messages(Model model) {
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "message_list";
    }

}
