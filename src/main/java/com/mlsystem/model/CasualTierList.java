package com.mlsystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

// INHERITANCE: Kelas anak pewarisan dari TierList khusus untuk referensi seru-seruan / fun match
@Entity
@DiscriminatorValue("CASUAL")
public class CasualTierList extends TierList {

    // POLYMORFISME: Mengimplementasikan method abstract dengan warna biru santai / casual
    @Override
    public String getTemaWarnaKanvas() {
        return "bg-blue-950 border-blue-500 text-blue-200";
    }

    @Override
    public String getJenisKey(){
        return "serius";
    }

    @Override
    public String getJenisName(){
        return "Competitive";
    }
}