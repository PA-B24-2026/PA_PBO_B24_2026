# HeroRepository.java



## 1. Analisis 4 Pilar OOP (PBO)

Kode berupa berkas Interface ini menerapkan **2 dari 4 pilar utama OOP**:

* **Abstraksi (Abstraction):**
  Terlihat sangat dominan karena berkas ini berbentuk sebuah **Interface** (`public interface HeroRepository`), bukan kelas murni. Berkas ini hanya berisi deklarasi nama method, parameter, dan anotasi query tanpa memiliki blok tubuh kode (*body block* `{}`). Implementasi nyata dari method-method ini diabstraksikan dan akan dibuat secara otomatis oleh framework Spring Data JPA di latar belakang.
* **Inheritansi (Inheritance):**
  Terjadi pada baris `extends JpaRepository<Hero, Long>`. Keyword `extends` pada interface menandakan bahwa `HeroRepository` mewarisi ratusan fungsi CRUD bawaan (seperti `save()`, `findAll()`, `deleteById()`) milik interface induk `JpaRepository`.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Pola Kontrak Kontrol:** Interface ini bekerja sebagai jembatan penghubung utama antara aplikasi Java dengan database mesin SQL.
2. **Native SQL Query Execution:** Ketika method di dalam berkas ini dipanggil oleh layer bisnis (Service), framework Spring Boot akan membaca anotasi `@Query(..., nativeQuery = true)` dan langsung menembakkan perintah SQL mentah tersebut ke database.
3. **Mekanisme Data Binding (`@Param`):** Nilai variabel yang dikirim dari kode Java diikat secara aman ke dalam parameter SQL menggunakan simbol titik dua (contoh: `:id`) untuk mencegah celah keamanan *SQL Injection*.
4. **Data Modifying & Transaksi:** Untuk operasi yang mengubah isi tabel (Insert, Update, Delete), anotasi `@Modifying` dan `@Transactional` bekerja sama untuk memastikan bahwa perubahan data diproses secara aman dalam satu kesatuan transaksi database. Jika di tengah jalan terjadi eror, data otomatis dibatalkan (*rollback*).

---

## 3. Layer Arsitektur

Kode ini berada di **Repository / Data Access Object (DAO) Layer**.

* **Menuju ke mana?** Layer ini bertindak sebagai gerbang keluar terakhir dari aplikasi menuju **Database/Basis Data**. Layer ini hanya boleh dipanggil dan digunakan oleh **Service Layer (Business Logic)**.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Wrapper/Object:** `Long` (untuk mapping Primary Key), `String` (parameter query SQL).
  * **Collection:** `List<Hero>` (struktur data dinamis untuk menampung banyak objek Hero sekaligus).
  * **Void:** `void` (tipe kembalian yang menandakan method insert/update/delete tidak mengembalikan data apa pun).
* **Tech Stack / Teknologi:**
  * **Language:** Java SE.
  * **Framework Data:** Spring Data JPA (menyediakan fitur `JpaRepository`, `@Query`, `@Modifying`).
  * **Transaction Management:** Spring Framework Transaction (`@Transactional`).

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem.repository;` -> Menandakan lokasi folder paket repository data access.
* `import ...;` -> Menarik pustaka Spring Data JPA dan model entitas `Hero`.
* `@Repository` -> Stereotype anotasi Spring untuk menandai berkas ini sebagai komponen operasi data access.
* `public interface HeroRepository extends JpaRepository<Hero, Long>` -> Deklarasi interface repository untuk entitas `Hero` dengan tipe data Primary Key berupa `Long`.
* `@Query(value = "...", nativeQuery = true)` -> Anotasi untuk menulis perintah query database menggunakan sintaks SQL mentah sesuai dialek database yang dipakai (bukan JPQL).
* `List<Hero> findAllManual();` -> Method untuk mengambil seluruh data baris dari tabel `tb_hero` dan membungkusnya ke dalam struktur data `List`.
* `@Param("nama")` -> Mengikat variabel argumen Java agar bisa dibaca di dalam query SQL sebagai `:nama`.
* `Hero findByNamaHeroManual(...)` -> Mencari data satu hero spesifik berdasarkan string namanya untuk kebutuhan validasi sinkronisasi API.
* `@Transactional` -> Memastikan operasi manipulasi data berjalan dalam koridor transaksi database yang aman (ACID compliance).
* `@Modifying` -> Memberitahu Spring Data JPA bahwa query ini bukan operasi pembacaan (SELECT), melainkan operasi pengubahan data (DML).
* `void insertHeroManual(...)` -> Method insert data master hero baru secara manual langsung ke kolom tabel database.
* `void updateGambarHeroManual(...)` -> Memproses penggantian gambar kustom tanpa merusak atau menimpa string tautan URL gambar asli bawaan API eksternal.
* `void deleteHeroManualById(...)` -> Menghapus baris data master hero dari database menggunakan parameter ID.
* `void clearGambarKustomManual(...)` -> Mengeset kembali nilai kolom `gambar_custom` menjadi `NULL` agar visual tampilan hero kembali merujuk pada aset gambar standar API.
