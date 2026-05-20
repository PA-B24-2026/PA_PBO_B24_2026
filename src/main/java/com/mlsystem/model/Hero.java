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

    private String role;

    @Column(name = "status") // FIX: Disamakan dengan nama kolom di DB MySQL kamu
    private String statusHero;

    private String gambar; // Teks bebas, misal: "Chou, Kaja, Tigreal"

    @Column(name = "gambar_kustom") // FIX: Disamakan dengan script SQL native fallback kita
    private String gambarKustom;

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

    public String getStatusHero() {
        return statusHero;
    }
    public void setStatusHero(String statusHero) {
        this.statusHero = statusHero;
    }

    // FIX: Getter & Setter properti gambar diisi lengkap biar Thymeleaf tidak lelah mencari
    public String getGambar() {
        return gambar;
    }
    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getGambarKustom() {
        return gambarKustom;
    }
    public void setGambarKustom(String gambarKustom) {
        this.gambarKustom = gambarKustom;
    }
}