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

    // Pages Mapping
    @GetMapping("/") public String home() { return "index"; }
    @GetMapping("/about") public String about() { return "about"; }
    @GetMapping("/events") public String events() { return "events"; }
    @GetMapping("/team") public String team() { return "team"; }
    @GetMapping("/gallery") public String gallery() { return "gallery"; }
    @GetMapping("/contact") public String contact() { return "contact"; }
    @GetMapping("/login") public String loginPage() { return "login"; }
    @GetMapping("/dashboard") public String dashboard() { return "dashboard"; }

    // Registration Handler
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Registration reg, RedirectAttributes redirectAttributes) {
        System.out.println("NEW REGISTRATION: " + reg.getFullName() + " for " + reg.getEvent());
        redirectAttributes.addFlashAttribute("message", "Thank you " + reg.getFullName() + "! Registration successful.");
        return "redirect:/contact";
    }

    // Admin Login Handler
    @PostMapping("/core-login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "mit123".equals(password)) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid Member ID or Access Key!");
            return "login";
        }
    }
}
