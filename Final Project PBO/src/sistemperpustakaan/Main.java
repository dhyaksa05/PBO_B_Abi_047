package sistemperpustakaan;

import java.util.Scanner;

// Kelas utama sebagai Main.java dengan menu interaktif
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Perpustakaan perpus;
    private static Pustakawan[] daftarPustakawan = new Pustakawan[10];
    private static int jumlahPustakawan = 0;
    private static Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];
    private static int jumlahMahasiswa = 0;
    private static Pengguna penggunaAktif = null;

    public static void main(String[] args) {
        inisialisasiData();

        boolean lanjutkan = true;
        while (lanjutkan) {
            if (penggunaAktif == null) {
                tampilkanMenuLogin();
                int pilihan = getInputAngka("Pilih menu: ");

                switch (pilihan) {
                    case 1:
                        loginPengguna();
                        break;
                    case 2:
                        tambahMahasiswa();
                        break;
                    case 3:
                        lanjutkan = false;
                        System.out.println("Terima kasih telah menggunakan Sistem Perpustakaan!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } else if (penggunaAktif instanceof Pustakawan) {
                tampilkanMenuPustakawan();
                int pilihan = getInputAngka("Pilih menu: ");
                menuPustakawan(pilihan);
            } else if (penggunaAktif instanceof Mahasiswa) {
                tampilkanMenuMahasiswa();
                int pilihan = getInputAngka("Pilih menu: ");
                menuMahasiswa(pilihan);
            }
        }

        scanner.close();
    }

    private static void inisialisasiData() {
        // Inisialisasi perpustakaan
        perpus = new Perpustakaan("Perpustakaan Pusat UMM", "Jl. Raya Tlogomas No. 246");

        // Tambahkan pustakawan default
        Pustakawan admin = new Pustakawan("Admin", "A047", "Kepala Pustakawan");
        daftarPustakawan[jumlahPustakawan++] = admin;

        // Tambahkan beberapa buku default
        perpus.tambahBuku(new Buku("Pemrograman Java", "Budi Santoso", "Komputer"));
        perpus.tambahBuku(new Buku("Algoritma dan Struktur Data", "Andi Wijaya", "Komputer"));
        perpus.tambahBuku(new Buku("Sejarah Indonesia", "Siti Rahayu", "Sejarah"));

        // Tambahkan mahasiswa default
        Mahasiswa mhs1 = new Mahasiswa("Abi", "047", "Informatika");
        daftarMahasiswa[jumlahMahasiswa++] = mhs1;

        // Tambahkan kartu perpustakaan ke mahasiswa
        admin.buatKartu(mhs1);

        System.out.println("Sistem Perpustakaan berhasil diinisialisasi.");
        System.out.println("Tanggal saat ini: " + java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    private static void tampilkanMenuLogin() {
        System.out.println("\n===== SISTEM PERPUSTAKAAN =====");
        System.out.println("1. Login");
        System.out.println("2. Daftar Mahasiswa Baru");
        System.out.println("3. Keluar");
        System.out.println("=============================");
    }

    private static void loginPengguna() {
        System.out.println("\n=== LOGIN PENGGUNA ===");
        String id = getInputTeks("Masukkan ID Anda: ");

        // Cek apakah ID adalah pustakawan
        for (int i = 0; i < jumlahPustakawan; i++) {
            if (daftarPustakawan[i].getId().equals(id)) {
                penggunaAktif = daftarPustakawan[i];
                System.out.println("Login berhasil sebagai Pustakawan: " + penggunaAktif.getNama());
                return;
            }
        }

        // Cek apakah ID adalah mahasiswa
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (daftarMahasiswa[i].getId().equals(id)) {
                penggunaAktif = daftarMahasiswa[i];
                System.out.println("Login berhasil sebagai Mahasiswa: " + penggunaAktif.getNama());
                return;
            }
        }

        System.out.println("ID tidak ditemukan. Login gagal!");
    }

    private static void tampilkanMenuPustakawan() {
        System.out.println("\n===== MENU PUSTAKAWAN =====");
        System.out.println("1. Lihat Daftar Buku");
        System.out.println("2. Tambah Buku Baru");
        System.out.println("3. Lihat Daftar Mahasiswa");
        System.out.println("4. Tambah Pustakawan Baru");
        System.out.println("5. Buat Kartu Perpustakaan");
        System.out.println("6. Perpanjang Kartu Perpustakaan");
        System.out.println("7. Cari Buku");
        System.out.println("8. Informasi Perpustakaan");
        System.out.println("9. Kelola Buku");
        System.out.println("10. Tampilkan Info Pustakawan");
        System.out.println("11. Bantu Pengguna");
        System.out.println("12. Kelola Beberapa Buku");
        System.out.println("13. Cari Katalog");
        System.out.println("14. Logout");
        System.out.println("=============================");
    }

    private static void tampilkanMenuMahasiswa() {
        System.out.println("\n===== MENU MAHASISWA =====");
        System.out.println("1. Lihat Daftar Buku");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Cari Buku");
        System.out.println("5. Informasi Kartu Perpustakaan");
        System.out.println("6. Belajar");
        System.out.println("7. Logout");
        System.out.println("=============================");
    }

    private static void menuPustakawan(int pilihan) {
        Pustakawan pustakawan = (Pustakawan) penggunaAktif;

        switch (pilihan) {
            case 1:
                lihatDaftarBuku();
                break;
            case 2:
                tambahBuku();
                break;
            case 3:
                lihatDaftarMahasiswa();
                break;
            case 4:
                tambahPustakawan();
                break;
            case 5:
                buatKartuPerpustakaan(pustakawan);
                break;
            case 6:
                perpanjangKartuPerpustakaan(pustakawan);
                break;
            case 7:
                cariBuku();
                break;
            case 8:
                lihatInfoPerpustakaan();
                break;
            case 9:
                kelolaBuku(pustakawan);
                break;
            case 10:
                pustakawan.tampilkanInfo();
                break;
            case 11:
                bantuPengguna(pustakawan);
                break;
            case 12:
                kelolaBeberapaBuku(pustakawan);
                break;
            case 13:
                cariKatalog(pustakawan);
                break;
            case 14:
                penggunaAktif = null;
                System.out.println("Logout berhasil!");
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void menuMahasiswa(int pilihan) {
        Mahasiswa mahasiswa = (Mahasiswa) penggunaAktif;

        switch (pilihan) {
            case 1:
                lihatDaftarBuku();
                break;
            case 2:
                pinjamBuku(mahasiswa);
                break;
            case 3:
                kembalikanBuku(mahasiswa);
                break;
            case 4:
                cariBuku();
                break;
            case 5:
                lihatInfoKartu(mahasiswa);
                break;
            case 6:
                mahasiswa.belajar();
                break;
            case 7:
                penggunaAktif = null;
                System.out.println("Logout berhasil!");
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void lihatDaftarBuku() {
        perpus.tampilkanDaftarBuku();
    }

    private static void tambahBuku() {
        System.out.println("\n=== TAMBAH BUKU BARU ===");
        String judul = getInputTeks("Masukkan judul buku: ");
        String penulis = getInputTeks("Masukkan nama penulis: ");
        String kategori = getInputTeks("Masukkan kategori buku: ");

        Buku bukuBaru = new Buku(judul, penulis, kategori);
        perpus.tambahBuku(bukuBaru);
    }

    private static void lihatDaftarMahasiswa() {
        System.out.println("\n=== DAFTAR MAHASISWA ===");
        if (jumlahMahasiswa == 0) {
            System.out.println("Belum ada mahasiswa terdaftar.");
            return;
        }

        for (int i = 0; i < jumlahMahasiswa; i++) {
            System.out.println((i+1) + ". " + daftarMahasiswa[i].getNama() +
                    " (ID: " + daftarMahasiswa[i].getId() + ") - " +
                    (daftarMahasiswa[i].getKartu() != null ?
                            "Memiliki Kartu" : "Belum Memiliki Kartu"));
        }
    }

    private static void tambahPustakawan() {
        System.out.println("\n=== TAMBAH PUSTAKAWAN BARU ===");
        String nama = getInputTeks("Masukkan nama pustakawan: ");
        String id = getInputTeks("Masukkan ID pustakawan: ");
        String jabatan = getInputTeks("Masukkan jabatan: ");

        Pustakawan pustakawanBaru = new Pustakawan(nama, id, jabatan);
        daftarPustakawan[jumlahPustakawan++] = pustakawanBaru;
        System.out.println("Pustakawan berhasil ditambahkan!");
    }

    private static void buatKartuPerpustakaan(Pustakawan pustakawan) {
        System.out.println("\n=== BUAT KARTU PERPUSTAKAAN ===");
        lihatDaftarMahasiswa();

        int pilihan = getInputAngka("Pilih nomor mahasiswa: ") - 1;
        if (pilihan >= 0 && pilihan < jumlahMahasiswa) {
            if (daftarMahasiswa[pilihan].getKartu() != null) {
                System.out.println("Mahasiswa sudah memiliki kartu perpustakaan!");
                boolean perpanjang = getInputYaTidak("Apakah ingin memperpanjang masa berlaku kartu? (y/n): ");
                if (perpanjang) {
                    int hari = getInputAngka("Masukkan jumlah hari perpanjangan: ");
                    pustakawan.perpanjangKartu(daftarMahasiswa[pilihan], hari);
                }
                return;
            }
            pustakawan.buatKartu(daftarMahasiswa[pilihan]);
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    private static void perpanjangKartuPerpustakaan(Pustakawan pustakawan) {
        System.out.println("\n=== PERPANJANG KARTU PERPUSTAKAAN ===");

        // Tampilkan hanya mahasiswa yang memiliki kartu
        System.out.println("\nDaftar Mahasiswa dengan Kartu Perpustakaan:");
        int counter = 1;
        int[] indexMhs = new int[jumlahMahasiswa]; // Untuk menyimpan index asli

        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (daftarMahasiswa[i].getKartu() != null) {
                System.out.println(counter + ". " + daftarMahasiswa[i].getNama() +
                        " (ID: " + daftarMahasiswa[i].getId() + ")");
                indexMhs[counter-1] = i;
                counter++;
            }
        }

        if (counter == 1) {
            System.out.println("Tidak ada mahasiswa yang memiliki kartu perpustakaan.");
            return;
        }

        int pilihan = getInputAngka("Pilih nomor mahasiswa: ");
        if (pilihan >= 1 && pilihan < counter) {
            int index = indexMhs[pilihan-1];
            daftarMahasiswa[index].getKartu().tampilkanInfo();
            int hari = getInputAngka("Masukkan jumlah hari perpanjangan: ");
            pustakawan.perpanjangKartu(daftarMahasiswa[index], hari);
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    private static void kelolaBuku(Pustakawan pustakawan) {
        System.out.println("\n=== KELOLA BUKU ===");
        perpus.tampilkanDaftarBuku();

        int pilihan = getInputAngka("Pilih nomor buku: ") - 1;
        if (pilihan >= 0 && pilihan < perpus.getJumlahBuku()) {
            Buku buku = perpus.getBuku(pilihan);
            System.out.println("\n1. Periksa buku");
            System.out.println("2. Perbaiki buku");
            System.out.println("3. Ubah kategori buku");

            int aksi = getInputAngka("Pilih aksi: ");
            switch (aksi) {
                case 1:
                    pustakawan.kelolaBuku(buku);
                    break;
                case 2:
                    pustakawan.kelolaBuku(buku, "perbaikan");
                    break;
                case 3:
                    String kategori = getInputTeks("Masukkan kategori baru: ");
                    buku.setKategori(kategori);
                    pustakawan.kelolaBuku(buku, "perubahan kategori");
                    break;
                default:
                    System.out.println("Aksi tidak valid!");
            }
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    private static void pinjamBuku(Mahasiswa mahasiswa) {
        System.out.println("\n=== PEMINJAMAN BUKU ===");

        // Periksa apakah mahasiswa memiliki kartu
        if (mahasiswa.getKartu() == null) {
            System.out.println("Anda belum memiliki kartu perpustakaan.");
            return;
        }

        // Tampilkan daftar buku
        perpus.tampilkanDaftarBuku();

        int indeksBuku = getInputAngka("Masukkan nomor buku yang ingin dipinjam: ") - 1;

        if (indeksBuku >= 0 && indeksBuku < perpus.getJumlahBuku()) {
            Buku buku = perpus.getBuku(indeksBuku);
            mahasiswa.pinjamBuku(buku);
        } else {
            System.out.println("Nomor buku tidak valid!");
        }
    }

    private static void kembalikanBuku(Mahasiswa mahasiswa) {
        System.out.println("\n=== PENGEMBALIAN BUKU ===");

        // Tampilkan daftar buku yang sedang dipinjam
        System.out.println("\nDaftar Buku yang Tidak Tersedia (Mungkin Dipinjam):");
        int counter = 1;
        Buku[] daftarBuku = perpus.getKoleksiBuku();
        for (int i = 0; i < daftarBuku.length; i++) {
            if (!daftarBuku[i].isKetersediaan()) {
                System.out.println(counter + ". " + daftarBuku[i].getJudul());
                counter++;
            }
        }

        if (counter == 1) {
            System.out.println("Tidak ada buku yang sedang dipinjam.");
            return;
        }

        int indeksBuku = getInputAngka("Masukkan nomor buku yang ingin dikembalikan: ") - 1;

        // Hitung ulang indeks sebenarnya
        counter = 0;
        int indeksSebenarnya = -1;
        for (int i = 0; i < daftarBuku.length; i++) {
            if (!daftarBuku[i].isKetersediaan()) {
                if (counter == indeksBuku) {
                    indeksSebenarnya = i;
                    break;
                }
                counter++;
            }
        }

        if (indeksSebenarnya >= 0) {
            mahasiswa.kembalikanBuku(daftarBuku[indeksSebenarnya]);
        } else {
            System.out.println("Nomor buku tidak valid!");
        }
    }

    private static void lihatInfoKartu(Mahasiswa mahasiswa) {
        if (mahasiswa.getKartu() != null) {
            mahasiswa.getKartu().tampilkanInfo();
        } else {
            System.out.println("Anda belum memiliki kartu perpustakaan.");
        }
    }

    private static Mahasiswa tambahMahasiswa() {
        System.out.println("\n=== DAFTARKAN MAHASISWA BARU ===");
        String nama = getInputTeks("Masukkan nama mahasiswa: ");
        String id = getInputTeks("Masukkan ID mahasiswa: ");
        String jurusan = getInputTeks("Masukkan jurusan: ");

        Mahasiswa mahasiswa = new Mahasiswa(nama, id, jurusan);
        daftarMahasiswa[jumlahMahasiswa++] = mahasiswa;
        System.out.println("Mahasiswa berhasil didaftarkan!");

        return mahasiswa;
    }

    private static void cariBuku() {
        System.out.println("\n=== PENCARIAN BUKU ===");
        String katakunci = getInputTeks("Masukkan kata kunci: ");

        System.out.println("\nHasil pencarian untuk '" + katakunci + "':");
        boolean ditemukan = false;

        Buku[] daftarBuku = perpus.getKoleksiBuku();
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().toLowerCase().contains(katakunci.toLowerCase()) ||
                    buku.getPenulis().toLowerCase().contains(katakunci.toLowerCase()) ||
                    buku.getKategori().toLowerCase().contains(katakunci.toLowerCase())) {
                buku.tampilkanInfo();
                System.out.println();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ditemukan buku yang sesuai dengan kata kunci.");
        }
    }

    private static void lihatInfoPerpustakaan() {
        perpus.tampilkanInfo();
    }

    private static void bantuPengguna(Pustakawan pustakawan) {
        System.out.println("\n=== BANTU PENGGUNA ===");
        lihatDaftarMahasiswa();

        int pilihan = getInputAngka("Pilih nomor mahasiswa yang ingin dibantu: ") - 1;
        if (pilihan >= 0 && pilihan < jumlahMahasiswa) {
            pustakawan.bantuPengguna(daftarMahasiswa[pilihan]);
            System.out.println("Pustakawan " + pustakawan.getNama() + " memberikan bantuan kepada " + daftarMahasiswa[pilihan].getNama());
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    private static void kelolaBeberapaBuku(Pustakawan pustakawan) {
        System.out.println("\n=== KELOLA BEBERAPA BUKU ===");
        perpus.tampilkanDaftarBuku();

        System.out.println("\nPilih beberapa buku untuk dikelola (pisahkan dengan koma, contoh: 1,3,5)");
        String input = getInputTeks("Masukkan nomor buku: ");
        String[] pilihan = input.split(",");

        if (pilihan.length > 0) {
            Buku[] bukuDipilih = new Buku[pilihan.length];
            int count = 0;

            for (String p : pilihan) {
                try {
                    int indeks = Integer.parseInt(p.trim()) - 1;
                    if (indeks >= 0 && indeks < perpus.getJumlahBuku()) {
                        bukuDipilih[count++] = perpus.getBuku(indeks);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka: " + p);
                }
            }

            // Buat array dengan ukuran yang tepat berdasarkan buku yang valid
            Buku[] bukuFinal = new Buku[count];
            System.arraycopy(bukuDipilih, 0, bukuFinal, 0, count);

            pustakawan.kelolaBuku(bukuFinal);
        } else {
            System.out.println("Tidak ada buku yang dipilih!");
        }
    }

    private static void cariKatalog(Pustakawan pustakawan) {
        System.out.println("\n=== CARI KATALOG ===");
        String katakunci = getInputTeks("Masukkan kata kunci untuk pencarian katalog: ");
        pustakawan.cari(katakunci);
    }

    private static String getInputTeks(String pesan) {
        System.out.print(pesan);
        return scanner.nextLine();
    }

    private static int getInputAngka(String pesan) {
        int angka = 0;
        boolean inputValid = false;

        while (!inputValid) {
            try {
                System.out.print(pesan);
                angka = Integer.parseInt(scanner.nextLine());
                inputValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }

        return angka;
    }

    private static boolean getInputYaTidak(String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("y") || input.equals("ya")) {
                return true;
            } else if (input.equals("n") || input.equals("tidak")) {
                return false;
            } else {
                System.out.println("Input tidak valid! Masukkan 'y' atau 'n'.");
            }
        }
    }
}