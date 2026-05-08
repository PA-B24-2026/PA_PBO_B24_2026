package com.mlsystem.model;

public class Hero {

    // ===== PRIVATE VARIABLES (Encapsulation) =====
    private Long id;
    private String name;
    private String role;        // Marksman, Mage, Tank, Fighter, Support, Assassigitn
    private String tier;        // S, A, B, C, D
    private String imageUrl;    // Path ke foto hero
    private String description;

    // ===== CONSTRUCTOR KOSONG (wajib untuk Spring/JPA) =====
    public Hero() {}

    // ===== CONSTRUCTOR LENGKAP =====
    public Hero(Long id, String name, String role, String tier,
                String imageUrl, String description) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.tier = tier;
        this.imageUrl = imageUrl;
    }

    // ===== GETTER & SETTER =====

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getTier() {
        return tier;
    }
    public void setTier(String tier) {
        if (tier != null && tier.matches("[SABCDsabcd]")) {
            this.tier = tier.toUpperCase();
        } else {
            throw new IllegalArgumentException("Tier tidak valid: " + tier);
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    // ===== toString() untuk debugging =====
    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", tier='" + tier + '\'' +
                '}';
    }
}