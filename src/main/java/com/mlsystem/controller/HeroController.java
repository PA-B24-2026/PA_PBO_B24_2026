package com.mlsystem.controller;

// Ini import dari java
import java.util.List;

// Ini import dari file kita
import com.mlsystem.model.*;

// Import INTERFACE, bukan class murni
import com.mlsystem.service.HeroService;

// Ini import ORG
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Menandai kelas ini sebagai controller untuk handle request HTTP (Web)
@Controller
public class HeroController {

    //  Depedency Injection: Menyambungkan controller sama service hero secara otomatis
    @Autowired
    private HeroService heroService;

    //  Handle request pas pertama kali menuju localhost:8080
    @GetMapping("/")
    public String index(Model model) {
//      Ambil semua data hero dari db via service baru dimasukan ke variabel listHero
        model.addAttribute("heroes", heroService.getAllHeroes());
        // Mengambil data semua tier list yang tersimpan di database baru
        model.addAttribute("tiers", heroService.getAllTiers());
//      Membuka file index.html secara otomatis
        return "index";
    }

    // Tombol di UI untuk memicu sinkronisasi API eksternal (OpenMLBB)
    @GetMapping("/sync")
    public String sync() {
        // Memanggil fungsi sinkronisasi dari service implementation
        heroService.syncFromApi();
        return "redirect:/";
    }

    //  Handle request pas user tekan tombol simpan pada form (POST)
    @PostMapping("/hero/save")
    public String save(@ModelAttribute Hero hero,
                       @RequestParam(value = "roleList", required = false) List<String> roleList,
                       RedirectAttributes ra) {

//      Logika buat role karakter
        if (roleList != null && !roleList.isEmpty()) {
            // Menggabungkan List menjadi String: "Assassin, Marksman"
            String rolesCombined = String.join(", ", roleList);
            hero.setRole(rolesCombined);
        } else {
//          Kalau ga conteng apa-apa bakal no role
            hero.setRole("No Role");
        }

//      simpan hero ke db melalui service
        heroService.saveHero(hero);

//      notifikasi flash attribute yang dibaca oleh SweetAlert2
        ra.addFlashAttribute("pesan", "Hero berhasil disimpan dengan role: " + hero.getRole());
        ra.addFlashAttribute("tipe", "success");

//      Kalau sudah tersave langsung kembali ke halaman utama
        return "redirect:/";
    }

    // Memproses penyimpanan hasil kreasi susunan sandox TierMaker dari user
    @PostMapping("/tierlist/save")
    public String saveTierList(@RequestParam(value = "idTier", required = false) Long idTier,
                               @RequestParam("namaTierList") String nama,
                               @RequestParam("susunanHero") String susunan,
                               @RequestParam("jenisTier") String jenis,
                               RedirectAttributes ra) {

        // POLYMORFISME: Penentuan objek anak murni dilakukan saat aplikasi berjalan (Runtime)
        TierList kustomUser;
        if (jenis.equalsIgnoreCase("serius")) {
            kustomUser = new CompetitiveTierList();
        } else {
            kustomUser = new CasualTierList();
        }

        // Jika ada ID tierlist maka dia akan ambil lalu update
        if (idTier != null) {
            kustomUser.setIdTier(idTier);
        }

        kustomUser.setNamaTierList(nama);
        kustomUser.setSusunanHero(susunan);

        // Menembus layer kontrak service untuk menyimpan hasil kreasi referensi user
        heroService.saveTierList(kustomUser);

        ra.addFlashAttribute("pesan", "Referensi kustom TierList berhasil dibekukan ke database!");
        ra.addFlashAttribute("tipe", "success");
        return "redirect:/";
    }

    // Menghapus data master hero dari pool bahan sandbox
    @GetMapping("/hero/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
//      Hapus hero dari database melalui service
        heroService.deleteHeroById(id);
        ra.addFlashAttribute("pesan", "Data master hero dicabut dari sistem.");
        ra.addFlashAttribute("tipe", "info");
//      Kembali ke halaman utama setelah hapus
        return "redirect:/";
    }


}