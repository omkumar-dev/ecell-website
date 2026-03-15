package com.example.ecell;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        // Detailed logging for Render console
        System.out.println(">>> NEW REGISTRATION DATA <<<");
        System.out.println("Full Name: " + reg.getFullName());
        System.out.println("Email    : " + reg.getEmail());
        System.out.println("Event    : " + reg.getEvent());
        System.out.println("-----------------------------");

        // Flash message for the UI
        redirectAttributes.addFlashAttribute("message", "Thank you " + reg.getFullName() + "! Registration successful.");
        
        // redirect keyword will solve your 'LinkedHashMap' warning
        return "redirect:/contact";
    }

    // --- Core Member Access ---

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/core-login")
    public String handleLogin(@RequestParam String username, 
                              @RequestParam String password, 
                              Model model) {
        
        // Simple authentication check
        if ("admin".equals(username) && "mit123".equals(password)) {
            return "redirect:/dashboard"; 
        } else {
            model.addAttribute("error", "Invalid Member ID or Access Key!");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; 
    }
}
