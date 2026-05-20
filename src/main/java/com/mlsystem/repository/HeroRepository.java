package com.mlsystem.repository;

import com.mlsystem.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    // 1. SELECT ALL HERO MANUAL
    @Query(value = "SELECT * FROM tb_hero", nativeQuery = true)
    List<Hero> findAllManual();

    // 2. FIND BY NAME MANUAL (Dipakai saat Sync API agar tidak duplikat)
    @Query(value = "SELECT * FROM tb_hero WHERE nama_hero = :nama LIMIT 1", nativeQuery = true)
    Hero findByNamaHeroManual(@Param("nama") String nama);

    // 3. SELECT HERO BY ID MANUAL
    @Query(value = "SELECT * FROM tb_hero WHERE id_hero = :id LIMIT 1", nativeQuery = true)
    Hero findHeroByIdManual(@Param("id") Long id);

    // 4. INSERT MANUAL HERO + STATUS ENUM DB (Urutan Kolom Sesuai Letak di Model: Status dulu baru Gambar)
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tb_hero (nama_hero, role, status_hero, gambar) VALUES (:nama, :role, :status, :gambar)", nativeQuery = true)
    void insertHeroManual(@Param("nama") String nama,
                          @Param("role") String role,
                          @Param("status") String status,
                          @Param("gambar") String gambar);

    // 5. UNTUK FITUR ALTERNATIF GANTI GAMBAR: Otomatis set status jadi MANUAL di tingkat DB
    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_hero SET gambar = :gambar, status_hero = 'MANUAL' WHERE id_hero = :id", nativeQuery = true)
    void updateGambarHeroManual(@Param("id") Long id, @Param("gambar") String gambar);

    // 6. DELETE MASTER HERO MANUAL BY ID
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_hero WHERE id_hero = :id", nativeQuery = true)
    void deleteHeroManualById(@Param("id") Long id);
}