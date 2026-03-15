package com.example.ecell;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; // Login parameters ke liye zaroori hai
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    // --- Public Pages ---

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

    // --- Registration Logic ---

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Registration reg, RedirectAttributes redirectAttributes) {
        System.out.println("New Registration: " + reg.getFullName());
        redirectAttributes.addFlashAttribute("message", "Thank you " + reg.getFullName() + "! Your registration is successful.");
        return "redirect:/contact";
    }

    // --- Core Member Access (Login & Dashboard) ---

    // Login Page dikhane ke liye
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Login logic handle karne ke liye
    @PostMapping("/core-login")
    public String handleLogin(@RequestParam String username, 
                              @RequestParam String password, 
                              Model model) {
        
        // Simple logic: username "admin" aur password "mit123"
        // Aap ise baad mein badal sakte hain
        if ("admin".equals(username) && "mit123".equals(password)) {
            return "redirect:/dashboard"; 
        } else {
            model.addAttribute("error", "Invalid Member ID or Access Key!");
            return "login";
        }
    }

    // Core Member Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; 
    }
}
