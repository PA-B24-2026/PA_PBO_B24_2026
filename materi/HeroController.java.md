# HeroController.java

## 1. Analisis 4 Pilar OOP (PBO)

Kode ini menerapkan **3 dari 4 pilar utama OOP**:

* **Abstraksi (Abstraction):** 
  Terjadi pada penggunaan `private HeroService heroService;`. Di sini controller hanya memanggil method seperti `heroService.getAllHeroes()` tanpa perlu tahu bagaimana logika internal atau query database-nya bekerja. Interface bertindak sebagai kontrak.
* **Polimorfisme (Polymorphism):** 
  Terjadi secara *Runtime Polymorphism* pada method `saveTierList`. Objek `kustomUser` dideklarasikan dengan tipe data parent (`TierList`), namun instansiasi objeknya dinamis mengikuti input user: bisa menjadi `CompetitiveTierList` atau `CasualTierList`.
* **Inheritansi (Inheritance):** 
  Hubungan pewarisan tersirat dari poin polimorfisme di atas. `CompetitiveTierList` dan `CasualTierList` merupakan kelas anak (*subclass*) yang mewarisi sifat/method dari kelas induk `TierList` (*superclass*).
* **Enkapsulasi (Encapsulation):**
  Terlihat pada penggunaan keyword `private` pada atribut `heroService` dan pemanggilan method setter/getter seperti `hero.setRole()`, `kustomUser.setIdTier()`, dan `hero.getRole()`. Data sensitif di dalam objek disembunyikan dan hanya bisa diakses via method.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **HTTP Request:** User melakukan aksi di browser (akses halaman, submit form, atau klik hapus). Request ditangkap oleh Spring Boot Routing (`@GetMapping` atau `@PostMapping`).
2. **Dependency Injection:** Controller otomatis menggunakan instance dari Service layer yang sudah disuntikkan via `@Autowired`.
3. **Business Logic & Interaksi Data:** Controller memanggil method yang ada di `heroService` untuk mengambil, menyimpan, menyinkronkan, atau menghapus data ke database.
4. **Data Binding:** Data yang dibawa dari UI dipetakan otomatis ke objek (`@ModelAttribute Hero hero`) atau parameter terpisah (`@RequestParam`).
5. **Response Delivery:** Controller mengirimkan data kembali ke UI via objek `Model`, lalu mengarahkan user ke halaman HTML (`return "index"`) atau melakukan *redirect* (`return "redirect:/"`).

---

## 3. Layer Arsitektur

Kode ini berada di **Presentation Layer (UI/Controller Layer)**.

* **Menuju ke mana?** Layer ini mengarah langsung ke **Service Layer (Business Logic)** melalui interface `HeroService`. Layer ini tidak boleh berkomunikasi langsung dengan database (Repository/DAO layer).

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Primitive/Wrapper:** `Long` (untuk ID), `boolean` (tersirat pada pengecekan *empty*).
  * **Object/Class:** `String`, `Hero`, `TierList`, `CompetitiveTierList`, `CasualTierList`, `Model`, `RedirectAttributes`, `Exception`.
  * **Collection:** `List<String>` (untuk menampung banyak data *role* sekaligus).
* **Tech Stack / Teknologi:**
  * **Language:** Java SE.
  * **Framework:** Spring Boot (Spring Web / Spring MVC).
  * **Depedency Injection Container:** Spring Core Core IoC (`@Autowired`).
  * **UI Integration:** SweetAlert2 (lewat Flash Attributes) dan Thymeleaf (tersirat dari *return view* "index").

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem.controller;` -> Menentukan lokasi folder/paket dari file controller ini.
* `import ...;` -> Menarik library bawaan Java, file model kita, serta komponen Spring Framework agar bisa digunakan di kelas ini.
* `@Controller` -> Annotation Spring untuk menandai kelas ini sebagai pengendali request web (HTTP).
* `public class HeroController { ... }` -> Deklarasi kelas utama controller.
* `@Autowired private HeroService heroService;` -> Menyuntikkan (DI) implementasi dari `HeroService` secara otomatis tanpa ketik `new`.
* `@GetMapping("/")` -> Menangani request HTTP GET ketika user mengakses alamat utama IP/localhost.
* `model.addAttribute(...)` -> Menitipkan data list hero dan tier ke objek model agar bisa dirender dan dibaca di file HTML.
* `return "index";` -> Membuka dan menampilkan file cetakan `index.html`.
* `@GetMapping("/sync")` -> Menangani request sinkronisasi data dari API eksternal OpenMLBB.
* `heroService.syncFromApi();` -> Memerintahkan layer service untuk mengeksekusi penarikan data API.
* `return "redirect:/";` -> Mengarahkan ulang (refresh) browser kembali ke halaman utama.
* `@PostMapping("/hero/save")` -> Menangani request HTTP POST saat user mengirim data dari form input hero.
* `@ModelAttribute Hero hero` -> Otomatis membaca seluruh input form dan membungkusnya menjadi objek `Hero`.
* `@RequestParam(...) List<String> roleList` -> Mengambil data spesifik dari checkbox role di UI ke dalam struktur data List.
* `String.join(", ", roleList);` -> Menggabungkan isi array list menjadi satu baris teks String dipisah tanda koma.
* `heroService.saveHero(hero);` -> Mengirim objek hero yang sudah rapi ke service untuk disimpan ke database.
* `ra.addFlashAttribute(...)` -> Mengirim pesan notifikasi sementara yang akan dibaca oleh library SweetAlert2 di halaman web.
* `@PostMapping("/tierlist/save")` -> Menangani request HTTP POST untuk menyimpan konfigurasi sandbox Tier list kustom user.
* `TierList kustomUser;` -> Deklarasi variabel bertipe parent class untuk wadah polimorfisme objek.
* `if (jenis.equalsIgnoreCase("serius")) { ... } else { ... }` -> Logika runtime untuk menentukan apakah objek diinstansiasi sebagai `CompetitiveTierList` atau `CasualTierList` (Polimorfisme).
* `@GetMapping("/hero/delete/{id}")` -> Menangani request HTTP GET untuk menghapus data master hero berdasarkan path ID yang dinamis.
* `@PathVariable Long id` -> Menangkap nilai ID yang ada di dalam URL bar browser.
* `try { ... } catch (Exception e) { ... }` -> Mekanisme *Error Handling* untuk menangkap kegagalan sistem saat proses hapus data tier list agar aplikasi tidak langsung *crash*.
