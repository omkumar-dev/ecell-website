package com.example.ecell;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping("/about")
    public String about() { return "about"; }

    @GetMapping("/events")
    public String events() { return "events"; }

    @GetMapping("/team")
    public String team() { return "team"; }

    @GetMapping("/gallery")
    public String gallery() { return "gallery"; }

    @GetMapping("/contact")
    public String contact() { return "contact"; }

    // Updated Register Method
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Registration reg, RedirectAttributes redirectAttributes) {
        // Console par data print karne ke liye
        System.out.println("====== New Registration ======");
        System.out.println("Name: " + reg.getFullName());
        System.out.println("Email: " + reg.getEmail());
        System.out.println("Event: " + reg.getEvent());
        System.out.println("==============================");

        // FlashAttribute use karne se page refresh hone par data loss nahi hota
        redirectAttributes.addFlashAttribute("message", "Thank you " + reg.getFullName() + "! Your registration is successful.");
        
        // Form submit hone ke baad wapas contact page par bhej dega (Safe Method)
        return "redirect:/contact"; 
    }
}
