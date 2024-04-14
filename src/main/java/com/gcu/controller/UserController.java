package com.gcu.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.repository.User;
import com.gcu.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class UserController {
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String listUsers(Model model) {
    	logger.info("Entering ListUsers method");
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        logger.info("Exiting listUsers method");
        return "list";
    }
    @GetMapping("/form")
    public String showUserForm() {
    	logger.info("Entering showUserForm method");
        return "form";
    }
    @PostMapping("/save")
    public String saveUser(@RequestParam String firstName, @RequestParam String lastName) {
    	logger.info("Entering saveUser method");
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
        logger.info("Exiting saveUser method");
        return "redirect:list";
    }
    
   
    
}

