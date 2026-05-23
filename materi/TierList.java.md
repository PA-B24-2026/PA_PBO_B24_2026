# TierList.java

## 1. Analisis 4 Pilar OOP (PBO)

Kelas ini merupakan pusat dari implementasi PBO di sistem kamu karena menerapkan **3 dari 4 pilar utama OOP** sekaligus:

* **Abstraksi (Abstraction):**
  Terlihat jelas pada deklarasi kelas `public abstract class TierList`. Kelas ini sengaja ditandai sebagai kelas abstrak (*abstract class*) karena konsep `TierList` bersifat umum/cetakan mentah, sehingga tidak boleh diinstansiasi langsung menggunakan kata kunci `new TierList()`.
* **Polimorfisme (Polymorphism):**
  Diterapkan melalui deklarasi tiga method abstrak: `getTemaWarnaKanvas()`, `getJenisKey()`, dan `getJenisName()`. Method ini tidak memiliki tubuh (*body* kode) karena perilakunya akan ditentukan secara dinamis (polimorfis) oleh kelas anak saat aplikasi berjalan.
* **Enkapsulasi (Encapsulation):**
  Terlihat pada penggunaan keyword `private` pada seluruh atribut properti data (`idTier`, `namaTierList`, `susunanHero`). Akses langsung dari luar kelas ditutup rapat dan wajib melewati gerbang method publik **Getter dan Setter**.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Pola Cetakan Induk:** Kelas ini menjadi blueprint utama yang menyatukan semua sifat umum dari segala jenis klasifikasi tier list.
2. **Single Table Inheritance (JPA):** Saat framework Hibernate membaca kelas ini, ia akan membuat satu tabel tunggal bernama `tb_tierlist` di database. Kolom khusus bernama `jenis_tierlist` otomatis dibuat sebagai pembeda (*discriminator*) untuk mencatat apakah suatu baris data bertipe "CASUAL" atau "COMPETITIVE".
3. **Data Mapping Besar (@Lob):** Properti `susunanHero` disiapkan khusus dengan anotasi `@Lob` agar database mampu menampung string panjang teks cetakan format JSON/teks kustom data susunan hero tanpa terkena batas limit karakter standar.

---

## 3. Layer Arsitektur

Kode ini berada di **Model / Entity Layer (Data Layer)** yang bertindak sebagai Parent Class Data.

* **Menuju ke mana?** Struktur cetakan ini mengarah dan dibaca langsung oleh komponen data access seperti **Repository Layer**, dipasangkan di dalam fungsi **Service Layer** sebagai tipe data umum kontrak bisnis, hingga mengalir ke **Controller Layer** untuk dipetakan secara dinamis.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Wrapper/Object:** 
    * `Long` (pada `idTier`): Tipe data angka besar objek untuk menampung ID primer.
    * `String` (pada `namaTierList` dan `susunanHero`): Objek teks penampung data String kustom.
* **Tech Stack / Teknologi:**
  * **Language:** Java SE.
  * **ORM Specification:** Jakarta Persistence API (`jakarta.persistence.*`) dengan strategi khusus pemetaan pewarisan database tabel tunggal (`InheritanceType.SINGLE_TABLE`).

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem.model;` -> Identifikasi lokasi paket folder model domain data aplikasi.
* `import jakarta.persistence.*;` -> Mengimpor fungsionalitas annotations pengelola persistensi basis data relasional.
* `@Entity` -> Menandai kelas abstrak ini agar ikut dipetakan ke dalam skema tabel database.
* `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` -> Strategi pemetaan JPA untuk menggabungkan data kelas induk dan seluruh kelas anak ke dalam satu tabel fisik yang sama.
* `@DiscriminatorColumn(...)` -> Mengonfigurasi pembuatan kolom penanda di tabel database dengan nama `jenis_tierlist` bertipe string untuk memisahkan data objek anak.
* `@Table(name = "tb_tierlist")` -> Menamai tabel fisik di database dengan nama `tb_tierlist`.
* `public abstract class TierList { ... }` -> Deklarasi cetakan kelas induk berbasis abstract.
* `@Id` -> Menandai variabel `idTier` sebagai penunjuk kunci utama (Primary Key) unik tabel.
* `@GeneratedValue(...)` -> Mengatur pembuatan nilai ID otomatis secara sekuensial (Auto Increment) bawaan database.
* `@Column(...)` -> Sinkronisasi pemetaan nama atribut kode Java dengan nama kolom fisik di database.
* `@Lob` -> Menandai variabel `susunanHero` sebagai *Large Object* (tipe data teks kapasitas besar di database).
* `columnDefinition = "TEXT"` -> Memaksa tipe kolom database bertipe data TEXT (bukan varchar biasa).
* `public TierList() {}` -> Constructor kosong default yang wajib disediakan demi kelancaran mapping objek oleh framework Hibernate.
* `public abstract String getTemaWarnaKanvas();` -> Deklarasi blueprint method abstract untuk warna UI wajib dipasang di kelas anak.
* `public abstract String getJenisKey();` -> Deklarasi blueprint method abstract untuk kunci klasifikasi jenis tier.
* `public abstract String getJenisName();` -> Deklarasi blueprint method abstract untuk nama teks visual jenis tier.
* *(Baris baris Getter & Setter manual di bawahnya)* -> Fungsi enkapsulasi standar untuk membaca dan memodifikasi nilai dari variabel privat kelas.
