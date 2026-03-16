package com.example.ecell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private RegistrationRepository repo;

    @GetMapping("/") public String home() { return "index"; }
    @GetMapping("/about") public String about() { return "about"; }
    @GetMapping("/events") public String events() { return "events"; }
    @GetMapping("/team") public String team() { return "team"; }
    @GetMapping("/gallery") public String gallery() { return "gallery"; }
    @GetMapping("/contact") public String contact() { return "contact"; }
    @GetMapping("/login") public String loginPage() { return "login"; }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Registration reg, RedirectAttributes ra) {
        repo.save(reg); 
        ra.addFlashAttribute("message", "Registration Successful!");
        return "redirect:/contact";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("registrations", repo.findAll());
        return "dashboard";
    }

    @GetMapping("/dashboard/download")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=registrations.csv");
        response.getWriter().println("ID,Name,Email,Event");
        List<Registration> list = repo.findAll();
        for (Registration reg : list) {
            response.getWriter().println(reg.getId() + "," + reg.getFullName() + "," + reg.getEmail() + "," + reg.getEvent());
        }
    }

    @PostMapping("/core-login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "mit123".equals(password)) return "redirect:/dashboard";
        model.addAttribute("error", "Wrong Credentials!");
        return "login";
    }
}
