# Tutorial APAP

## Authors

* **Rama Tridigdaya** - *2106638532* - *C*

---
## Tutorial 1
### What I have learned today
### GitLab
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
   Issue Tracker pada GitLab adalah alat/tempat yang dapat digunakan oleh orang untuk berkolaborasi dalam suatu proyek. Di Issue 
   Tracker, suatu tim dapat berkolaborasi untuk merencanakan proyek, berdiskusi tentang proyek, menyelesaikan masalah-masalah
   yang ada pada proyek, melacak progress proyek, dan lain sebagainya.  
2. Apa perbedaan dari git merge dan git merge --squash?
   Kalau 'git merge', branch tujuan merge (main) akan menyimpan seluruh history commit dari branch asal  
   sedangkan pada 'git merge --squash' branch tujuan (main) tidak akan menyimpan seluruh history commit dari branch asal melainkan
   akan di-"squash" menjadi 1 commit saja.
3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi?
   Dengan menggunakan Version Control System seperti Git, kita dapat dengan mudah melacak progress proyek yang ktia miliki
   karena Git telah mencatat setiap perubahan yang kita lakukan pada proyek tersebut sehingga jika terdapat suatu problem
   maka dapat dilacak bagian mana yang terdapat masalah. Banyak keunggulan lain seperti kolaborasi, revert, branching, backup,
   dan lain sebagainya.
### Spring
4. Apa itu library & dependency?
   Library adalah kumpulan kode yang sudah ditulis sebelumnya, biasanya berisi fungsi atau rutinitas yang dapat digunakan
   oleh para pengembang untuk menyelesaikan masalah tertentu (biasanya umum) tanpa harus buat kode itu dari awal lagi.
   Dependency pada dasarnya adalah library yang penting/integral yang digunakan pada suatu aplikasi/proyek yang jika 
   tidak ada maka program tersebut tidak dapat berjalan 
5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
   Maven adalah alat manajemen proyek yang dapat digunakan untuk membantu pengembang membangun, mem-publish, dan men-deploy 
   aplikasi mereka. Maven juga membantu pengembang mengatur, mengatur, dan membangun lifecycle proyek mereka. Ya, seperti Gradle.  
6. Jelaskan bagaimana alur ketika kita menjalankan http://localhost:8080/celsius-converter/90 sampai dengan muncul keluarannya di browser!
   Masuk website > Web akan dimapping menggunakan @PathVariable berdasarkan value celsius yang sudah terdapat pada URL >
   Menjalankan fungsi getCelsiusConverterPage dan karena value celsius tidak null maka dia akan membuat Object CelsiusConverter
   > Return template html yang telah dibuat > Oleh karena object CelsiusConverter telah dibuat maka akan menampilkan
   nilai suhu yang sudah di-convert dengan fungsi yang ada pada Object CelsiusConverter tersebut > Website akan ditampilkan
   dengan menggunakan data yang telah ada
7. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?
   Banyak sekali tipe aplikasi/proyek lain yang dapat dikembangkan dengan menggunakan Spring framework seperti aplikasi
   desktop, aplikasi command-line, web services/APIs, dan lain sebagainya.
8. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable?
   @RequestParam mengambil data yang dikirim sebagai paramaeter dalam query dari URL web tersebut yang dimulai dari tanda 
   tanya '?' dan berisi parameter serta value dari parameter tersebut. Sedangkan pada @PathVariable data yang diambil merupakan
   bagian dari path URL contoh pada aplikasi tutor 1 'celsius-converter/{celsius}' dimana data yang akan diambil adalah
   'celsius' ini. @RequestParam lebih disarankan jika parameter itu dinamis atau sering berubah sedangkan @PathVariable akan
   lebih baik jika value yang diambil statis

### What I did not understand
- [ ] Masih banyak yang belum ngerti :D
