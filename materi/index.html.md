# index.html

## 1. Analisis 4 Pilar OOP (PBO)

Meskipun berkas ini adalah file HTML/View, di dalamnya terdapat interaksi dengan objek Java serta penerapan konsep PBO pada sisi client script:

* **Abstraksi (Abstraction) via Template Engine:**
  Penggunaan sintaks Thymeleaf seperti `th:each="h : ${heroes}"` mewakili konsep abstraksi. Di sisi View, kita tidak perlu tahu struktur internal koneksi database atau query SQL yang mengumpulkan objek `Hero`. View hanya menerima kontrak sekumpulan objek data matang untuk langsung dirender ke komponen visual.
* **Polimorfisme (Polymorphism) Tampilan Runtime:**
  Terjadi pada bagian pemisahan lencana (*badge*) tipe tier list (`tier.class.simpleName == 'CompetitiveTierList'`). View secara dinamis mengubah warna tema kelas CSS (Merah untuk *Competitive* dan Biru untuk *Casual*) berdasarkan tipe objek riil yang dikirimkan oleh server saat halaman dirender.
* **Enkapsulasi (Encapsulation) Objek Javascript:**
  Fungsi seperti `prosesSimpanTierList()` menerapkan enkapsulasi logika manipulasi DOM. Seluruh data dari baris-baris HTML dikemas secara terisolasi ke dalam satu objek JSON terenkapsulasi (`hasilSusunan`), lalu dimasukkan ke dalam elemen input tersembunyi (`#susunanHeroInput`) sebelum dikirim secara aman ke server.

---

## 2. Cara Kerja Kode (Alur Eksekusi)

1. **Server-Side Rendering (Thymeleaf):** Saat server Spring Boot memanggil halaman "index", Thymeleaf menyisir tag `th:` untuk melakukan perulangan daftar hero dan daftar riwayat tier list ke dalam komponen card dan modal HTML.
2. **Client Side Drag and Drop:** Event API HTML5 (`ondragstart`, `ondragover`, `ondrop`) mendeteksi pergerakan mouse user ketika menyeret gambar avatar hero. Saat dilepas di zona `.tier-row`, element HTML hero dipindahkan secara fisik sebagai anak (*child element*) dari baris tier tersebut.
3. **Serialisasi JSON & Submit:** Saat tombol simpan ditekan, fungsi JavaScript mengumpulkan semua data nama hero dari tiap baris tier, mengubahnya menjadi teks string JSON lewat `JSON.stringify()`, memasukkannya ke input form, lalu menembakkan form POST menuju URL `/tierlist/save`.
4. **Deserialisasi Data (Load):** Saat tombol "Muat" di riwayat ditekan, JavaScript membaca atribut teks JSON, membedahnya kembali menjadi objek lewat `JSON.parse()`, lalu menyebarkan kembali kartu-kartu hero dari pool utama ke baris visual tier S sampai F secara otomatis.

---

## 3. Layer Arsitektur

Kode ini berada di **Presentation Layer / View Layer (Front-End UI)**.

* **Menuju ke mana?** Layer ini merupakan ujung tombak interaksi user. Data aksi form atau klik tombol navigasi dari layer ini diarahkan langsung menuju **Presentation Layer Server (HeroController)** melalui pemetaan URL HTTP (seperti `/hero/save`, `/sync`, atau `/tierlist/delete/{id}`).

---

## 4. Tipe Data & Tech Stack

* **Tipe Data yang Digunakan (Java/Thymeleaf Context):**
  * `List<Hero>` dan `List<TierList>` sebagai koleksi penampung data utama dari server.
  * `String` (untuk konversi ekspresi nama, gambar, teks pesan notifikasi).
* **Tipe Data yang Digunakan (JavaScript Context):**
  * `Object / JSON` (`hasilSusunan` untuk memetakan nama tier ke daftar array hero).
  * `Array` (`listNamaHero` penampung barisan teks nama hero).
  * `Boolean` (`isKustom` untuk status penanda kondisi visual gambar kustom).
* **Tech Stack / Teknologi UI:**
  * **Thymeleaf:** Template engine untuk menjembatani object Java masuk ke struktur HTML.
  * **Tailwind CSS & DaisyUI:** Framework desain komponen responsif bertema gelap (*night theme*).
  * **SweetAlert2:** Library JavaScript untuk memicu kotak dialog alert konfirmasi hapus dan notifikasi interaktif.
  * **FontAwesome:** Paket pustaka penyedia ikon visual navigasi sistem.

---

## 5. Penjelasan Per Baris Code Utama

* `<html xmlns:th="http://www.thymeleaf.org" data-theme="night">` -> Inisialisasi dokumen HTML dengan integrasi pustaka Thymeleaf serta set tema DaisyUI mode malam.
* `<div th:each="h : ${heroes}" ... draggable="true">` -> Perulangan Thymeleaf untuk menciptakan elemen kartu pembungkus objek hero secara dinamas yang diizinkan untuk diseret (*draggable*).
* `img th:src="${h.gambarKustom != null ? h.gambarKustom : h.gambar}"` -> Operator ternary Thymeleaf untuk memilih visual gambar kustom user. Jika kosong, gunakan URL gambar bawaan API asli.
* `<dialog id="modal_load" class="modal ...">` -> Komponen dialog modal DaisyUI untuk menampilkan riwayat daftar cetakan tier list dari database.
* `th:text="${tier.class.simpleName == 'CompetitiveTierList' ? ...}"` -> Kode refleksi Java di level UI untuk mendeteksi nama kelas objek anak guna menentukan teks penanda jenis.
* `function allowDrop(ev) { ev.preventDefault(); }` -> Mematikan fungsi bawaan browser agar suatu area HTML valid menjadi area drop objek eksternal.
* `ev.dataTransfer.setData("text", ev.target.id);` -> Menyimpan identitas ID kartu hero yang sedang diseret ke dalam memori clipboard drag-and-drop.
* `targetZone.appendChild(draggedElement);` -> Memindahkan elemen fisik HTML kartu hero ke dalam kontainer baris tier target secara dinamis.
* `const jsonString = JSON.stringify(hasilSusunan);` -> Mengonversi representasi struktur objek susunan map JavaScript menjadi baris data teks string JSON agar bisa dikirim via form HTTP.
* `const susunan = JSON.parse(susunanStr);` -> Membedah string JSON mentah dari database kembali menjadi struktur objek Map data koordinat letak hero.
* `const pesan = [[${pesan}]];` -> Sintaks ekspresi inlining Thymeleaf untuk menyuntikkan teks variabel notifikasi kilat (*flash attribute*) dari Controller Java langsung ke variabel JavaScript.
* `window.location.href = '/hero/clear-gambar/' + idHeroAktifMedsos;` -> Perintah pengalihan URL browser (HTTP GET) untuk menembak endpoint pembersihan data gambar kustom di server Spring Boot.
* `if (namaHero.includes(kataKunci)) { kartu.style.display = ""; }` -> Logika filter pencarian teks real-time untuk memunculkan kembali kartu hero jika namanya mengandung huruf inputan user.
