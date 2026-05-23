package com.mlsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //pengatur semua konfigurasi otomatis
public class TierListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TierListApplication.class, args); //nyalakan mesin server
    }
}