# Latihan GUI Java (Swing) - To-Do List Tugas Kuliah

**NIM :** F1D02510123
**Nama :** Muhammad Ariq Nabhan

Aplikasi GUI untuk mencatat tugas kuliah.

## Fitur
- Tambah tugas (tombol atau tekan **Enter**) lengkap dengan mata kuliah
- Tandai tugas **Penting** (diberi tanda `(!)`)
- Tandai / batalkan selesai (tombol atau **klik 2x** pada tugas)
- Hapus tugas (dengan konfirmasi)
- Bersihkan semua tugas yang sudah selesai
- Penghitung total dan jumlah tugas selesai
- Konfirmasi saat menutup aplikasi

## Cara Menjalankan

```bash
javac TodoKuliah.java
java TodoKuliah
```

## 5 Komponen Utama GUI yang Digunakan

| No | Komponen | Class / Interface | Fungsi |
|---|---|---|---|
| 1 | Top-level Container | `JFrame` | Jendela utama aplikasi |
| 2 | Intermediate Container | `JPanel`, `JScrollPane` | `JPanel` mengelompokkan komponen, `JScrollPane` membuat daftar bisa di-scroll |
| 3 | Atomic Component | `JLabel`, `JTextField`, `JComboBox`, `JCheckBox`, `JButton`, `JList` | Label, input tugas, pilihan mata kuliah, penanda penting, tombol, dan daftar tugas |
| 4 | Layout Classes | `BorderLayout`, `FlowLayout`, `GridLayout` | `BorderLayout` untuk frame, `GridLayout` untuk baris input dan tombol aksi, `FlowLayout` untuk isi tiap baris input |
| 5 | Event Listener | `ActionListener`, `KeyAdapter`, `MouseAdapter`, `WindowAdapter` | Klik tombol, tombol Enter, klik 2x pada daftar, dan penutupan jendela |

## Screenshot

> `screenshot/tampilan.png`