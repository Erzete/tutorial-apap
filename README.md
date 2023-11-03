# Tutorial APAP

## Authors

* **Rama Tridigdaya** - *2106638532* - *C*
---
## Tutorial 6
### What I have learned today
1. Apa yang menjadi penyebab dari CONFLICT tersebut? 
   Yang menjadi penyebab dari conflict tersebut adalah adanya perubahan pada baris yang sama pada masing-masing branch yang ingin di-merge tersebut. Mereka sama-sama mengubah baris ke 5-7 pada file index.html sehingga terjadi conflict ketika di-merge.
2. Jelaskan perbedaan dari "rebase –continue", "rebase –skip", dan "rebase –abort"!
   "rebase --continue" digunakan untuk melanjutkan proses rebase sebelumnya yang belum berhasil/selesai "rebase --skip" digunakan untuk melewatkan/abaikan conflict/bagian yang bermasalah ketika melakukan proses rebase sedangkan "rebase --abort" digunakan untuk membatalkan rebase dan balik ke keadaan sebelum rebase
3. Apa perbedaan Git Merge dengan Git Rebase? Buatlah/carilah ilustrasi yang dapat menggambarkan perbedaanya!
   Kalau Merge dia preserve/melestarikan riwayat commit sedangkan kalau rebase dia akan mengubah riwayat commit. Log commit pada rebase akan linear dan ketika di-rebase maka riwayat tersebut akan diubah sehingga menjadi seperti itu.
   ![Git Merge vs Rebase](https://i.imgur.com/3QOfdze.png)
4. Mengapa hal pada langkah no 4 bisa terjadi? Mengapa git stash menjadi solusinya?
   Hal tersebut dapat terjadi karena pada branch feature-stash-1 terdapat perubahan yang belum kita commit sehingga kita tidak bisa melakukan switch branch. Git Stash menjadi solusi karena git stash dapat menyimpan sementara perubahan yang belum di-commit pada stash area untuk nantinya dapat kita pop/recover ketika kembali ke branch tersebut.
5. Sebutkan dan jelaskan tiga tipe dari Git Reset!
   1) "--soft" mengatur ulang HEAD ke commit tertentu tetapi perubahan yang telah dibuat tetap akan terlihat di working directory dan staging area walaupun tidak akan di-commit
   2) "--mixed" mengatur ulang HEAD ke commit tertentu dan akan mengembalikan perubahan yang dibuat di staging area ke keadaan sebelum commit tersebut. Akan tetapi, perubahan yang ada di working directory tetap ada
   3) "--hard" mengatur ulang HEAD ke commit tertentu dan akan mengembalikan semua perubahan yang telah dibuat di working directory & staging area
6. Apa itu git revert? Apa perbedaannya dengan git reset?
   Git revert akan membuat commit baru yang isinya adalah menghapus perubahan yang sudah ada dan mempertahankan commit yang di-revert sedangkan git reset tidak membuat commit baru melainkan langsung kembali/ke-reset ke commit pilihannya (menghilangkan commit yang setelah commit pilihan tersebut).
7. Buatlah grafik yang menggambarkan alur commit pada bagian Git Flow and Branching ini serta jelaskan! Grafik dapat berupa tulis tangan maupun menggunakan software.
   ![Grafik Git Flow](https://i.imgur.com/fNgrUJp.png)
   Dari development kita branch ke feature-a commit "edit base.html" setelah itu kita merge ke development. Dari development kita juga branch ke feature-b lalu commit "edit base.html" kita akan melakukan rebase tapi ternyata ada conflict sehingga harus kita resolve terlebih dahulu setelah resolve di-commit maka rebase akan berlanjut pada branch feature-b. Setelah selesai rebase maka bisa kita merge feature-b ke development
8. Apa kegunaan dari langkah di atas (Add HTTP Header Manager)?
   Langkah di atas untuk menambahkan HTTP header manager dan mengisi name = "content-type" dan value = "application/json" adalah untuk memastikan bahwa JMeter akan menerima/menghasilkan data dalam format JSON dan bukan dalam format lainnya sehingga dapat kita lihat hasilnya. Langkah ini pada dasarnya memberi tahu server tentang jenis konten yang akan dikirim/diterima.
9. Apa itu JSON Extractor? Sebutkan semua kegunaannya di Test Plan ini!
   JSON Extractor adalah komponen pada JMeter yang dapat digunakan untuk mendapatkan/ekstrak data dari JSON yang telah dibuat/didapatkan. Pada kasus test plan ini kita ingin mendapatkan idBuku yang terdapat pada JSON yang dibuat setelah proses HTTP Request Get Buku. Oleh karena itu, kita akan menggunakan JSON Extractor untuk mengambil idBuku yang dibuat tersebut. idBuku ini kemudian nantinya akan digunakan dalam proses selanjutnya yaitu HTTP Request Update Buku yang dapat kita lihat pada JSON Assertion Id.
10. Apa itu Assertions dalam JMeter? Sebutkan contoh 3 Assertions dan kegunaannya!
   Assertions pada JMEter adalah komponen/alat yang digunakan untuk memvalidasi response dari suatu request yang kita send ke server. 3 Contoh Assertion:
   1. Response Assertion -> Memeriksa apakah response yang didapatkan dari server cocok dengan pola atau teks yang ingin kita uji
   2. Size Assertion -> Memeriksa apakah ukuran response server sesuai dengan yang dites (misal apakah lebih besar/kecil/sama dengan ukuran bytes yang kita ingin uji)
   3. Duration Assertion -> Memeriksa apakah response server didapatkan pada maksimal batas waktu yang kita inginkan (misal 1929 ms)
11. Apa itu Number of Threads dan Ramp-up Period? Apa hubungan antar keduanya?
   Number of Thread adalah jumlah virtual user yang kita expect untuk mengakses server dan menjalankan steps yang telah kita set di JMeter-nya sedangkan Ramp-up period adalah batas/periode waktu di mana JMeter harus meningkatkan jumlah virtual user dari 0 ke jumlah Number of Thread yang sudah kita tentukan. Hubungannya seperti itu, jika ramp-up period pendek maka kemungkinan thread akan dimulai secara bersamaan jika panjang maka akan dimulai secara bertahap
12. Temuan menarik:
   Test ini bukan yang pertama kali saya jalankan tapi mungkin ini yang menurut saya paling "sempurna" dibandingkan tes-tes sebelumnya. Dpaat dilihat dalam summary report, total error pada HTTP request hanya berjumlah 9.76% dari total yang dijalankan dan itupun hanya error dari HTTP Request Random Request yang tentunya memiliki presentase 50-50 untuk gagal/berhasil. Selain dari itu, HTTP request-nya dapat berjalan tanpa adanya error. Dilihat dari throughput ternyata yang paling baik adalah HTTP request Create Buku yang throughput-nya adalah 6.8/sec.

   HTTP request Search Buku sepertinya menjadi HTTP request dengan ukuran terbesar yaitu mencapai 249258.5 Bytes rata-rata, jauh dibandingkan HTTP request yang lainnya. Hal ini tentu normal karena kita memiliki 1000++ buku yang akan dicari. Test Plan ini memiliki 5000 sampel dengan throughput mencapai 1.275.776/menit dan rata-rata 17649 ms dan ukuran 50068 Bytes.
   ![Results Tree](https://i.imgur.com/0qNomuv.png)
   ![Summary Report](https://i.imgur.com/CZIuUZf.png)
   ![Graph Results](https://i.imgur.com/HyVydUf.png)
   ![Assertion Result](https://i.imgur.com/PGCpzBp.png)
13. Jconsole, temuan menarik:
   Screenshot di bawah diambil ketika sedang menjalankan test pada JMeter, bisa dilihat semua grafik cenderung naik. Pada grafik CPU usage kita melihat grafik naik tajam ke atas ketika baru menjalankan tesnya namun turun ke bawah (walaupun tetap naik dari awal) setelah beberapa saat hal itu berarti menunjukkan bahwa beban kerja CPU yang dihasilkan dari melakukan testing besar di awal namun cenderung rendah dan fluaktif setelahnya dengan penggunaan 2.2%. Pada Memory Usage grafik juga fluaktif naik turun walaupun tentunya tetap naik dari awal hal ini berarti penggunaan memory ketika melakukan test plan fluaktif sekitar 200 - 600 Mb.

   Pada dua grafik lainnya cenderung datar/tidak fluaktif (setelah naik dari awal) jika dibandingkan dengan 2 grafik sebelumnya. Pada grafik Thread ini menujukkan jumlah thread yang sedang berjalan pada JVM jika kita lihat maka pada saat di-screenshot JVM sedang menjalankan 239 threads dengan peak-nya yaitu 242 threads. Pada classes juga sama seperti sebelumnya datar, kita juga dapat melihat bahwa terdapat 17.100 total kelas yang sedang dimuat pada JVM dan 56 kelas yang sudah dihapus dari JVM.
   ![Jconsole](https://i.imgur.com/ly8MIW1.png)
14. Apa itu Load Testing? Buatlah kesimpulan dari pengerjaan tutorial JMeter & JConsole ini.
   Load testing adalah salah satu tipe/metode pengujian yang dapat kita terapkan pada aplikasi/perangkat lunak. Pengujian ini bertujuan untuk menguji apakah sebuah aplikasi dapat berjalan dengan baik di bawah beban/tekanan yang berat atau di luar kapasitas biasanya. Dengan hasil load testing kita dapat menentukan batas kemampuan, responsiveness sistem, dan juga identifikasi maslaah yang mungkin timbul ketika banyak pengguna. Kesimpulan dari pengerjaan tutorial ini ternyata aplikasi dapat berjalan dengan baik di bawah load testing.
---
## Tutorial 5
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
