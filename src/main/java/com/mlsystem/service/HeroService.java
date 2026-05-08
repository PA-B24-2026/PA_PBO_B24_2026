package com.mlsystem.service;

import com.mlsystem.model.Hero;
import com.mlsystem.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Menandakan bahwa class ini adalah "Service" atau otak logika bisnis
public class HeroService {

    @Autowired // Konsep "Dependency Injection": Spring Boot otomatis menyuntikkan HeroRepository ke sini tanpa kita harus membuat objek baru (new HeroRepository)
    private HeroRepository heroRepository;

    // 1. Fungsi untuk mengambil SEMUA data hero
    public List<Hero> getAllHeroes() {
        return heroRepository.findAll(); // findAll() adalah fungsi warisan (Inheritance) dari JpaRepository
    }

    // 2. Fungsi untuk menyimpan data hero baru (Create / Update)
    public void saveHero(Hero hero) {
        heroRepository.save(hero);
    }

    // 3. Fungsi untuk mencari hero berdasarkan ID (Read 1 data)
    public Hero getHeroById(Long id) {
        // orElse(null) artinya jika ID tidak ditemukan di database, maka kembalikan nilai kosong (null)
        return heroRepository.findById(id).orElse(null);
    }

    // 4. Fungsi untuk menghapus hero (Delete)
    public void deleteHeroById(Long id) {
        heroRepository.deleteById(id);
    }
}