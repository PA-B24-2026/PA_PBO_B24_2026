package com.mlsystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//inheritance: Kelas pewarisan anak dari TierList (tl kompe)
@Entity
@DiscriminatorValue("COMPETITIVE")
public class CompetitiveTierList extends TierList {

    // polymorfisme: method abstract dengan warna merah biar kek turnamen lah
    @Override
    public String getTemaWarnaKanvas() {
        return "bg-red-950 border-red-500 text-red-200";
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
