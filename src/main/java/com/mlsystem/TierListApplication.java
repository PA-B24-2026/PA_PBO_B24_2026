package com.mlsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Ini "Sakti", fungsinya mengatur semua konfigurasi otomatis
public class TierListApplication {

    public static void main(String[] args) {
        // Ini perintah untuk menyalakan mesin server
        SpringApplication.run(TierListApplication.class, args);
    }
}