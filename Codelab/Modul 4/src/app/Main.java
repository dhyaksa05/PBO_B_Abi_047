/ File: src/app/Main.java
package app;

import perpustakaan.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Buku> daftarBuku = new ArrayList<>();
    private static ArrayList<Anggota> daftarAnggota = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inisialisasi beberapa buku
        daftarBuku.add(new NonFiksi("Madilog", "Tan Malaka", "Sejarah & Ilmu Pengetahuan"));
        daftarBuku.add(new Fiksi("Hainuwele: Sang Putri Kelapa", "Lilis Hu", "Dongeng"));
        daftarBuku.add(new NonFiksi("Sapiens", "Yuval Noah Harari", "Antropologi"));
        daftarBuku.add(new Fiksi("Laskar Pelangi", "Andrea Hirata", "Novel"));

        boolean lanjut = true;

        while (lanjut) {
            tampilkanMenu();
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    tambahAnggota();
                    break;
                case 2:
                    tampilkanDaftarAnggota();
                    break;
                case 3:
                    tambahBuku();
                    break;
                case 4:
                    tampilkanDaftarBuku();
                    break;
                case 5:
                    pinjamBuku();
                    break;
                case 6:
                    kembalikanBuku();
                    break;
                case 7:
                    lanjut = false;
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n=== SISTEM MANAJEMEN PERPUSTAKAAN ===");
        System.out.println("1. Tambah Anggota");
        System.out.println("2. Lihat Daftar Anggota");
        System.out.println("3. Tambah Buku");
        System.out.println("4. Lihat Daftar Buku");
        System.out.println("5. Pinjam Buku");
        System.out.println("6. Kembalikan Buku");
        System.out.println("7. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    private static void tambahAnggota() {
        System.out.println("\n=== TAMBAH ANGGOTA ===");
        System.out.print("Nama: ");
        String nama = scanner.nextLine();

        System.out.print("ID Anggota (3 digit): ");
        String idAnggota = scanner.nextLine();

        // Validasi ID anggota
        if (idAnggota.length() != 3) {
            System.out.println("ID Anggota harus terdiri dari 3 digit.");
            return;
        }

        Anggota anggotaBaru = new Anggota(nama, idAnggota);
        daftarAnggota.add(anggotaBaru);
        System.out.println("Anggota berhasil ditambahkan!");
        anggotaBaru.displayInfo();
    }

    private static void tampilkanDaftarAnggota() {
        System.out.println("\n=== DAFTAR ANGGOTA ===");
        if (daftarAnggota.isEmpty()) {
            System.out.println("Belum ada anggota terdaftar.");
            return;
        }

        for (Anggota anggota : daftarAnggota) {
            anggota.displayInfo();
        }
    }

    private static void tambahBuku() {
        System.out.println("\n=== TAMBAH BUKU ===");
        System.out.print("Judul: ");
        String judul = scanner.nextLine();

        System.out.print("Penulis: ");
        String penulis = scanner.nextLine();

        System.out.println("Tipe Buku:");
        System.out.println("1. Fiksi");
        System.out.println("2. Non-Fiksi");
        System.out.print("Pilihan: ");
        int tipeBuku = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        if (tipeBuku == 1) {
            System.out.print("Genre: ");
            String genre = scanner.nextLine();
            daftarBuku.add(new Fiksi(judul, penulis, genre));
            System.out.println("Buku Fiksi berhasil ditambahkan!");
        } else if (tipeBuku == 2) {
            System.out.print("Bidang: ");
            String bidang = scanner.nextLine();
            daftarBuku.add(new NonFiksi(judul, penulis, bidang));
            System.out.println("Buku Non-Fiksi berhasil ditambahkan!");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private static void tampilkanDaftarBuku() {
        System.out.println("\n=== DAFTAR BUKU ===");
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku terdaftar.");
            return;
        }

        for (Buku buku : daftarBuku) {
            buku.displayInfo();
        }
    }

    private static void pinjamBuku() {
        System.out.println("\n=== PINJAM BUKU ===");
        if (daftarAnggota.isEmpty() || daftarBuku.isEmpty()) {
            System.out.println("Daftar anggota atau buku masih kosong.");
            return;
        }

        // Pilih anggota
        System.out.println("Pilih Anggota:");
        for (int i = 0; i < daftarAnggota.size(); i++) {
            System.out.println((i + 1) + ". " + daftarAnggota.get(i).getNama() + " (ID: " + daftarAnggota.get(i).getIdAnggota() + ")");
        }

        System.out.print("Pilihan: ");
        int pilihanAnggota = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        if (pilihanAnggota < 1 || pilihanAnggota > daftarAnggota.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Anggota anggota = daftarAnggota.get(pilihanAnggota - 1);

        // Pilih buku
        System.out.println("Pilih Buku:");
        for (int i = 0; i < daftarBuku.size(); i++) {
            System.out.println((i + 1) + ". " + daftarBuku.get(i).getJudul());
        }

        System.out.print("Pilihan: ");
        int pilihanBuku = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        if (pilihanBuku < 1 || pilihanBuku > daftarBuku.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Buku buku = daftarBuku.get(pilihanBuku - 1);

        // Tanyakan durasi peminjaman
        System.out.print("Durasi peminjaman (dalam hari, tekan Enter untuk default): ");
        String inputDurasi = scanner.nextLine();

        if (inputDurasi.isEmpty()) {
            anggota.pinjamBuku(buku.getJudul());
        } else {
            try {
                int durasi = Integer.parseInt(inputDurasi);
                anggota.pinjamBuku(buku.getJudul(), durasi);
            } catch (NumberFormatException e) {
                System.out.println("Format durasi tidak valid. Menggunakan peminjaman tanpa durasi.");
                anggota.pinjamBuku(buku.getJudul());
            }
        }
    }

    private static void kembalikanBuku() {
        System.out.println("\n=== KEMBALIKAN BUKU ===");
        if (daftarAnggota.isEmpty() || daftarBuku.isEmpty()) {
            System.out.println("Daftar anggota atau buku masih kosong.");
            return;
        }

        // Pilih anggota
        System.out.println("Pilih Anggota:");
        for (int i = 0; i < daftarAnggota.size(); i++) {
            System.out.println((i + 1) + ". " + daftarAnggota.get(i).getNama() + " (ID: " + daftarAnggota.get(i).getIdAnggota() + ")");
        }

        System.out.print("Pilihan: ");
        int pilihanAnggota = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        if (pilihanAnggota < 1 || pilihanAnggota > daftarAnggota.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Anggota anggota = daftarAnggota.get(pilihanAnggota - 1);

        // Pilih buku
        System.out.println("Pilih Buku yang akan dikembalikan:");
        for (int i = 0; i < daftarBuku.size(); i++) {
            System.out.println((i + 1) + ". " + daftarBuku.get(i).getJudul());
        }

        System.out.print("Pilihan: ");
        int pilihanBuku = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        if (pilihanBuku < 1 || pilihanBuku > daftarBuku.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Buku buku = daftarBuku.get(pilihanBuku - 1);
        anggota.kembalikanBuku(buku.getJudul());
    }
}