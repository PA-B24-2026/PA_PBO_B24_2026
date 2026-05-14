package com.mlsystem.service.impl;

import com.mlsystem.model.Hero;
import com.mlsystem.model.TierList;
import com.mlsystem.repository.HeroRepository;
import com.mlsystem.repository.TierListRepository;
import com.mlsystem.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// LAYER IMPLEMENTASI NYATA: Mengisi seluruh fungsi logika bisnis berdasarkan kontrak di Interface
@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private TierListRepository tierListRepository; // Menyuntikkan repositori tier list

    @Override
    public List<Hero> getAllHeroes() {
        // MENEMBUS LAYER: Service memanggil fungsi milik Repository
        return heroRepository.findAll();
    }

    @Override
    public void saveHero(Hero hero) {
        heroRepository.save(hero);
    }

    @Override
    public Hero getHeroById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteHeroById(Long id) {
        heroRepository.deleteById(id);
    }

    @Override
    public void ambilDataHeroDariApiLuar() {
        // Logika mengonsumsi API publik eksternal menggunakan RestTemplate untuk mengisi pool hero otomatis
        // RestTemplate restTemplate = new RestTemplate();
        // String endpoint = "https://api.mobilelegends.com/v1/heroes";
    }

    @Override
    public List<TierList> getAllTierLists() {
        return tierListRepository.findAll();
    }

    @Override
    public void saveTierList(TierList tierList) {
        tierListRepository.save(tierList);
    }
}