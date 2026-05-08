package com.mlsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tierlist")
public class TierList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tier")
    private Long idTier;

    @Column(name = "lane")
    private String lane; // EXP, GOLD, MID, JUNGLE, ROAM

    @Column(name = "grade_tier")
    private String gradeTier; // S, A, B, C

    // Relasi Many-to-One ke tb_hero
    @ManyToOne
    @JoinColumn(name = "id_hero")
    private Hero hero;

    // CONSTRUCTOR KOSONG
    public TierList() {}

    // CONSTRUCTOR LENGKAP
    public TierList(Long idTier, Hero hero, String lane, String gradeTier) {
        this.idTier = idTier;
        this.hero = hero;
        this.lane = lane;
        setGradeTier(gradeTier); // pakai setter agar validasi berjalan
    }

    // GETTER & SETTER (Encapsulation)

    public Long getIdTier() {
        return idTier;
    }
    public void setIdTier(Long idTier) {
        this.idTier = idTier;
    }

    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public String getLane() {
        return lane;
    }
    public void setLane(String lane) {
        // Validasi lane hanya boleh 5 nilai ini
        if (lane != null && lane.matches("EXP|GOLD|MID|JUNGLE|ROAM")) {
            this.lane = lane.toUpperCase();
        } else {
            throw new IllegalArgumentException("Lane tidak valid: " + lane);
        }
    }

    public String getGradeTier() {
        return gradeTier;
    }
    public void setGradeTier(String gradeTier) {
        // Validasi grade hanya boleh S, A, B, atau C
        if (gradeTier != null && gradeTier.matches("[SABCsabc]")) {
            this.gradeTier = gradeTier.toUpperCase();
        } else {
            throw new IllegalArgumentException("Grade tier tidak valid: " + gradeTier);
        }
    }

    @Override
    public String toString() {
        return "TierList{" +
                "idTier=" + idTier +
                ", hero=" + (hero != null ? hero.getNamaHero() : "null") +
                ", lane='" + lane + '\'' +
                ", gradeTier='" + gradeTier + '\'' +
                '}';
    }
}