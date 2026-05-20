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

    // FIX PARAMETER: Diubah menjadi 1 parameter saja agar klop dengan Controller
    void clearGambarKustom(Long id);

    // Method untuk Tier List (Disamakan dengan kebutuhan Controller)
    List<TierList> getAllTiers();
    void saveTierList(TierList tierList);
    void deleteTierListById(Long id);

    // Method Sync API
    void syncFromApi();
}