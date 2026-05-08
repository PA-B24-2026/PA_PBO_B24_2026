package com.mlsystem.repository;

import com.mlsystem.model.TierList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierListRepository extends JpaRepository<TierList, Long> {

}