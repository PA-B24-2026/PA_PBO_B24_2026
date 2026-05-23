# CompetitiveTierList.java

## 1. Analisis 4 Pilar OOP (PBO)

Kode pada kelas ini menerapkan **3 dari 4 pilar utama OOP**:

* **Inheritansi (Inheritance):**
  Terlihat pada baris `public class CompetitiveTierList extends TierList`. Keyword `extends` menandakan bahwa `CompetitiveTierList` adalah kelas anak (*subclass*) yang mewarisi seluruh properti dan perilaku dari kelas induk `TierList` (*superclass*).
* **Polimorfisme (Polymorphism):**
  Terjadi secara *Method Overriding* yang ditandai dengan annotation `@Override`. Kelas anak ini memodifikasi isi atau perilaku dari method `getTemaWarnaKanvas()`, `getJenisKey()`, dan `getJenisName()` agar menghasilkan nilai spesifik yang berbeda dari kelas induk atau kelas saudara kembarnya (`CasualTierList`).
* **Abstraksi (Abstraction):**
  Tercermin dari pemenuhan kontrak kelas induk. `CompetitiveTierList` wajib mengimplementasikan method-method abstrak yang dideklarasikan di kelas induknya (`TierList`) agar objek ini bisa diinstansiasi secara utuh dan nyata.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Instansiasi Objek:** Ketika aplikasi menerima data bertipe "serius/competitive", sistem akan membuat instance baru di memori (`new CompetitiveTierList()`).
2. **OR-Mapping (JPA):** Saat objek disimpan ke database, Hibernate akan membaca `@DiscriminatorValue("COMPETITIVE")` dan mengisi kolom tipe data pembeda (*discriminator column*) dengan teks `"COMPETITIVE"`.
3. **Dynamic Binding:** Saat Controller atau Service memanggil `.getTemaWarnaKanvas()` pada objek induk `TierList` yang berisi instance ini, Java runtime secara dinamis mengeksekusi kode di kelas ini dan mengembalikan string warna merah turnamen (`bg-red-950...`).

---

## 3. Layer Arsitektur

Kode ini berada di **Model / Entity Layer (Data Layer)**.

* **Menuju ke mana?** Layer ini merupakan struktur cetakan data dasar (domain object). Objek dari layer ini dialirkan menuju **Repository Layer** (untuk disimpan/diambil dari DB) dan dibaca oleh **Service Layer** untuk diproses logikanya sebelum dilempar ke **Controller (Presentation Layer)**.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Object/Class:** `String` (sebagai tipe data nilai kembalian/*return value* dari seluruh method di dalam kelas).
* **Tech Stack / Teknologi:**
  * **Language:** Java SE.
  * **Framework ORM:** Jakarta Persistence API (JPA) / Hibernate untuk memetakan objek Java ke database relasional.
  * **Front-end Styling:** CSS Framework Tailwind (terlihat dari nilai string kelas utility seperti `bg-red-950 border-red-500 text-red-200`).

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem.model;` -> Menandakan lokasi file kelas ini berada dalam paket/folder model data aplikasi.
* `import jakarta.persistence...;` -> Menarik pustaka standard Jakarta EE untuk mengelola pemetaan objek Java ke dalam tabel database (JPA).
* `@Entity` -> Menandai kelas ini sebagai entitas database yang datanya akan disimpan dan dikelola oleh framework Hibernate.
* `@DiscriminatorValue("COMPETITIVE")` -> Menentukan string penanda `"COMPETITIVE"` pada kolom khusus di tabel database untuk mengidentifikasi bahwa baris data tersebut adalah milik kelas anak ini (Strategi *Single Table Inheritance*).
* `public class CompetitiveTierList extends TierList` -> Deklarasi kelas anak bernama `CompetitiveTierList` yang mewarisi (inheritance) seluruh struktur dari kelas induk `TierList`.
* `@Override` -> Penanda validasi untuk compiler bahwa method di bawahnya ditulis ulang untuk menggantikan fungsi bawaan milik parent class (Polimorfisme).
* `public String getTemaWarnaKanvas()` -> Method untuk mengambil tema warna kanvas dengan tipe kembalian data berupa teks (`String`).
* `return "bg-red-950 border-red-500 text-red-200";` -> Mengembalikan nilai string berisi deretan *utility classes* CSS Tailwind bertema merah gelap (ala turnamen) untuk merender tampilan UI.
* `public String getJenisKey()` -> Method untuk mengambil kunci identifikasi jenis tier list dalam format string.
* `return "serius";` -> Mengembalikan nilai string `"serius"`.
* `public String getJenisName()` -> Method untuk mengambil nama tampilan jenis tier list yang ramah dibaca user pada web interface.
* `return "Competitive";` -> Mengembalikan nilai string teks `"Competitive"`.
