package com.mlsystem.service;

import com.mlsystem.model.*;
import com.mlsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class HeroServiceImpl implements HeroService {
    @Autowired private HeroRepository heroRepo;
    @Autowired private TierListRepository tierRepo;

    @Override
    public List<Hero> getAllHeroes() { return heroRepo.findAll(); }

    @Override
    public List<TierList> getAllTiers() { return tierRepo.findAll(); }

    @Override
    public void saveTier(TierList tier) { tierRepo.save(tier); }

    @Override
    @SuppressWarnings("unchecked")
    public void syncFromApi() {
        System.out.println("=============================================");
        System.out.println("[LOG] MEMULAI PROSES SINKRONISASI API...");
        System.out.println("=============================================");

        org.springframework.web.client.RestTemplate rest = new org.springframework.web.client.RestTemplate();
        String url = "https://openmlbb.fastapicloud.dev/api/heroes?size=150&index=1&order=desc&lang=en";

        try {
            // BYPASS: Tambah Header User-Agent biar ga diblokir sama hostingan API-nya
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);

            // Ambil data mentah berupa String dulu buat kita intip di konsol
            org.springframework.http.ResponseEntity<String> rawResponse = rest.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class);
            System.out.println("[LOG] KONEKSI BERHASIL! Data mentah dari API:");
            System.out.println(rawResponse.getBody().substring(0, Math.min(rawResponse.getBody().length(), 500)) + "... (dipotong biar ga kepanjangan)");

            // FIX 1: Ambil sebagai Map.class karena root JSON adalah Object {} bukan Array []
            org.springframework.http.ResponseEntity<Map> response = rest.exchange(url, org.springframework.http.HttpMethod.GET, entity, Map.class);
            Map<String, Object> rootResponse = response.getBody();

            if (rootResponse != null && rootResponse.get("data") != null) {
                // FIX 2: Masuk ke root -> data -> records
                Map<String, Object> dataResponse = (Map<String, Object>) rootResponse.get("data");
                List<Map<String, Object>> records = (List<Map<String, Object>>) dataResponse.get("records");

                if (records != null && !records.isEmpty()) {
                    System.out.println("[LOG] Total jumlah hero terdeteksi dari API: " + records.size());
                    int dataBaruTerbawa = 0;

                    // Iterasi isi array "records"
                    for (Map<String, Object> record : records) {
                        // Masuk ke record -> data
                        Map<String, Object> recordData = (Map<String, Object>) record.get("data");
                        if (recordData == null) continue;

                        // Masuk ke record -> data -> hero
                        Map<String, Object> heroWrapper = (Map<String, Object>) recordData.get("hero");
                        if (heroWrapper == null) continue;

                        // Masuk ke record -> data -> hero -> data (Di sini tempat name & head berada)
                        Map<String, Object> heroData = (Map<String, Object>) heroWrapper.get("data");
                        if (heroData == null) continue;

                        // Mengambil data asli sesuai struktur objek API
                        String nama = (String) heroData.get("name");
                        String gambar = (String) heroData.get("head"); // Menggunakan 'head' untuk avatar wajah hero

                        if (nama != null) {
                            // Cek apakah hero sudah ada di DB biar ga duplikat
                            if (heroRepo.findByNamaHero(nama) == null) {
                                Hero h = new Hero();
                                h.setNamaHero(nama);
                                h.setGambar(gambar);
                                h.setRole("Unset");
                                heroRepo.save(h); // Simpan ke DB
                                dataBaruTerbawa++;
                            }
                        }
                    }
                    System.out.println("[LOG] Proses loop selesai. " + dataBaruTerbawa + " data hero baru berhasil masuk DB.");
                } else {
                    System.out.println("[LOG] Peringatan: API merespon, tetapi list records kosong.");
                }
            } else {
                System.out.println("[LOG] Peringatan: Objek 'data' utama tidak ditemukan pada respon API.");
            }

        } catch (Exception e) {
            System.err.println("[LOG ERROR] Terjadi kegagalan saat hit API eksternal!");
            System.err.println("Pesan Eror: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("=============================================");
        System.out.println("[LOG] SINKRONISASI API SELESAI");
        System.out.println("=============================================");
    }

    @Override
    public void saveHero(Hero hero) {
        heroRepo.save(hero);
    }

    @Override
    public Hero getHeroById(Long id) {
        return heroRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteHeroById(Long id) {
        heroRepo.deleteById(id);
    }

    @Override
    public void saveTierList(TierList tierList) {
        tierRepo.save(tierList);
    }
}