package com.mlsystem.controller;

import java.util.List;
import com.mlsystem.model.*;
import com.mlsystem.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    // FIX: ENDPOINT UNTUK PROSES GANTI GAMBAR ALTERNATIF (UPLOAD FILE LOKAL)
    @PostMapping("/hero/ganti-gambar")
    public String gantiGambar(@RequestParam("idHero") Long idHero,
                              @RequestParam("fileGambarAlternatif") MultipartFile fileGambar,
                              RedirectAttributes ra) {
        try {
            if (fileGambar != null && !fileGambar.isEmpty()) {
                String folderTujuan = new java.io.File("src/main/resources/static/uploads/").getAbsolutePath();
                String namaFileUnik = "alt_" + System.currentTimeMillis() + "_" + fileGambar.getOriginalFilename();
                java.io.File fileFisik = new java.io.File(folderTujuan + java.io.File.separator + namaFileUnik);

                if (!fileFisik.getParentFile().exists()) {
                    fileFisik.getParentFile().mkdirs();
                }
                fileGambar.transferTo(fileFisik);

                String pathLokalBaru = "/uploads/" + namaFileUnik;

                // Panggil service untuk eksekusi query native UPDATE ke database
                heroService.updateGambarHeroManual(idHero, pathLokalBaru);

                ra.addFlashAttribute("pesan", "Gambar alternatif berhasil dipasang!");
                ra.addFlashAttribute("tipe", "success");
            } else {
                ra.addFlashAttribute("pesan", "File gambar kosong atau tidak valid!");
                ra.addFlashAttribute("tipe", "error");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("pesan", "Gagal mengganti gambar: " + e.getMessage());
            ra.addFlashAttribute("tipe", "error");
        }
        return "redirect:/";
    }

    // FIX: HANYA ADA SATU ROUTING DELETE HERO DI SINI (ANTI BENTROK)
    @GetMapping("/hero/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            Hero hero = heroService.getHeroById(id);

            if (hero != null && "MANUAL".equalsIgnoreCase(hero.getStatusHero())) {

                // === LOGIKA PEMBERSIHAN FILE FISIK GAMBAR SAMPAH ===
                String pathGambarLama = hero.getGambar();
                if (pathGambarLama != null && pathGambarLama.startsWith("/uploads/")) {
                    // Cari alamat absolute folder static/uploads di storage lokal
                    String folderPath = new java.io.File("src/main/resources/static").getAbsolutePath();
                    java.io.File fileFisikGambar = new java.io.File(folderPath + pathGambarLama);

                    // Jika file fisik ada di komputer, eksekusi penghapusan secara instan
                    if (fileFisikGambar.exists()) {
                        boolean suksesHapusFile = fileFisikGambar.delete();
                        if (suksesHapusFile) {
                            System.out.println("[LOG] Berhasil menghapus file fisik sampah: " + fileFisikGambar.getName());
                        } else {
                            System.err.println("[LOG WARNING] File ditemukan tapi gagal dihapus dari OS.");
                        }
                    }
                }
                // ==================================================

                // Setelah berkas bersih, hapus baris record dari database MySQL
                heroService.deleteHeroById(id);
                ra.addFlashAttribute("pesan", "Data hero manual dan file gambar berhasil dihapus permanen!");
                ra.addFlashAttribute("tipe", "info");
            } else {
                ra.addFlashAttribute("pesan", "Aksi ditolak! Hero bawaan API tidak boleh dihapus.");
                ra.addFlashAttribute("tipe", "error");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("pesan", "Gagal menghapus hero: " + e.getMessage());
            ra.addFlashAttribute("tipe", "error");
        }
        return "redirect:/";
    }
}