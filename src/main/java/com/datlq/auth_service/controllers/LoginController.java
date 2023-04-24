package com.datlq.auth_service.controllers;

import com.datlq.auth_service.dto.UserDto;
import com.datlq.auth_service.exception.UserException;
import com.datlq.auth_service.models.User;
import com.datlq.auth_service.requests.LoginRequest;
import com.datlq.auth_service.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {

        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@Valid @ModelAttribute("loginRequest")
                                  LoginRequest loginRequest,
                              BindingResult bindingResult,
                              HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        User user;
        try {
            user = userService.login(loginRequest.email(), loginRequest.password());
            httpSession.setAttribute("user", new UserDto(user.getId(), user.getEmail(), user.getFullname()));
            return "redirect:/";

        } catch (UserException ex) {
            System.out.println(ex.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @GetMapping("/foo")
    public String foo() {
        throw new UserException("Foo error");
    }
}
