# Project Final Praktikum - Sistem Tier List Hero Meta Mobile Legend

Selamat datang di repositori kelompok kami! Proyek ini dikembangkan untuk memenuhi tugas akhir mata kuliah **Pemrograman Berbasis Objek (PBO)**.

## 👥 Anggota Kelompok
| Nama | NIM |
| :---: | :---: |
| Ananda Daffa Harahap | 2409106050 |
| Ridwan Nur Rahman | 2409106064 |
| Aulia Nur Rachman | 2409106069 |
| Razib Ramadhan | 2409106076 |
---

## Struktur Folder
```bash
src/
├── main/
│   ├── java/
│   │   └── com/mlsystem/                 <-- Root Package (Brand Proyek)
│   │       ├── TierListApplication.java  <-- Main Class (Kunci Kontak/Running)
│   │       ├── controller/               <-- LOGIKA NAVIGASI (Backend)
│   │       │   └── HeroController.java   <-- Pengatur alur data ke browser
│   │       ├── model/                    <-- STRUKTUR DATA (PBO - Encapsulation)
│   │       │   └── Hero.java             <-- Definisi atribut Hero (ID, Nama, Tier, dll)
│   │       ├── repository/               <-- AKSES DATABASE (PBO - Abstraction)
│   │       │   └── HeroRepository.java   <-- Interface untuk query otomatis ke MySQL
│   │       └── service/                  <-- (Opsional) LOGIKA BISNIS
│   │           └── HeroService.java      <-- Logika hitung Meta/Tier List
│   └── resources/
│       ├── static/                       <-- ASET STATIS (Langsung dibaca Browser)
│       │   ├── css/
│       │   │   └── style.css             <-- Styling UI Modern
│       │   ├── js/
│       │   │   └── main.js               <-- Script interaktif (jika ada)
│       │   └── assets/
│       │       └── heroes/               <-- TEMPAT FOTO HERO (.png, .jpg)
│       ├── templates/                    <-- VIEW / FRONTEND (Thymeleaf)
│       │   ├── index.html                <-- Halaman Utama Tier List
│       │   ├── detail.html               <-- Halaman Detail Hero
│       │   └── form.html                 <-- Halaman Input/Edit Hero
│       └── application.properties        <-- KONFIGURASI DATABASE & SERVER
└── test/                                 <-- Unit Testing (Opsional)
```
### Briefing
1. TierListApplication.java: Ini adalah jantungnya. Saat kamu tekan tombol Run, file inilah yang menyalakan server Tomcat di latar belakang.
2. model/: Di sini pilar Encapsulation bekerja. Isinya adalah kelas Hero dengan variabel private dan getter/setter. 
3. repository/: Ini adalah pilar Abstraction. Kamu cukup membuat interface yang mewarisi JpaRepository. Kamu tidak perlu menulis SQL SELECT * FROM secara manual. 
4. controller/: Lapisan yang menghubungkan Java ke browser. Di sini kamu menentukan URL, misal /hero/jungle maka akan memanggil data apa. 
5. static/: Semua file yang tidak diolah oleh Java ditaruh di sini. Jika kamu panggil gambar di HTML, jalurnya langsung dimulai dari /assets/heroes/.... 
6. templates/: Tempat file HTML yang menggunakan Thymeleaf. Di sini kamu bisa melakukan looping data hero yang dikirim dari Backend. 
7. application.properties: Kamu cukup isi baris kode untuk koneksi ke MySQL di sini satu kali saja.
---

## Cara Menghubungkan Kode Lokal ke GitHub (Khusus Anggota)
Jika kamu adalah anggota tim yang memegang kode sumber pertama kali, ikuti langkah ini untuk menghubungkan folder lokal ke repositori ini tanpa menghapus file yang ada:

1.  **Buka Terminal** (atau Terminal di Visual Studio) di dalam folder proyek kamu.
2.  **Inisialisasi Git**:
    ```bash
    git init
    ```
3.  **Hubungkan ke Repositori ini**:
    ```bash
    git remote add origin https://github.com/PA-B24-2026/PA_PBO_B24_2026.git
    ```
4.  **Ambil README (Sangat Penting)**:
    ```bash
    git pull origin main --rebase
    ```
5.  **Push Kode Kamu**:
    ```bash
    git add .
    git commit -m "Initial commit: Menambahkan source code utama"
    git push origin main
    ```

---

## Prasyarat (Prerequisites)
Sebelum menjalankan aplikasi ini, pastikan kamu sudah menginstal:
*   **Java Development Kit (JDK)** versi 17 atau terbaru.
*   **Intellij Idea** atau **VS Code** dengan ekstensi yang sesuai.
*   **MySQL**.

---

## Cara Clone Aplikasi

### 1. Clone Repositori
Jika kamu baru ingin mengambil kode dari GitHub ke laptop baru:
```bash
git clone https://github.com/PA-B24-2026/PA_PBO_B24_2026.git
cd PA_PBO_B24_2026
```

### 2. Setelah clone, jalankan program seperti biasa menggunakan visual studio
1. Masuk ke aplikasi yang kamu gunakan.
2. Buka folder dimana repo _di-clone_.
3. Kemudian klik tombol run seperti biasanya.
4. Selamat! Programnya telah berhasil dijalankan

## Cara Menjalankan Aplikasi
1. Buka folder projek
2. Pilih file TierListApplication kemudian jalankan file ini
3. Buka pada browser anda kemudian ketik `localhost:8080`
4. Selamat! Anda sudah berhasil menjalankan projek ini!