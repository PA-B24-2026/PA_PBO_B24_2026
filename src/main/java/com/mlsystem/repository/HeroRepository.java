package com.mlsystem.repository;

import com.mlsystem.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Menandakan bahwa ini adalah komponen pengakses database
public interface HeroRepository extends JpaRepository<Hero, Long> {
//    Secara otomatis punya fungsi save(), findAll(), dll.

//    Ini custom field
    Hero findByNamaHero(String namaHero);
}