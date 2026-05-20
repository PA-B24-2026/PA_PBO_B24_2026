package com.mlsystem.controller;

import java.util.List;
import com.mlsystem.model.*;
import com.mlsystem.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("heroes", heroService.getAllHeroes());
        model.addAttribute("tiers", heroService.getAllTiers());
        return "index";
    }

    @GetMapping("/sync")
    public String sync() {
        try {
            heroService.syncFromApi();
        } catch (Exception e) {
            System.err.println("Gagal sinkronisasi API: " + e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/hero/save")
    public String save(@ModelAttribute Hero hero,
                       @RequestParam(value = "roleList", required = false) List<String> roleList,
                       RedirectAttributes ra) {
        // TRY-CATCH SINKRON REVISI 1
        try {
            if (roleList != null && !roleList.isEmpty()) {
                hero.setRole(String.join(", ", roleList));
            } else {
                hero.setRole("No Role");
            }

            heroService.saveHero(hero);
            ra.addFlashAttribute("pesan", "Hero '" + hero.getNamaHero() + "' berhasil disimpan!");
            ra.addFlashAttribute("tipe", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("pesan", "Gagal menyimpan hero: " + e.getMessage());
            ra.addFlashAttribute("tipe", "error");
        }
        return "redirect:/";
    }

    @PostMapping("/tierlist/save")
    public String saveTierList(@RequestParam(value = "idTier", required = false) Long idTier,
                               @RequestParam("namaTierList") String nama,
                               @RequestParam("susunanHero") String susunan,
                               @RequestParam("jenisTier") String jenis,
                               RedirectAttributes ra) {
        // TRY-CATCH SINKRON REVISI 1
        try {
            TierList kustomUser = jenis.equalsIgnoreCase("serius") ? new CompetitiveTierList() : new CasualTierList();

            if (idTier != null) {
                kustomUser.setIdTier(idTier);
            }

            kustomUser.setNamaTierList(nama);
            kustomUser.setSusunanHero(susunan);

            heroService.saveTierList(kustomUser);

            String infoPesan = (idTier != null) ? "Perubahan Tier List berhasil disimpan!" : "Tier List berhasil dibekukan ke database!";
            ra.addFlashAttribute("pesan", infoPesan);
            ra.addFlashAttribute("tipe", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("pesan", "Gagal membekukan Tier List: " + e.getMessage());
            ra.addFlashAttribute("tipe", "error");
        }
        return "redirect:/";
    }

    @GetMapping("/tierlist/delete/{id}")
    public String deleteTierList(@PathVariable Long id, RedirectAttributes ra) {
        // TRY-CATCH SINKRON REVISI 1
        try {
            heroService.deleteTierListById(id);
            ra.addFlashAttribute("pesan", "Referensi Tier List berhasil dihapus permanen.");
            ra.addFlashAttribute("tipe", "info");
        } catch (Exception e) {
            ra.addFlashAttribute("pesan", "Gagal menghapus: " + e.getMessage());
            ra.addFlashAttribute("tipe", "error");
        }
        return "redirect:/";
    }

    // REVISI 2: ROUTE HAPUS HERO DI POOL SUDAH DIHAPUS TOTAL DARI SINI
}