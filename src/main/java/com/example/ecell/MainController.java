package com.example.ecell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class MainController {

    // Database access ke liye repository connect ki gayi hai
    @Autowired
    private RegistrationRepository repo;

    // --- Public Pages ---
    @GetMapping("/") public String home() { return "index"; }
    @GetMapping("/about") public String about() { return "about"; }
    @GetMapping("/events") public String events() { return "events"; }
    @GetMapping("/team") public String team() { return "team"; }
    @GetMapping("/gallery") public String gallery() { return "gallery"; }
    @GetMapping("/contact") public String contact() { return "contact"; }
    @GetMapping("/login") public String loginPage() { return "login"; }

    // DYNAMIC SAVE: Form data ko database mein save karne ke liye
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Registration reg, RedirectAttributes ra) {
        // Ye line data ko H2 Database mein save karegi
        repo.save(reg); 
        ra.addFlashAttribute("message", "Registration Successful for " + reg.getFullName());
        return "redirect:/contact";
    }

    // DYNAMIC VIEW: Dashboard par database se saara data nikal kar dikhane ke liye
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Registration> allRegs = repo.findAll();
        model.addAttribute("registrations", allRegs);
        return "dashboard";
    }

    // Login logic handle karne ke liye
    @PostMapping("/core-login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "mit123".equals(password)) {
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Wrong Credentials!");
        return "login";
    }
}
