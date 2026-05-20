package com.mlsystem.service;

import com.mlsystem.model.*;
import com.mlsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroRepository heroRepo;

    @Autowired
    private TierListRepository tierRepo;

    @Override
    public List<Hero> getAllHeroes() {
        return heroRepo.findAllManual();
    }

    @Override
    public List<TierList> getAllTiers() {
        return tierRepo.findAllByOrderByIdTierDesc();
    }

    @Override
    public void deleteTierListById(Long id) {
        tierRepo.deleteManualById(id);
    }

    @Override
    public void saveHero(Hero hero) {
        heroRepo.insertHeroManual(hero.getNamaHero(), hero.getRole(), "MANUAL", hero.getGambar());
    }

    @Override
    public void saveTierList(TierList tierList) {
        String jenisString = (tierList instanceof CompetitiveTierList) ? "COMPETITIVE" : "CASUAL";

        if (tierList.getIdTier() != null) {
            tierRepo.updateManual(tierList.getIdTier(), tierList.getNamaTierList(), tierList.getSusunanHero(), jenisString);
        } else {
            tierRepo.insertManual(tierList.getNamaTierList(), tierList.getSusunanHero(), jenisString);
        }
    }

    // FIXED 1: Implementasi getHeroById menggunakan Query murni SQL manual
    @Override
    public Hero getHeroById(Long id) {
        return heroRepo.findHeroByIdManual(id);
    }

    @Override
    public void deleteHeroById(Long id) {
        heroRepo.deleteHeroManualById(id);
    }

    // FIXED 2: Fungsi Sync API diaktifkan kembali dan menggunakan query native manual
    @Override
    @SuppressWarnings("unchecked")
    public void syncFromApi() {
        System.out.println("=============================================");
        System.out.println("[LOG] MEMULAI PROSES SINKRONISASI API...");
        System.out.println("=============================================");

        org.springframework.web.client.RestTemplate rest = new org.springframework.web.client.RestTemplate();
        String url = "https://openmlbb.fastapicloud.dev/api/heroes?size=150&index=1&order=desc&lang=en";

        try {
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);

            org.springframework.http.ResponseEntity<Map> response = rest.exchange(url, org.springframework.http.HttpMethod.GET, entity, Map.class);
            Map<String, Object> rootResponse = response.getBody();

            if (rootResponse != null && rootResponse.get("data") != null) {
                Map<String, Object> dataResponse = (Map<String, Object>) rootResponse.get("data");
                List<Map<String, Object>> records = (List<Map<String, Object>>) dataResponse.get("records");

                if (records != null && !records.isEmpty()) {
                    for (Map<String, Object> record : records) {
                        Map<String, Object> recordData = (Map<String, Object>) record.get("data");
                        if (recordData == null) continue;

                        Map<String, Object> heroWrapper = (Map<String, Object>) recordData.get("hero");
                        if (heroWrapper == null) continue;

                        Map<String, Object> heroData = (Map<String, Object>) heroWrapper.get("data");
                        if (heroData == null) continue;

                        String nama = (String) heroData.get("name");
                        String gambar = (String) heroData.get("head");

                        // Memakai fungsi pengecekan duplikat dan insert manual murni
                        if (nama != null && heroRepo.findByNamaHeroManual(nama) == null) {
                            heroRepo.insertHeroManual(nama, "Unset", "API", gambar);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[LOG ERROR] Gagal sync API: " + e.getMessage());
        }
        System.out.println("[LOG] SINKRONISASI API SELESAI");
    }
}