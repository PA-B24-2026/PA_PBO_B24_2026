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
        // FIX: Mengirimkan 5 parameter sesuai cetak biru baru repository kita
        heroRepo.insertHeroManual(
                hero.getNamaHero(),
                "UNSET",
                "MANUAL",
                "/images/default-hero.png", // Masuk ke kolom 'gambar' asli (cadangan)
                hero.getGambarKustom()      // Masuk ke kolom 'gambar_kustom'
        );
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

    @Override
    public Hero getHeroById(Long id) {
        return heroRepo.findHeroByIdManual(id);
    }

    @Override
    public void deleteHeroById(Long id) {
        heroRepo.deleteHeroManualById(id);
    }

    @Override
    public void updateGambarHeroManual(Long id, String gambar) {
        heroRepo.updateGambarHeroManual(id, gambar);
    }

    // FIX IMPLEMENTASI: Menambahkan override clearGambarKustom bawaan interface
    @Override
    public void clearGambarKustom(Long id) {
        heroRepo.clearGambarKustomManual(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void syncFromApi() {
        // Cek dulu ke database, apakah sudah ada data heronya?
        long jumlahHeroDiDb = heroRepo.count();

        // KONDISI UX PINTAR: Jika DB sudah ada isinya, stop di sini! Gak usah tembak API luar lagi.
        if (jumlahHeroDiDb > 0) {
            System.out.println("[LOG] Data DB aman (Terisi " + jumlahHeroDiDb + " hero). Melewati sync API.");
            return;
        }

        // JIKA DB KOSONG (Baru beres truncate / pertama kali install), BARU JALANKAN PROSES DI BAWAH:
        System.out.println("=============================================");
        System.out.println("[LOG] DB KOSONG! OTOMATIS MENARIK DATA API...");
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

                        // Masukkan ke database dengan urutan 5 parameter aman kita
                        if (nama != null && heroRepo.findByNamaHeroManual(nama) == null) {
                            heroRepo.insertHeroManual(nama, "Unset", "API", gambar, null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[LOG ERROR] Gagal sync otomatis: " + e.getMessage());
        }
        System.out.println("[LOG] SINKRONISASI OTOMATIS SELESAI.");
    }
}