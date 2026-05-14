package com.mlsystem.model;

import jakarta.persistence.*;

// Menandakan kelas ini sebagai entitas DB untuk data hero
@Entity
@Table(name = "tb_hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hero")
    private Long idHero;

    private String namaHero;
    private String role;
    private String gambar; // Teks bebas, misal: "Chou, Kaja, Tigreal"

    // CONSTRUCTOR KOSONG Wajib buat si JPA Hibernate
    // Mengubah objek Java menjadi baris tabel database (ORM - Object Relational Mapping)
    public Hero() {}

    // GETTER & SETTER (Encapsulation)

    public Long getIdHero() {
        return idHero;
    }
    public void setIdHero(Long idHero) {
        this.idHero = idHero;
    }

    public String getNamaHero() {
        return namaHero;
    }
    public void setNamaHero(String namaHero) {
        this.namaHero = namaHero;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getGambar() {
        return gambar;
    }

    public String setGambar() {
        return gambar;
    }
    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}