package com.example.proyek_mad.data

object MockDB {
    var selectedMateri:Int = 1
    var selectedKelas:Int = 101

    var currentUser:User = User(
        1,
        "valen",
        "valen@gmail.com",
        "valen123",
        "Valensia Clarins"
    )

    var courses:List<Course> = listOf(
        Course(
            kelas_id = 101,
            nama_kelas = "Pengantar Pemrograman",
            deskripsi_kelas = "Mempelajari dasar-dasar pemrograman menggunakan Kotlin.",
            prasyarat_kelas_id = 0 // No prerequisite
        ),
        Course(
            kelas_id = 102,
            nama_kelas = "Struktur Data Lanjut",
            deskripsi_kelas = "Membahas struktur data kompleks dan algoritma efisien.",
            prasyarat_kelas_id = 101 // Requires Pengantar Pemrograman
        ),
        Course(
            kelas_id = 201,
            nama_kelas = "Desain Antarmuka Pengguna",
            deskripsi_kelas = "Fokus pada prinsip desain UI/UX untuk aplikasi mobile.",
            prasyarat_kelas_id = 0 // No prerequisite
        ),
        Course(
            kelas_id = 202,
            nama_kelas = "Pengembangan Aplikasi Android",
            deskripsi_kelas = "Membangun aplikasi Android dari awal hingga siap rilis.",
            prasyarat_kelas_id = 101 // Requires Pengantar Pemrograman
        ),
        Course(
            kelas_id = 301,
            nama_kelas = "Basis Data Relasional",
            deskripsi_kelas = "Mempelajari konsep dan implementasi basis data relasional dengan SQL.",
            prasyarat_kelas_id = 101 // Requires Pengantar Pemrograman
        )
    )

    var modules:List<Module> = listOf(
        Module(
            materi_id = 1,
            kelas_id = 101,
            judul_materi = "Pengenalan Kotlin",
            konten_materi = "Kotlin adalah bahasa pemrograman modern yang digunakan untuk Android.",
            urutan_materi_dalam_kelas = 1,
            deskripsi_singkat = "Dasar-dasar bahasa Kotlin untuk pemula."
        ),
        Module(
            materi_id = 2,
            kelas_id = 101,
            judul_materi = "Variabel dan Tipe Data",
            konten_materi = "Kotlin memiliki tipe data seperti Int, String, Boolean, dll.",
            urutan_materi_dalam_kelas = 2,
            deskripsi_singkat = "Memahami tipe data dan variabel dalam Kotlin."
        ),
        Module(
            materi_id = 3,
            kelas_id = 101,
            judul_materi = "Fungsi dan Control Flow",
            konten_materi = "Fungsi di Kotlin bisa dideklarasikan dengan fun. Kotlin juga mendukung if, when, for, while.",
            urutan_materi_dalam_kelas = 3,
            deskripsi_singkat = "Cara menggunakan fungsi dan kontrol alur di Kotlin."
        )
    )

    val quizzes: List<Quiz> = listOf(
        Quiz(
            kuis_id = 1,
            kelas_id = 101,
            judul_kuis = "Kuis Pengantar Pemrograman",
//            batas_waktu_pengerjaan_menit = "30",
            batas_waktu_pengerjaan_menit = "2",
            nilai_minimum_lulus = 60
        ),
        Quiz(
            kuis_id = 2,
            kelas_id = 102,
            judul_kuis = "Kuis Struktur Data Lanjut",
//            batas_waktu_pengerjaan_menit = "40",
            batas_waktu_pengerjaan_menit = "1",
            nilai_minimum_lulus = 65
        ),
        Quiz(
            kuis_id = 3,
            kelas_id = 201,
            judul_kuis = "Kuis Desain Antarmuka Pengguna",
//            batas_waktu_pengerjaan_menit = "30",
            batas_waktu_pengerjaan_menit = "2",
            nilai_minimum_lulus = 70
        ),
        Quiz(
            kuis_id = 4,
            kelas_id = 202,
            judul_kuis = "Kuis Pengembangan Aplikasi Android",
//            batas_waktu_pengerjaan_menit = "45",
            batas_waktu_pengerjaan_menit = "1",
            nilai_minimum_lulus = 70
        ),
        Quiz(
            kuis_id = 5,
            kelas_id = 301,
            judul_kuis = "Kuis Basis Data Relasional",
//            batas_waktu_pengerjaan_menit = "35",
            batas_waktu_pengerjaan_menit = "1",
            nilai_minimum_lulus = 65
        )
    )

    val quizAttempts: MutableList<QuizAttempt> = mutableListOf(
        QuizAttempt(1, 1, 1, 1, 60, 0),
        QuizAttempt(2, 1, 1, 1, 80, 1)
    )

    val quizAnswers: MutableList<QuizAnswer> = mutableListOf(
        QuizAnswer(1, 1, 1, 1, 1),
        QuizAnswer(2, 1, 2, 4, 0),
        QuizAnswer(3, 2, 1, 2, 0),
        QuizAnswer(4, 2, 2, 3, 0)
    )

    val questions: List<Question> = listOf(
        // Kuis 1: Pengantar Pemrograman
        Question(soal_id = 1, kuis_id = 1, teks_soal = "Apa itu variabel dalam pemrograman?", urutan_soal = 1, poin_soal = 40),
        Question(soal_id = 2, kuis_id = 1, teks_soal = "Jelaskan perbedaan antara val dan var di Kotlin.", urutan_soal = 2, poin_soal = 30),
        Question(soal_id = 3, kuis_id = 1, teks_soal = "Buatlah fungsi sederhana yang mengembalikan nilai 'Hello World'.", urutan_soal = 3, poin_soal = 30),

        // Kuis 2: Struktur Data Lanjut
        Question(soal_id = 4, kuis_id = 2, teks_soal = "Apa perbedaan antara stack dan queue?", urutan_soal = 1, poin_soal = 40),
        Question(soal_id = 5, kuis_id = 2, teks_soal = "Jelaskan bagaimana cara kerja algoritma quicksort.", urutan_soal = 2, poin_soal = 30),
        Question(soal_id = 6, kuis_id = 2, teks_soal = "Apa keuntungan menggunakan tree dibandingkan array?", urutan_soal = 3, poin_soal = 30),

        // Kuis 3: Desain Antarmuka Pengguna
        Question(soal_id = 7, kuis_id = 3, teks_soal = "Apa prinsip dasar desain UI yang baik?", urutan_soal = 1, poin_soal = 40),
        Question(soal_id = 8, kuis_id = 3, teks_soal = "Jelaskan pentingnya hierarki visual dalam desain.", urutan_soal = 2, poin_soal = 30),
        Question(soal_id = 9, kuis_id = 3, teks_soal = "Apa itu prototyping dalam UI/UX design?", urutan_soal = 3, poin_soal = 30),

        // Kuis 4: Pengembangan Aplikasi Android
        Question(soal_id = 10, kuis_id = 4, teks_soal = "Apa itu activity dalam Android?", urutan_soal = 1, poin_soal = 40),
        Question(soal_id = 11, kuis_id = 4, teks_soal = "Jelaskan siklus hidup (lifecycle) sebuah activity.", urutan_soal = 2, poin_soal = 30),
        Question(soal_id = 12, kuis_id = 4, teks_soal = "Bagaimana cara menambahkan tombol dan menangani kliknya?", urutan_soal = 3, poin_soal = 30),

        // Kuis 5: Basis Data Relasional
        Question(soal_id = 13, kuis_id = 5, teks_soal = "Apa itu primary key dalam database relasional?", urutan_soal = 1, poin_soal = 40),
        Question(soal_id = 14, kuis_id = 5, teks_soal = "Tuliskan contoh perintah SQL untuk membuat tabel.", urutan_soal = 2, poin_soal = 30),
        Question(soal_id = 15, kuis_id = 5, teks_soal = "Apa perbedaan antara JOIN dan SUBQUERY dalam SQL?", urutan_soal = 3, poin_soal = 30)
    )

    val options: List<Option> = listOf(
        // Soal 1
        Option(1, 1, "Tempat menyimpan data dalam program", 1),
        Option(2, 1, "Sebuah fungsi dalam Kotlin", 0),
        Option(3, 1, "Kumpulan perintah dalam bahasa SQL", 0),
        Option(4, 1, "Library eksternal", 0),

        // Soal 2
        Option(5, 2, "val untuk variabel immutable, var untuk mutable", 1),
        Option(6, 2, "val dan var tidak berbeda", 0),
        Option(7, 2, "val untuk fungsi, var untuk class", 0),
        Option(8, 2, "var hanya bisa digunakan di loop", 0),

        // Soal 3
        Option(9, 3, "fun hello() = \"Hello World\"", 1),
        Option(10, 3, "def hello = Hello", 0),
        Option(11, 3, "hello := function() Hello", 0),
        Option(12, 3, "echo 'Hello World'", 0),

        // Soal 4
        Option(13, 4, "Stack LIFO, Queue FIFO", 1),
        Option(14, 4, "Stack adalah array, Queue adalah tree", 0),
        Option(15, 4, "Queue hanya untuk grafik", 0),
        Option(16, 4, "Stack dan Queue tidak berbeda", 0),

        // Soal 5
        Option(17, 5, "Memilih elemen pivot dan membaginya", 1),
        Option(18, 5, "Mencari nilai tengah dengan brute force", 0),
        Option(19, 5, "Menggunakan struktur tree secara langsung", 0),
        Option(20, 5, "Memasukkan semua elemen ke dalam stack", 0),

        // Soal 6
        Option(21, 6, "Tree lebih efisien dalam pencarian data", 1),
        Option(22, 6, "Array memiliki struktur data dinamis", 0),
        Option(23, 6, "Tree digunakan hanya untuk tampilan", 0),
        Option(24, 6, "Array lebih cepat dari semua struktur data", 0),

        // Soal 7
        Option(25, 7, "Konsistensi, keterbacaan, dan kesederhanaan", 1),
        Option(26, 7, "Kompleksitas dan variasi", 0),
        Option(27, 7, "Warna-warni dan animasi", 0),
        Option(28, 7, "Desain berdasarkan perasaan", 0),

        // Soal 8
        Option(29, 8, "Untuk mengarahkan perhatian pengguna", 1),
        Option(30, 8, "Hanya untuk membuat layout cantik", 0),
        Option(31, 8, "Untuk memperbanyak teks", 0),
        Option(32, 8, "Agar terlihat seperti website", 0),

        // Soal 9
        Option(33, 9, "Simulasi desain antarmuka sebelum coding", 1),
        Option(34, 9, "Menambahkan data ke database", 0),
        Option(35, 9, "Membuat dokumentasi kode", 0),
        Option(36, 9, "Menentukan spesifikasi teknis server", 0),

        // Soal 10
        Option(37, 10, "Komponen dasar UI di Android", 1),
        Option(38, 10, "Fungsi utama Kotlin", 0),
        Option(39, 10, "Library untuk SQL", 0),
        Option(40, 10, "Nama lain dari RecyclerView", 0),

        // Soal 11
        Option(41, 11, "onCreate -> onStart -> onResume", 1),
        Option(42, 11, "onStart -> onPause -> onDestroy", 0),
        Option(43, 11, "onResume -> onDestroy -> onPause", 0),
        Option(44, 11, "onCreate -> onPause -> onStart", 0),

        // Soal 12
        Option(45, 12, "Menggunakan Button dan setOnClickListener", 1),
        Option(46, 12, "Membuat layout dengan HTML", 0),
        Option(47, 12, "Menambahkan event di database", 0),
        Option(48, 12, "Menggunakan Toast saja", 0),

        // Soal 13
        Option(49, 13, "Kolom unik yang menjadi identitas data", 1),
        Option(50, 13, "Nama tabel", 0),
        Option(51, 13, "Jenis data kolom", 0),
        Option(52, 13, "Perintah untuk menambahkan data", 0),

        // Soal 14
        Option(53, 14, "CREATE TABLE siswa (id INT, nama TEXT);", 1),
        Option(54, 14, "SELECT FROM siswa;", 0),
        Option(55, 14, "JOIN siswa ON id;", 0),
        Option(56, 14, "INSERT siswa VALUES (1, 'Ani');", 0),

        // Soal 15
        Option(57, 15, "JOIN menggabungkan tabel, SUBQUERY menanamkan query dalam query", 1),
        Option(58, 15, "Keduanya sama", 0),
        Option(59, 15, "JOIN hanya untuk Oracle", 0),
        Option(60, 15, "SUBQUERY lebih cepat dari JOIN dalam semua kasus", 0)
    )
}