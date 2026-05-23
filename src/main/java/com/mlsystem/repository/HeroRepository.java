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

    // select semua hero
    @Query(value = "SELECT * FROM tb_hero", nativeQuery = true)
    List<Hero> findAllManual();

    //cari nama hero
    @Query(value = "SELECT * FROM tb_hero WHERE nama_hero = :nama LIMIT 1", nativeQuery = true)
    Hero findByNamaHeroManual(@Param("nama") String nama);

    //select hero by id
    @Query(value = "SELECT * FROM tb_hero WHERE id_hero = :id LIMIT 1", nativeQuery = true)
    Hero findHeroByIdManual(@Param("id") Long id);

    //insert hero ke tb
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tb_hero (nama_hero, role, status, gambar, gambar_custom) VALUES (:nama, :role, :status, :gambar, :gambarKustom)", nativeQuery = true)
    void insertHeroManual(@Param("nama") String nama,
                          @Param("role") String role,
                          @Param("status") String status,
                          @Param("gambar") String gambar,
                          @Param("gambarKustom") String gambarKustom);

    //ganti gambar
    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_hero SET gambar_custom = :gambarKustom WHERE id_hero = :id", nativeQuery = true)
    void updateGambarHeroManual(@Param("id") Long id, @Param("gambarKustom") String gambarKustom);

    //hapus hero
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_hero WHERE id_hero = :id", nativeQuery = true)
    void deleteHeroManualById(@Param("id") Long id);

    //reset foto kembali sesuai API
    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_hero SET gambar_custom = NULL WHERE id_hero = :id", nativeQuery = true)
    void clearGambarKustomManual(@Param("id") Long id);
}