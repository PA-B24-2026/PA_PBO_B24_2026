package com.mlsystem.service;

import com.mlsystem.model.Hero;
import com.mlsystem.model.TierList;
import java.util.List;

public interface HeroService {
    // Method untuk Hero
    List<Hero> getAllHeroes();
    void saveHero(Hero hero);
    Hero getHeroById(Long id);
    void deleteHeroById(Long id);

    void updateGambarHeroManual(Long id, String gambar);

    // Method untuk Tier List (Disamakan dengan kebutuhan Controller)
    List<TierList> getAllTiers();
//    void saveTier(TierList tier);
    void saveTierList(TierList tierList);
    void deleteTierListById(Long id);

    // Method Sync API
    void syncFromApi();
}