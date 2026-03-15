package com.example.ecell;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@Controller
public class MainController {

    // 1. Home Page
    @GetMapping("/")
    public String home() { 
        return "index"; 
    }

    // 2. About Page
    @GetMapping("/about")
    public String about() { 
        return "about"; 
    }

    // 3. Events Page
    @GetMapping("/events")
    public String events() { 
        return "events"; 
    }

    // 4. Team Page
    @GetMapping("/team")
    public String team() { 
        return "team"; 
    }

    // 5. Gallery Page
    @GetMapping("/gallery")
    public String gallery() { 
        return "gallery"; 
    }

    // 6. Contact Page
    @GetMapping("/contact")
    public String contact() { 
        return "contact"; 
    }

    // 7. Handle Form Submission (Registration)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Registration reg, Model model) {
        // Form ka data console par dikhega
        System.out.println("New Agri Start UP Registration:");
        System.out.println("Name: " + reg.getFullName());
        System.out.println("Email: " + reg.getEmail());
        
        model.addAttribute("message", "Thank you, " + reg.getFullName() + "! We will contact you soon.");
        return "contact"; // Form submit hone ke baad wapas contact page par bhej dega
    }
}
