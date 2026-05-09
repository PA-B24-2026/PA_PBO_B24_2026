package com.mlsystem.controller;

import com.mlsystem.model.Hero;
import com.mlsystem.service.HeroService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HeroController {
    @Autowired
    private HeroService heroService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listHero", heroService.getAllHeroes());
        return "index";
    }

    @PostMapping("/hero/save")
    public String save(@ModelAttribute Hero hero, RedirectAttributes ra) {
        heroService.saveHero(hero);
        ra.addFlashAttribute("pesan", "Hero " + hero.getNamaHero() + " berhasil disimpan!");
        ra.addFlashAttribute("tipe", "success");
        return "redirect:/";
    }

    @GetMapping("/hero/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        heroService.deleteHeroById(id);
        ra.addFlashAttribute("pesan", "Data berhasil dihapus!");
        ra.addFlashAttribute("tipe", "info");
        return "redirect:/";
    }
}
