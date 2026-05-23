package com.mlsystem.repository;

import com.mlsystem.model.TierList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface TierListRepository extends JpaRepository<TierList, Long> {

    //select tier list
    @Query(value = "SELECT * FROM tb_tierlist ORDER BY id_tier DESC", nativeQuery = true)
    List<TierList> findAllByOrderByIdTierDesc();

    //insert (untuk simpan data baru)
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tb_tierlist (nama_tierlist, susunan_hero, jenis_tierlist) VALUES (:nama, :susunan, :jenis)", nativeQuery = true)
    void insertManual(@Param("nama") String nama, @Param("susunan") String susunan, @Param("jenis") String jenis);

    // 3.update tier list (edit2)
    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_tierlist SET nama_tierlist = :nama, susunan_hero = :susunan, jenis_tierlist = :jenis WHERE id_tier = :id", nativeQuery = true)
    void updateManual(@Param("id") Long id, @Param("nama") String nama, @Param("susunan") String susunan, @Param("jenis") String jenis);

    //hapus tier list
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_tierlist WHERE id_tier = :id", nativeQuery = true)
    void deleteManualById(@Param("id") Long id);
}