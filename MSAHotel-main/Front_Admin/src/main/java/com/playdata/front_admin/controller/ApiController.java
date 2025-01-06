package com.playdata.front_admin.controller;

import org.springframework.web.bind.annotation.RestController;

import com.playdata.front_admin.entity.User;
import com.playdata.front_admin.security.JwtUtil;
import com.playdata.front_admin.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public ApiController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String getMethodName(@RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "password", required = true) String password) {

        User user = userService.findByEmail(email);

        if (!user.getPassword().equals(password)) {
            throw new IllegalAccessError("패스워드가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}
