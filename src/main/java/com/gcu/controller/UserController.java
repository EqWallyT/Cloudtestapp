package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.repository.User;
import com.gcu.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "list";
    }
    @GetMapping("/form")
    public String showUserForm() {
        return "form";
    }
    @PostMapping("/save")
    public String saveUser(@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
        return "redirect:list";
    }
    
   
    
}

