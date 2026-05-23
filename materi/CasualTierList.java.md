# CasualTierList.java

## 1. Analisis 4 Pilar OOP (PBO)

Kode pada kelas ini menerapkan **3 dari 4 pilar utama OOP**:

* **Inheritansi (Inheritance):**
  Terlihat sangat jelas pada baris `public class CasualTierList extends TierList`. Keyword `extends` menandakan bahwa kelas `CasualTierList` merupakan kelas anak (*subclass*) yang mewarisi seluruh properti dan perilaku dari kelas induk `TierList` (*superclass*).
* **Polimorfisme (Polymorphism):**
  Terjadi secara *Method Overriding* (Polimorfisme dinamis) yang ditandai dengan annotation `@Override`. Kelas anak mendefinisikan ulang isi/perilaku dari method `getTemaWarnaKanvas()`, `getJenisKey()`, dan `getJenisName()` secara spesifik yang berbeda dari implementasi bawaan induknya atau kelas saudara lainnya (seperti `CompetitiveTierList`).
* **Abstraksi (Abstraction):**
  Tercermin dari proses implementasi method abstrak. Kelas `CasualTierList` dipaksa oleh kontrak kelas induknya (`TierList` yang kemungkinan besar adalah sebuah kelas abstrak) untuk memberikan detail implementasi nyata bagaimana nilai-nilai warna dan jenis teks itu dihasilkan.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Instansiasi Objek:** Saat aplikasi mendeteksi tipe tier list adalah "casual", objek ini dibuat di memori (`new CasualTierList()`).
2. **OR-Mapping (JPA):** Saat objek ini disimpan atau dibaca dari database, framework akan otomatis mengisi kolom *discriminator* dengan nilai `"CASUAL"` berkat annotation `@DiscriminatorValue`.
3. **Dynamic Binding:** Saat UI atau Service layer memanggil method `.getTemaWarnaKanvas()` pada objek `TierList` yang ternyata berisi instance `CasualTierList`, Java runtime akan mengeksekusi method di dalam kelas ini dan mengembalikan string kelas warna CSS Tailwind (`bg-blue-950...`).

---

## 3. Layer Arsitektur

Kode ini berada di **Model / Entity Layer (Data Layer)**.

* **Menuju ke mana?** Layer ini merupakan representasi data mentah dan struktur tabel database. Objek dari layer ini akan dibawa menembus **Repository Layer**, diolah di **Service Layer**, hingga akhirnya ditampilkan ke user oleh **Presentation Layer (Controller)**.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Object/Class:** `String` (digunakan sebagai tipe data kembalian/*return value* dari ketiga method di dalam kelas).
* **Tech Stack / Teknologi:**
  * **Language:** Java SE.
  * **Framework ORM:** Jakarta Persistence API (JPA) / Hibernate (ditandai dengan package `jakarta.persistence`).
  * **Front-end Styling:** CSS Framework Tailwind (terlihat dari format string utility class seperti `bg-blue-950 border-blue-500`).

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem.model;` -> Menandakan bahwa file kelas ini berada dalam paket/folder struktur model data aplikasi.
* `import jakarta.persistence...;` -> Menarik pustaka standard Jakarta EE untuk mengelola pemetaan objek Java ke dalam tabel database relasional (JPA).
* `@Entity` -> Penanda bagi framework Hibernate bahwa kelas Java ini adalah sebuah entitas yang strukturnya akan dicerminkan menjadi tabel di database.
* `@DiscriminatorValue("CASUAL")` -> Menentukan nilai penanda khusus ("CASUAL") pada kolom tabel database untuk membedakan kelas anak ini dengan kelas anak turunan `TierList` lainnya (strategi *Single Table Inheritance*).
* `public class CasualTierList extends TierList` -> Deklarasi kelas anak bernama `CasualTierList` yang secara resmi mewarisi (inheritance) sifat-sifat dari kelas induk `TierList`.
* `@Override` -> Petunjuk bagi compiler Java bahwa method di bawahnya sengaja ditulis ulang untuk menimpa/menggantikan method milik parent class (Polimorfisme).
* `public String getTemaWarnaKanvas()` -> Method untuk mengambil data tema warna visual kanvas dengan tipe kembalian data berupa teks (`String`).
* `return "bg-blue-950 border-blue-500 text-blue-200";` -> Mengembalikan nilai string berupa daftar *utility classes* CSS Tailwind bertema biru tua santai untuk dirender di halaman web.
* `public String getJenisKey()` -> Method untuk mengambil kunci identifikasi jenis tier list dalam format string.
* `return "serius";` -> *Catatan/Review Kode:* Mengembalikan string `"serius"`. (Tip: Jika kelas ini untuk casual/seru-seruan, pastikan nilai kembalian ini tidak tertukar dengan kelas `CompetitiveTierList`).
* `public String getJenisName()` -> Method untuk mengambil nama tampilan jenis tier list yang ramah dibaca pengguna.
* `return "Competitive";` -> Mengembalikan string teks `"Competitive"`.
