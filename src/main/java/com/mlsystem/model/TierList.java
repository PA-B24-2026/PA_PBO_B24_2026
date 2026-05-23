package com.mlsystem.model;

import jakarta.persistence.*;

//Abstract Class
@Entity
// Mapping inheritance kedalam satu tabel tunggal di DB
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "jenis_tierlist", discriminatorType = DiscriminatorType.STRING)
@Table(name = "tb_tierlist")
public abstract class TierList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tier")
    private Long idTier;

    @Column(name = "nama_tierlist")
    private String namaTierList;

    @Lob
    @Column(name = "susunan_hero", columnDefinition = "TEXT")
    private String susunanHero; // Menyimpan teks data susunan hero

    public TierList() {}

    //polymorfisme: Method abstract yang wajib di override secara berbeda oleh child class
    public abstract String getTemaWarnaKanvas();

    // polymorfisme untuk tierlist
    // Ngembalikan nilai serius or santai
    public abstract String getJenisKey();
    public abstract String getJenisName();

    //getter dan setter manual
    public Long getIdTier() { return idTier; }
    public void setIdTier(Long idTier) { this.idTier = idTier; }

    public String getNamaTierList() { return namaTierList; }
    public void setNamaTierList(String namaTierList) { this.namaTierList = namaTierList; }

    public String getSusunanHero() { return susunanHero; }
    public void setSusunanHero(String susunanHero) { this.susunanHero = susunanHero; }
}