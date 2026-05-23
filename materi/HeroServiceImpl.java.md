# HeroServiceImpl.java

## 1. Analisis 4 Pilar OOP (PBO)

Kode pada kelas ini menerapkan **3 dari 4 pilar utama OOP**:

* **Abstraksi (Abstraction) & Polimorfisme (Polymorphism):**
  Terjadi melalui baris `implements HeroService` dan penggunaan anotasi `@Override` pada setiap method. Kelas ini wajib mengimplementasikan seluruh kontrak abstrak dari interface `HeroService`. Polimorfisme juga terlihat pada method `saveTierList(TierList tierList)`, di mana argumen yang diterima menggunakan tipe parent class (`TierList`), tetapi sistem bisa mengenali tipenya secara dinamis melalui operator `instanceof`.
* **Enkapsulasi (Encapsulation):**
  Terlihat pada penyembunyian detail logika bisnis dari luar kelas. Selain itu, modifikasi atau pembacaan data objek model tetap memanfaatkan method enkapsulasi seperti `hero.getNamaHero()`, `tierList.getIdTier()`, dan `tierList.getNamaTierList()`.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Pusat Logika Bisnis:** Kelas ini mengatur jalannya alur data sebelum disimpan atau setelah diambil dari database.
2. **Kondisional Simpan (Polimorfik):** Pada method `saveTierList`, sistem memeriksa apakah objek kiriman merupakan instansiasi dari `CompetitiveTierList`. Jika benar, tipe data string penanda diset sebagai `"COMPETITIVE"`, jika salah diset sebagai `"CASUAL"`. Logika dilanjutkan untuk menentukan apakah data akan di-*insert* baru atau di-*update* berdasarkan ada atau tidaknya `idTier`.
3. **Mekanisme Smart Sync API:** Pada method `syncFromApi()`, sistem mengecek isi database terlebih dahulu via `heroRepo.count()`. Jika database sudah berisi data, proses dihentikan (*early return*). Jika kosong, Spring `RestTemplate` akan menembak API eksternal OpenMLBB, melakukan parsing data format JSON/Map berlapis secara berurutan, memvalidasi duplikasi nama, lalu menyimpannya ke database.

---

## 3. Layer Arsitektur

Kode ini berada di **Service Implementation Layer (Business Logic Layer)**.

* **Menuju ke mana?** Layer ini menerima instruksi dari **Controller Layer (Presentation)**, memproses aturan bisnis, lalu meneruskan perintah operasi data menuju **Repository Layer (Data Access)** via objek `heroRepo` dan `tierRepo`.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Primitive/Wrapper:** `long` (untuk menghitung jumlah baris DB), `Long` (untuk ID objek).
  * **Object/Class:** `String`, `Hero`, `TierList`, `RestTemplate`, `HttpHeaders`, `HttpEntity`, `ResponseEntity`, `Exception`.
  * **Collection:** `List<Hero>`, `List<TierList>`, `Map<String, Object>`, `List<Map<String, Object>>`.
* **Tech Stack / Teknologi:**
  * **Language:** Java SE.
  * **Dependency Injection Container:** Spring Core IoC (`@Service`, `@Autowired`).
  * **HTTP Client:** Spring Web RestTemplate (`org.springframework.web.client.RestTemplate`) untuk konsumsi REST API.

---

## 5. Penjelasan Per Baris Code

* `@Service` -> Anotasi untuk menandai kelas ini sebagai komponen Logika Bisnis di wadah Spring IoC Container.
* `public class HeroServiceImpl implements HeroService` -> Deklarasi kelas konkret yang mengimplementasikan (realisasi kontrak) interface `HeroService`.
* `@Autowired private HeroRepository heroRepo;` -> Menyuntikkan dependensi objek akses database hero secara otomatis.
* `@Override` -> Menandakan bahwa method ini merupakan implementasi ulang dari method milik interface induk.
* `heroRepo.findAllManual();` -> Memanggil fungsi repositori untuk mengambil list seluruh data hero.
* `tierList instanceof CompetitiveTierList` -> Operator evaluasi untuk mengecek apakah objek `tierList` dibuat menggunakan blueprint kelas `CompetitiveTierList`.
* `heroRepo.insertHeroManual(...)` -> Mengeksekusi query insert dengan mengirimkan 5 parameter data sesuai spesifikasi tabel baru.
* `heroRepo.count();` -> Fungsi bawaan JPA untuk menghitung total baris data yang ada pada tabel hero.
* `new RestTemplate();` -> Instansiasi objek HTTP Client Spring untuk melakukan request ke luar sistem.
* `headers.set("User-Agent", "...");` -> Memanipulasi header HTTP Request agar diizinkan dan tidak diblokir oleh server API OpenMLBB.
* `rest.exchange(...)` -> Mengeksekusi request HTTP GET ke URL API eksternal secara sinkronus.
* `(Map<String, Object>) rootResponse.get("data")` -> Proses *Type Casting* (konversi tipe data paksa) dari objek umum menjadi struktur data Map bawaan Java.
* `for (Map<String, Object> record : records) { ... }` -> Melakukan perulangan (*looping*) untuk membedah satu per satu data hero hasil respon API.
* `heroRepo.findByNamaHeroManual(nama) == null` -> Validasi lokal untuk memastikan nama hero dari API belum pernah terdaftar di database agar tidak terjadi eror duplikasi data (*redundancy check*).
