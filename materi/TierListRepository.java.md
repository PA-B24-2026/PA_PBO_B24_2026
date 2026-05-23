# TierListRepository.java

## 1. Analisis 4 Pilar OOP (PBO)

Kode berupa berkas Interface ini menerapkan **2 dari 4 pilar utama OOP**:

* **Abstraksi (Abstraction):**
  Sama seperti repositori sebelumnya, berkas ini berbentuk sebuah **Interface** (`public interface TierListRepository`). Berkas ini tidak memiliki blok tubuh kode (*body block*) melainkan hanya memuat tanda tangan deklarasi method, anotasi query, dan tipe parameter. Implementasi konkret bagaimana query SQL tersebut dijalankan sepenuhnya diserahkan secara abstrak kepada framework Spring Data JPA.
* **Inheritansi (Inheritance):**
  Terlihat pada baris `extends JpaRepository<TierList, Long>`. Interface `TierListRepository` mewarisi seluruh kemampuan manipulasi data bawaan dari interface induk generic `JpaRepository` dengan target entitas `TierList` yang bertipe Primary Key `Long`.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Penyedia Kontrak CRUD:** Interface ini bertindak sebagai perantara aman antara logika bisnis aplikasi dengan tabel database `tb_tierlist`.
2. **Eksekusi SQL Native Dinamis:** Saat Service layer memanggil fungsi seperti `findAllByOrderByIdTierDesc()`, Spring Data JPA akan langsung menerjemahkan anotasi `@Query(..., nativeQuery = true)` menjadi perintah query SQL mentah dan menembakkannya ke mesin basis data.
3. **Manajemen Transaksi Modifikasi:** Untuk operasi manipulasi data (Insert, Update, Delete), anotasi `@Modifying` dan `@Transactional` bekerja bersama memastikan instruksi SQL dieksekusi dalam satu siklus transaksi yang utuh. Jika proses update atau insert gagal di tengah jalan, database akan otomatis melakukan *rollback* demi menjaga konsistensi data.

---

## 3. Layer Arsitektur

Kode ini berada di **Repository / Data Access Object (DAO) Layer**.

* **Menuju ke mana?** Layer ini merupakan pintu keluar terakhir untuk berkomunikasi langsung dengan **Database/Basis Data**. Komponen ini bertindak sebagai pelayan data yang dipanggil eksklusif oleh **Service Layer**.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Wrapper Class & Object:** `Long` (untuk mapping parameter ID), `String` (untuk parameter teks nama, susunan, dan jenis tier).
  * **Collection:** `List<TierList>` (struktur wadah dinamis untuk menampung barisan objek dari model abstrak `TierList`).
  * **Void:** `void` (penanda tipe data kembalian kosong untuk method manipulasi DML).
* **Tech Stack / Teknologi:**
  * **Language:** Java SE.
  * **Framework Data:** Spring Data JPA (menyediakan anotasi `@Query`, `@Modifying`, dan interface `JpaRepository`).
  * **Transaction Handler:** Spring Transaction Framework (`@Transactional`).

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem.repository;` -> Menandakan lokasi berkas interface ini di dalam struktur folder repository paket aplikasi.
* `import ...;` -> Menarik dependensi framework Spring Data, penanganan transaksi, serta model data induk `TierList`.
* `@Repository` -> Anotasi penanda komponen Spring IoC bahwa interface ini memegang tanggung jawab atas akses manipulasi data storage.
* `public interface TierListRepository extends JpaRepository<TierList, Long>` -> Deklarasi interface repository untuk mengelola siklus data entitas `TierList` dengan tipe kunci utama `Long`.
* `@Query(value = "...", nativeQuery = true)` -> Perintah untuk menjalankan klausa instruksi SQL mentah bawaan mesin database target, bukan query objek (JPQL).
* `List<TierList> findAllByOrderByIdTierDesc();` -> Method untuk menarik seluruh data dari tabel `tb_tierlist` dengan urutan terbalik dari ID terbesar (data terbaru) ke dalam bentuk `List`.
* `@Transactional` -> Membungkus method manipulasi di bawahnya agar berjalan aman di dalam blok transaksi terisolasi database.
* `@Modifying` -> Menegaskan kepada Spring Data bahwa query yang dieksekusi akan mengubah status atau isi baris data tabel (bukan query pembacaan SELECT).
* `@Param("...")` -> Mengikat argumen variabel dari fungsi Java agar nilainya disuntikkan secara aman ke parameter SQL bertanda titik dua (seperti `:nama`, `:susunan`, `:jenis`).
* `void insertManual(...)` -> Method untuk memasukkan data kreasi cetakan tier list baru milik user langsung lewat instruksi INSERT SQL.
* `void updateManual(...)` -> Method untuk memperbarui teks nama, muatan struktur teks susunan hero, serta jenis penggolongan tier list berdasarkan filter parameter ID.
* `void deleteManualById(...)` -> Method untuk menghapus baris data rekam kustom tier list secara permanen berdasarkan acuan ID tertentu.
