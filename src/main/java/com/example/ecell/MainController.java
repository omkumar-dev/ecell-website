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

    // --- Public Pages ---
    @GetMapping("/") public String home() { return "index"; }
    @GetMapping("/about") public String about() { return "about"; }
    @GetMapping("/events") public String events() { return "events"; }
    @GetMapping("/team") public String team() { return "team"; }
    @GetMapping("/gallery") public String gallery() { return "gallery"; }
    @GetMapping("/contact") public String contact() { return "contact"; }
    @GetMapping("/login") public String loginPage() { return "login"; }

    // DYNAMIC SAVE: Database mein entry save karne ke liye
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Registration reg, RedirectAttributes ra) {
        repo.save(reg); 
        ra.addFlashAttribute("message", "Registration Successful for " + reg.getFullName());
        return "redirect:/contact";
    }

    // DYNAMIC VIEW: Dashboard par data dikhane ke liye
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Registration> allRegs = repo.findAll();
        model.addAttribute("registrations", allRegs);
        return "dashboard";
    }

    // DYNAMIC DOWNLOAD: CSV/Excel format mein data download karne ke liye
    @GetMapping("/dashboard/download")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=registrations.csv");

        List<Registration> list = repo.findAll();
        
        // CSV Header
        response.getWriter().println("ID,Full Name,Email,Event");

        // CSV Data Rows
        for (Registration reg : list) {
            response.getWriter().println(
                reg.getId() + "," + 
                reg.getFullName() + "," + 
                reg.getEmail() + "," + 
                reg.getEvent()
            );
        }
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
