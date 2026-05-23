# Hero.java

## 1. Analisis 4 Pilar OOP (PBO)

Kode pada kelas ini menerapkan **1 pilar utama OOP** secara dominan:

* **Enkapsulasi (Encapsulation):**
  Terlihat sangat jelas pada pembatasan akses variabel menggunakan keyword `private` (seperti `private Long idHero;`, `private String namaHero;`, dsb). Data di dalam objek disembunyikan agar tidak bisa diubah langsung dari luar kelas. Akses pembacaan dan modifikasi data dipusatkan melalui method publik bertipe **Getter dan Setter** (`getIdHero()`, `setIdHero()`, dsb).

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Definisi Blueprint / POJO:** Kelas ini bekerja sebagai cetakan (*Plain Old Java Object*) yang merepresentasikan satu entitas data Hero.
2. **Object-Relational Mapping (ORM):** Saat aplikasi berjalan, framework Hibernate akan membaca annotation `@Entity` dan `@Table` untuk memetakan kelas ini menjadi sebuah tabel database relasional bernama `tb_hero`.
3. **Mekanisme JPA:** Ketika Controller atau Service memanggil objek ini (misalnya saat proses `save` atau `getAllHeroes`), JPA menggunakan *Constructor Kosong* `public Hero() {}` untuk menginstansiasi objek secara internal, lalu mengisi datanya menggunakan method setter sebelum dialirkan ke layer lain.

---

## 3. Layer Arsitektur

Kode ini berada di **Model / Entity Layer (Data Layer)**.

* **Menuju ke mana?** Kelas ini bertindak sebagai objek data inti (*Domain Object*). Kelas ini akan digunakan oleh **Repository Layer (JPA/CRUD)** sebagai parameter tipe data, diproses oleh **Service Layer**, dan dibungkus oleh **Controller Layer** untuk dikirimkan datanya menuju UI tampilan user.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Wrapper Class / Object:** 
    * `Long`: Digunakan pada `idHero` untuk menampung data angka besar (Primary Key) yang mendukung nilai `null` sebelum disimpan ke database.
    * `String`: Digunakan pada `namaHero`, `role`, dan `gambar` untuk menyimpan kumpulan karakter teks bebas.
* **Tech Stack / Teknologi:**
  * **Language:** Java SE.
  * **Specification:** Jakarta Persistence API (JPA) via package `jakarta.persistence.*`.
  * **ORM Provider:** Hibernate (terintegrasi bawaan dari dependensi Spring Data JPA).

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem.model;` -> Menandakan file kelas ini disimpan dalam paket/folder model ekosistem sistem.
* `import jakarta.persistence.*;` -> Menarik seluruh library standard spesifikasi JPA untuk manajemen persistensi data ke database.
* `@Entity` -> Menandai kelas Java ini sebagai sebuah entitas tabel database yang sah.
* `@Table(name = "tb_hero")` -> Menginstruksikan framework untuk membuat atau mencocokkan entitas ini dengan tabel fisik database bernama `tb_hero`.
* `public class Hero { ... }` -> Deklarasi kelas publik `Hero`.
* `@Id` -> Menandai variabel di bawahnya (`idHero`) sebagai Primary Key (Kunci Utama) unik untuk tabel tersebut.
* `@GeneratedValue(strategy = GenerationType.IDENTITY)` -> Mengonfigurasi pembuatan ID secara otomatis (Auto Increment) yang diserahkan fiturnya kepada database.
* `@Column(name = "id_hero")` -> Menyelaraskan properti variabel `idHero` di Java dengan nama kolom fisik `id_hero` di tabel database.
* `private Long idHero;` -> Atribut privat untuk menyimpan ID Hero.
* `private String namaHero;` -> Atribut privat untuk menyimpan nama dari karakter hero.
* `private String role;` -> Atribut privat untuk menyimpan teks kumpulan role hero (misal: "Tank, Support").
* `private String gambar;` -> Atribut privat untuk menyimpan referensi string gambar atau nama file aset gambar hero.
* `public Hero() {}` -> Constructor default tanpa parameter, wajib ada agar framework JPA/Hibernate bisa melakukan mapping data secara reflektif.
* `public Long getIdHero() { return idHero; }` -> Method Getter untuk mengambil atau membaca nilai ID Hero dari luar kelas.
* `public void setIdHero(Long idHero) { this.idHero = idHero; }` -> Method Setter untuk mengubah atau mengeset nilai ID Hero dari luar kelas dengan keyword `this` merujuk ke atribut kelas itu sendiri.
* *(Baris baris Getter/Setter namaHero & role)* -> Berfungsi sama, memediasi akses variabel privat secara aman (Enkapsulasi).
* `public String getGambar() { return gambar; }` -> Method Getter untuk membaca data string gambar.
* `public String setGambar() { return gambar; }` -> *Catatan/Review Kode:* Method ini mengalami *overloading* yang kurang tepat karena mengembalikan nilai string tetapi menggunakan nama 'setGambar'. Ini bisa dihapus atau diabaikan jika tidak dipakai.
* `public void setGambar(String gambar) { this.gambar = gambar; }` -> Method Setter yang benar untuk memperbarui nilai string gambar dari luar objek.
