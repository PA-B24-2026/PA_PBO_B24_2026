package com.mlsystem.service;

import com.mlsystem.model.Hero;
import com.mlsystem.model.TierList;
import java.util.List;

// LAYER KONTRAK INTERFACE: Menentukan standar operasional tanpa menulis logika teknisnya
public interface HeroService {

    // Kontrak fungsi manajemen master hero
    List<Hero> getAllHeroes();
    void saveHero(Hero hero);
    Hero getHeroById(Long id);
    void deleteHeroById(Long id);
    void ambilDataHeroDariApiLuar(); // Pengambilan data external API sesuai revisi

    // Kontrak fungsi manajemen hasil kreasi sandbox Tier Maker global
    List<TierList> getAllTierLists();
    void saveTierList(TierList tierList);
}