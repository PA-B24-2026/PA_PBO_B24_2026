package com.mlsystem.model;

import jakarta.persistence.*;

// ABSTRAKSI: Menandakan class ini sebagai Abstract Class (konsep Tier List tanpa multi-user)
@Entity
// Mapping inheritance kedalam satu tabel tunggal di DB
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "jenis_tierlist", discriminatorType = DiscriminatorType.STRING)
@Table(name = "tb_tierlist")
public class TierList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tier")
    private Long idTier;

    @Column(name = "nama_tierlist")
    private String namaTierList; // Isinya referensi dari user kayak Tier Jungle S31

    @Lob
    @Column(name = "susunan_hero", columnDefinition = "TEXT")
    private String susunanHero; // Menyimpan teks data susunan hero contoh S:Fanny,Pernan|A:Freya

    // CONSTRUCTOR KOSONG
    public TierList() {}

    // POLYMORFISME: Method abstract yang wajib di override secara berbeda oleh child class
    public abstract String getTemaWarnaKanvas
    // GETTER & SETTER MANUAL
    public Long getIdTier() { return idTier; }
    public void setIdTier(Long idTier) { this.idTier = idTier; }

    public String getNamaTierList() { return namaTierList; }
    public void setNamaTierList(String namaTierList) { this.namaTierList = namaTierList; }

    public String getSusunanHero() { return susunanHero; }
    public void setSusunanHero(String susunanHero) { this.susunanHero = susunanHero; }
}