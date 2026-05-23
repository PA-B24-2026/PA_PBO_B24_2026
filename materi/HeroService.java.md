# HeroService.java

## 1. Analisis 4 Pilar OOP (PBO)

Berkas ini berwujud **Interface murni**, yang menerapkan **1 pilar utama OOP** secara mutlak:

* **Abstraksi (Abstraction):**
  Menjadi representasi paling murni dari pilar abstraksi. Keyword `public interface HeroService` menegaskan bahwa berkas ini berfungsi sebagai **kontrak sistem** (blueprint/rancangan). Di dalam berkas ini, tidak ada satu pun method yang memiliki badan kode (*body block* `{}`). Interface ini hanya mendefinisikan *apa saja* (nama method, parameter, tipe data kembalian) yang harus bisa dilakukan oleh sistem, sedangkan *bagaimana cara melakukannya* disembunyikan total dan diserahkan kepada kelas implementasinya (`HeroServiceImpl`).

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Gerbang Kontrak Bisnis:** Berkas ini menetapkan daftar fungsi bisnis standar (CRUD hero, pengelolaan tier list, dan sinkronisasi API external) yang diizinkan untuk diakses oleh Presentation Layer.
2. **Mediasi Loose Coupling:** Controller tidak akan memanggil kelas implementasi konkret secara langsung, melainkan hanya memanggil interface `HeroService` ini. 
3. **Delegasi Runtime:** Ketika Controller mengeksekusi method seperti `heroService.syncFromApi()`, Java Virtual Machine (JVM) secara otomatis mengalihkan panggilan tersebut ke baris kode nyata yang ada di dalam kelas implementasi konkrit yang telah didaftarkan ke Spring Container.

---

## 3. Layer Arsitektur

Kode ini berada di **Service Interface Layer (Business Logic Abstraction)**.

* **Menuju ke mana?** Interface ini berada tepat di tengah arsitektur aplikasi. Ia menerima panggilan/permintaan dari **Controller Layer (Presentation)**, dan menyediakan struktur kontrak yang nantinya akan ditembus menuju **Service Implementation Layer** sebelum akhirnya mengakses **Repository Layer (Data)**.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Wrapper Class & Object:** `Long` (untuk pengiriman parameter ID unik), `String` (untuk nama file/tautan gambar).
  * **Model/Domain Object:** `Hero`, `TierList` (digunakan langsung sebagai parameter objek utuh dalam method `save`).
  * **Collection:** `List<Hero>`, `List<TierList>` (tipe kembalian dinamis untuk menampung banyak data objek sekaligus).
  * **Void:** `void` (tipe kembalian hampa untuk aksi manipulasi data yang tidak membalas dengan kembalian objek).
* **Tech Stack / Teknologi:**
  * **Language:** Java SE Standard.
  * **Core Concept:** Pure Java Interface & Polymorphism Architecture.

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem.service;` -> Menandakan letak berkas ini di dalam struktur folder paket service aplikasi.
* `import ...;` -> Menarik dependensi model data `Hero`, `TierList`, serta library `List` bawaan Java Utility.
* `public interface HeroService { ... }` -> Deklarasi interface publik bernama `HeroService`.
* `List<Hero> getAllHeroes();` -> Kontrak fungsi untuk mengambil seluruh kumpulan data objek hero.
* `void saveHero(Hero hero);` -> Kontrak fungsi untuk menyimpan data objek hero utuh ke dalam sistem.
* `Hero getHeroById(Long id);` -> Kontrak fungsi untuk mencari dan mengembalikan satu data objek hero spesifik berdasarkan acuan data ID.
* `void deleteHeroById(Long id);` -> Kontrak fungsi untuk memicu penghapusan data master hero menggunakan parameter ID.
* `void updateGambarHeroManual(Long id, String gambar);` -> Kontrak fungsi untuk memproses manipulasi tautan atau file gambar hero secara kustom lewat parameter ID dan String teks gambar.
* `void clearGambarKustom(Long id);` -> Kontrak fungsi hasil perbaikan (fix) untuk mengembalikan gambar hero bawaan dari API menggunakan satu parameter ID.
* `List<TierList> getAllTiers();` -> Kontrak fungsi untuk menarik seluruh rekam data kreasi tier list yang terdaftar.
* `void saveTierList(TierList tierList);` -> Kontrak fungsi serbaguna untuk menyimpan objek tier list (mendukung polimorfisme objek anak).
* `void deleteTierListById(Long id);` -> Kontrak fungsi untuk menghapus baris data rekam kustom tier list dari sistem via parameter ID.
* `void syncFromApi();` -> Kontrak fungsi khusus untuk memicu proses penarikan dan sinkronisasi data dari API OpenMLBB eksternal.
