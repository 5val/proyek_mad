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
}