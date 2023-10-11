# Tutorial APAP

## Authors

* **Rama Tridigdaya** - *2106638532* - *C*
---
## Tutorial 4
### What I have learned today
1. Apa itu Postman? Apa kegunaannya?
   Postman merupakan platform pengembangan API yang dapat digunakan untuk mengembangkan, menguji, menggunakan, dan mendokumentasikan API. 
2. Apa yang terjadi ketika kita tidak menggunakan @JsonIgnoreProperties dan @JsonProperty pada model Buku dan Penulis? apabila terjadi error, mengapa hal tersebut dapat terjadi?
   Jika tidak menggunakan @JsonIgnoreProperties maka kemungkinan data dari model akan ditampilkan secara lengkap sedangkan @JsonProperty jika tidak digunakan maka bisa saja terjadi error karena ketika kita melakukan serialization/deserialization bisa saja atribut pada object tersebut tidak sama dengan field name yang ada pada JSON
3. Pada tutorial ini, kita mencoba untuk memanggil data dengan menggunakan method GET. Namun, apakah kita dapat memanggil data dengan method lainya, seperti POST? Jelaskan pendapat kalian?
   Ya bisa saja untuk mengambil data baru atau yang baru perbaharui atau yang dihapus bisa dengan menggunakan method POST.
4. Selain method GET dan POST, sebutkan dan jelaskan secara singkat HTTP request methods lainnya yang dapat kita gunakan!
   1) PUT -> Buat memperbaharui data yang sudah ada (seluruh atributnya)
   2) PATCH -> Mirip kayak PUT memperbaharui data tapi cuma sebagian saja
   3) DELETE -> Digunakan untuk menghapus data dari server
5. Apa kegunaan atribut WebClient?
   Atribut WebClient digunakan untuk membuat instance dari kelas WebClient. Kelas WebClient digunakan untuk mengirim permintaan HTTP ke server. Setelah instance dari kelas WebClient dibuat, kita dapat menggunakannya untuk mengirim permintaan HTTP.
---
## Tutorial 4
### What I have learned today
1. Apa itu xmlns? Jawab dengan singkat dan padat.
   xmlns adalah atribut yang digunakan untuk mendefinisikan namespace dari elemen HTML. Namespace digunakan untuk membedakan elemen HTML dari elemen HTML yang berasal dari namespace lain.
2. Jelaskan perbedaan th:include dan th:replace!
   1) th:include akan memasukkan konten dari fragment ke dalam tag host, sedangkan
   2) th:replace akan mengganti tag host dengan konten dari fragment. 
3. Kapan sebaiknya kita menggunakan static files dibandingkan dengan file eksternal menggunakan link?
   Ketika kita menggunakan file tersebut secara berulang-ulang di banyak halaman dan tidak berubah-ubah isinya (secara dinamis)
4. Jelaskan caramu menyelesaikan latihan no 2
   Kurang lebih saya copy kode yang similar yang ada pada bagian Panduan. Perbedaannya mungkin di controller sekarang pake updateBukuRequestDTO. Selain itu untuk memastikan bahwa isi listPenulis pada Buku terlihat kita iterasi list pada select.
   ```
   <select th:field="*{listPenulis[__${iterationStatus.index}__].idPenulis}" class="form-control">
   ```
   Dan masukkan value-nya pada option
   ```
   <option th:value="${penulis.idPenulis}" th:text="${penulis.namaPenulis}"></option>
   ```
   Selain dari itu, kodenya kurang lebih sama.
5. Jelaskan apa itu pagination!
   Pagination adalah fitur yang digunakan untuk membagi data dalam jumlah besar menjadi beberapa halaman untuk memudahkan pengguna dalam membaca data.
6. Sebutkan salah satu skenario yang mengharuskan adanya perbedaan dev dan prod dan jelaskan alasannya!
   Misalkan kita ingin membangun fitur baru yang belum dites atau menambahkan object baru, untuk menghindari adanya perubahan tidak sengaja yang menyebabkan aplikasi tersebut error ketika digunakan oleh user, maka akan lebih baik untuk menggunakan environment dev. Baru setelah kita yakin bahwa fitur tersebut sudah stable/sudah bisa digunakan dengan baik baru kita deploy ke prod env.
7. Lampirkan screenshot kalau kamu sudah berhasil membuat user untuk environment production serta bukti bahwa kamu sudah berhasil mengakses database production dengan user tersebut!
   ![Buat User Prod](https://i.imgur.com/wXdd59Z.png)
   ![Buka DB Prod](https://i.imgur.com/I7wrCed.png)
---
## Tutorial 3
### What I have learned today
1. Jelaskan apa itu ORM pada spring serta apa fungsi dan kegunaanya? 
   Object Relation Mapping (ORM) adalah sebuah framework yang dapat menjembatani perbedaan antara pemrograman berorientasi objek dengan yang berbasis data relasional. ORM berfungsi untuk memudahkan pengaksesan data dari basis data menggunakan objek Java. Bisa juga untuk mengotomatiskan proses mapping.
2. Jelaskan secara singkat apa itu dan kegunaan dari tag-tag dibawah ini. (@Entity, @Table, @Column)
   1) Tag @Entity digunakan untuk mendeklarasikan sebuah kelas Java sebagai entitas database dan jadi representasi tabel
   2) Tag @Table digunakan untuk mendeklarasikan nama tabel database yang akan digunakan untuk menyimpan data entitas
   3) Tag @Column digunakan untuk mendeklarasikan kolom database yang akan digunakan untuk menyimpan data atribut entitas
3. Pada relasi buku ke penulis, terdapat tag 
```
@JoinTable(name = "penulis_buku", joinColumns = @JoinColumn(name = "id"),
       inverseJoinColumns = @JoinColumn(name = "id_penulis"))
```
Jelaskan maksud dari tag @JoinTable tersebut beserta parameternya (name, joinColumns, inverseJoinColumns) dan implementasinya pada database. 
   Tag @JoinTable digunakan untuk mendeklarasikan tabel yang akan digunakan untuk menyimpan relasi antara dua entitas. 
   1) name -> name digunakan untuk mendeklarasikan nama tabel 
   2) joinColumns -> joinColumns digunakan untuk mendeklarasikan kolom di tabel baru yang akan digunakan untuk menunjuk ke entitas pertama. Dalam kasus ini, kolom id di tabel ini akan digunakan untuk menunjuk ke entitas Buku
   3) inverseJoinColumns -> inverseJoinColumns digunakan untuk mendeklarasikan kolom-kolom di tabel pivot yang akan digunakan untuk menunjuk ke entitas kedua
   ```
   CREATE TABLE penulis_buku (
      id UUID NOT NULL,
      id_penulis BIGINT NOT NULL,
      PRIMARY KEY (id, id_penulis),
      FOREIGN KEY (id) REFERENCES buku (id),
      FOREIGN KEY (id_penulis) REFERENCES penulis (id_penulis)
   );
   ```
4. Bagaimana cara kerja dari dependensi java mapper, yaitu mapstruct?
   MapStruct dapat digunakan untuk memetakan objek secara otomatis antara dua kelas/objek Java. Kita menandai interface pemetaan dengan anotasi @Mapper, lalu MapStruct menghasilkan kode pemetaan otomatis saat kompilasi, lalu akan dilakukan pemetaan objek secara otomatis dengan menggunakan metode instansi yang menggunakan interface yang punya anotasi tersebut
5. Apa keuntungan dari implementasi soft delete?
   Bisa simpan riwayat data, dipulihin, mencegah kesalahan ketika data direferensi, dan lainnya
---
## Tutorial 2
### What I have learned today
1. Apa itu DTO? Jelaskan kegunaannya pada proyek ini?
   DTO (Data Transfer Object) adalah objek yang digunakan biasanya untuk melakukan transfer data di antara layer yang ada pada aplikasi. DTO digunakan untuk pemisahan data antara layer sehingga data yang sebenarnya dapat lebih terproteksi security-nya.
2. Apa itu UUID? Mengapa UUID digunakan? 
   UUID (Universally Unique Identifier) merupakan kombinasi unik angka dan huruf yang terdiri dari 128-bit digunakan sebagai "identifier" atau kalau dalam matkul basis data "key atribut" yang digunakan untuk membedakan satu objek dengan lainnya
3. Pada service, mengapa perlu ada pemisahan antara interface dan implementasinya?
   Karena adanya Dependency Injection (DI) pada Spring Boot untuk mengelola dependensi antara komponen yang ada pada aplikasi. Dengan pemisahan interface dan implementasi, kita bisa bisa menyatakan dependensi pada interface sehingga bisa leluasa ketika mengganti implementasi tanpa mengganggu implementasi komponen lainnya yang ada pada aplikasi.
4. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat.
   Anotasi @Autowired ini merupakan implementasi dari konsep Dependency Injection. Pada package Service kita mempunyai implementasi dari
   BukuService yang bernama BukuServiceImpl, kita menganotasikan BukuServiceImpl dengan @Service untuk menandainya sebagai komponen dari Spring tersebut. Nah, di controller bagian BukuController, kita menginginkan untuk mengakses data yang ada menggunakan fungsi yang ada pada BukuService oleh karena itu kita menganotasikannya dengan @Autowired sehingga secara otomatis Spring Boot akan mendeteksi pengimplementasian BukuService yang sesuai dalam hal BukuServiceImpl dan sekarang kita bisa mengakses fungsi yang dipunya oleh BukuService tanpa harus membuat objek atau juga pengelolaan dependensi secara manual.
5. Apa perbedaan @GetMapping dan @PostMapping?
   Simpelnya @GetMapping digunakan ketika kita menggunakan metode HTTP GET untuk membaca data sedangkan @PostMapping digunakan ketika kita menggunakan metode HTTP POST untuk mengirim (membuat/mengubah) data.
6. Jelaskan proses yang terjadi di controller, model, dan service pada proses create buku, mulai dari fungsi formAddBuku hingga pengguna menerima halaman success-create-buku.
   1) Controller -> Mendapatkan bahwa laman berada pada buku/create dan akan menjalankan fungsi formAddBuku membuat objek BukuDTO dan mengembalikan halaman form-create-buku
   2) Controller -> Setelah user menekan tombol submit maka kita akan menerima data yang kini tersimpan di BukuDTO, fungsi addBuku akan berjalan. pertama cek dulu apakah Buku sudah ada (dari latihan), jika sudah maka buku tidak akan di-create, jika tidak ada maka buku akan di-create
   3) Model -> Selain dari fungsi getter-nya, model tentunya digunakan untuk membuat objek Buku
   4) Service -> Setelah objek Buku terbuat maka Controller akan memanggil fungsi yang ada pada Service dalam hal ini adalah fungsi createBuku. Setelah berhasil, maka dia akan me-load halaman form-create-buku
7. Jelaskan mengenai th:object!
   th:object ini pada dasarnya digunakan untuk menghubungkan objek yang ada pada Spring dengan HTML-nya. Jadi yang ada pada th:object itu maka dia akan diakses atribut-atributnya. Perubahan pada objek dalam HTML juga akan memengaruhi objeknya yang ada di Spring (menggunakan th:field)
8. Jelaskan mengenai th:field!
   th:field fungsinya seperti di atas berkaitan dengan th:object digunakan pada form untuk merubah nilai atribut yang ada pada objek. Pada source code, th:field secara otomatis akan berubah jadi id dan name yang akan = nama atributnya untuk POST.

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
