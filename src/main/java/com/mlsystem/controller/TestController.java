package com.mlsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/") // Artinya: Saat user akses alamat utama (localhost:8080/)
    public String home() {
        return "index"; // Spring akan mencari file 'index.html' di folder templates
    }
}