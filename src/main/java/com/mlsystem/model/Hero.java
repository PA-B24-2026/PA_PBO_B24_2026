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

    @Column(name = "nama_hero")
    private String namaHero;

    @Column(name = "role")
    private String role;

    @Column(name = "counter")
    private String counter; // Teks bebas, misal: "Chou, Kaja, Tigreal"

    @Column(name = "sinergi")
    private String sinergi; // Teks bebas, misal: "Atlas, Mathilda"

    @Column(name = "gambar")
    private String gambar; // misal: "/assets/heroes/chou.png"

    // CONSTRUCTOR KOSONG Wajib buat si JPA Hibernate
    // Mengubah objek Java menjadi baris tabel database (ORM - Object Relational Mapping)
    public Hero() {}

    // CONSTRUCTOR LENGKAP Biar mudah kita handle
    public Hero(Long idHero, String namaHero, String role,
                String counter, String sinergi, String gambar) {
        this.idHero = idHero;
        this.namaHero = namaHero;
        this.role = role;
        this.counter = counter;
        this.sinergi = sinergi;
        this.gambar = gambar;
    }

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

    public String getCounter() {
        return counter;
    }
    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getSinergi() {
        return sinergi;
    }
    public void setSinergi(String sinergi) {
        this.sinergi = sinergi;
    }

    public String getGambar() {
        return gambar;
    }
    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "idHero=" + idHero +
                ", namaHero='" + namaHero + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}