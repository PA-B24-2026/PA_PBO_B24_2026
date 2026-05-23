# TierListApplication.java

## 1. Analisis 4 Pilar OOP (PBO)

Kode pada kelas utama ini menerapkan konsep dasar PBO berupa:

* **Enkapsulasi (Encapsulation) Tingkat Kelas:**
  Seluruh komponen aplikasi dibungkus di dalam struktur cetakan unit terkecil PBO, yaitu `public class TierListApplication`. Modifikasi perilaku aplikasi dari luar dikunci dan dikendalikan sepenuhnya melalui konfigurasi deklaratif berupa anotasi (`@SpringBootApplication`).
* **Static Method Behavior (Konsep Class vs Object):**
  Penggunaan keyword `static` pada method `main` menunjukkan prinsip PBO di mana method tersebut menempel langsung pada *Class* itu sendiri (bukan milik sebuah objek *instance*). Sehingga Java Virtual Machine (JVM) dapat mengeksekusi mesin utama program tanpa perlu membuat objek baru dengan kata kunci `new`.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Entry Point Utama:** Saat file JAR/WAR aplikasi dieksekusi, JVM akan mencari method `public static void main` sebagai titik awal absolut jalannya program.
2. **Bootstrapping Server:** Instruksi `SpringApplication.run` dijalankan untuk memicu proses inisialisasi framework Spring Boot.
3. **Component Scanning & Auto Configuration:** Melalui anotasi `@SpringBootApplication`, Spring Boot melakukan pemindaian otomatis (*component scanning*) ke seluruh folder paket (`com.mlsystem.*`) untuk mendaftarkan Controller, Service, dan Repository ke dalam memori IoC Container.
4. **Embedded Server Activation:** Mesin Tomcat bawaan (*embedded server*) dinyalakan, meluncurkan aplikasi web pada port default `8080` sehingga siap menerima request HTTP dari browser.

---

## 3. Layer Arsitektur

Kode ini berada di **Main Root Layer / Entry Point Application**.

* **Menuju ke mana?** Kelas ini bertindak sebagai otak konseptual dan pondasi paling luar yang menyelimuti seluruh sistem. Ia menyalakan dan menghidupkan seluruh layer di bawahnya, mulai dari **Presentation Layer (Controller)**, **Business Logic Layer (Service)**, hingga **Data Access Layer (Repository)**.

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan:**
  * **Array Object:** `String[] args` (struktur array objek teks untuk menampung parameter argumen opsional dari terminal/command prompt).
  * **Metaclass Object:** `TierListApplication.class` (representasi reflektif dari blueprint kelas itu sendiri sebagai argumen konfigurasi Spring).
* **Tech Stack / Teknologi:**
  * **Language:** Java SE Standard.
  * **Framework Core:** Spring Boot Framework (`org.springframework.boot`).
  * **Embedded Server:** Apache Tomcat (otomatis aktif di latar belakang).

---

## 5. Penjelasan Per Baris Code

* `package com.mlsystem;` -> Menandakan lokasi akar paket utama (*root package*) dari seluruh sistem aplikasi.
* `import org.springframework.boot...;` -> Menarik pustaka inti penanggung jawab eksekusi dan konfigurasi otomatisasi server Spring Boot.
* `@SpringBootApplication` -> Anotasi mahasakti yang menggabungkan tiga fungsi utama: `@Configuration` (konfigurasi berbasis Java), `@EnableAutoConfiguration` (otomatisasi setting database/web), dan `@ComponentScan` (pemindaian otomatis seluruh anotasi internal).
* `public class TierListApplication { ... }` -> Deklarasi nama kelas utama penggerak seluruh sistem aplikasi Tier List.
* `public static void main(String[] args)` -> Tanda tangan method utama wajib (Entry Point) yang dibaca pertama kali oleh mesin Java runtime untuk memulai aplikasi.
* `SpringApplication.run(TierListApplication.class, args);` -> Perintah mutlak untuk menghidupkan mesin framework Spring Boot, membaca konfigurasi kelas root, melemparkan parameter argumen, dan mengaktifkan server web lokal.
