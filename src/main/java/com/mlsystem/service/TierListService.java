package com.mlsystem.service;

import com.mlsystem.model.TierList;
import com.mlsystem.repository.TierListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TierListService {

    @Autowired
    private TierListRepository tierListRepository;

    // 1. Mengambil semua data pemetaan tier list
    public List<TierList> getAllTierLists() {
        return tierListRepository.findAll();
    }

    // 2. Menyimpan data tier list baru (atau update)
    public void saveTierList(TierList tierList) {
        tierListRepository.save(tierList);
    }

    // 3. Menghapus tier list berdasarkan ID
    public void deleteTierListById(Long id) {
        tierListRepository.deleteById(id);
    }
}