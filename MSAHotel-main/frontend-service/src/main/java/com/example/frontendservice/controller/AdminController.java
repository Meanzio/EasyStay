package com.example.frontendservice.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
@Controller
@RequestMapping("/admin")  // 기본 경로 prefix 추가
public class AdminController {

    @Value("${api.gateway.url}")
    private String gatewayUrl;

    @GetMapping("/login")
    public String AdminLogin(Model model) {
        return "/users/hello";
    }

}
