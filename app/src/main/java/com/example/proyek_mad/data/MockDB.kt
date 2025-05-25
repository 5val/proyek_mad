package com.example.proyek_mad.data

object MockDB {
    var selectedMateri:Int = 1
    var selectedKelas:Int = 101

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