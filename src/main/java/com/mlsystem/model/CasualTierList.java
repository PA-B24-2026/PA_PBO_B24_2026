package com.mlsystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//inheritance: Kelas anak pewarisan dari TierList (tl casual)
@Entity
@DiscriminatorValue("CASUAL")
public class CasualTierList extends TierList {

    //polymorfisme: method abstract dengan warna biru santai
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