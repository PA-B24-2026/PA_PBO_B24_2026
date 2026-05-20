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

    // 3. INSERT MANUAL HERO
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tb_hero (nama_hero, gambar, role) VALUES (:nama, :gambar, :role)", nativeQuery = true)
    void insertHeroManual(@Param("nama") String nama, @Param("gambar") String gambar, @Param("role") String role);

    // 4. SELECT HERO BY ID MANUAL
    @Query(value = "SELECT * FROM tb_hero WHERE id_hero = :id LIMIT 1", nativeQuery = true)
    Hero findHeroByIdManual(@Param("id") Long id);
}