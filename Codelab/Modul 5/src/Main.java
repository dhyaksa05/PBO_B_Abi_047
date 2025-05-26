import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Barang> daftarBarang = new ArrayList<>();

        // Menambahkan beberapa objek Barang sebagai data awal
        daftarBarang.add(new Barang("Laptop", 5));
        daftarBarang.add(new Barang("Mouse", 10));
        daftarBarang.add(new Barang("Keyboard", 8));

        // Membuat instance Scanner untuk menerima input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                // Menampilkan menu utama
                System.out.println("\n=== SISTEM MANAJEMEN STOK BARANG ===");
                System.out.println("1. Tambah Barang Baru");
                System.out.println("2. Tampilkan Semua Barang");
                System.out.println("3. Kurangi Stok Barang");
                System.out.println("0. Keluar");
                System.out.print("Pilih opsi (0-3): ");

                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer

                switch (pilihan) {
                    case 1:
                        tambahBarang(scanner, daftarBarang);
                        break;
                    case 2:
                        tampilkanSemuaBarang(daftarBarang);
                        break;
                    case 3:
                        kurangiStokBarang(scanner, daftarBarang);
                        break;
                    case 0:
                        running = false;
                        System.out.println("Terima kasih!");
                        break;
                    default:
                        System.out.println("Opsi tidak valid. Silakan pilih 0-3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                scanner.nextLine(); // Membersihkan buffer
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Menutup Scanner untuk menghindari resource leak
        scanner.close();
    }

    // Implementasi Opsi 1 (Tambah Barang)
    private static void tambahBarang(Scanner scanner, ArrayList<Barang> daftarBarang) {
        System.out.print("Masukkan nama barang: ");
        String nama = scanner.nextLine();

        int stok = 0;
        try {
            System.out.print("Masukkan stok awal: ");
            stok = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (stok < 0) {
                System.out.println("Error: Stok tidak boleh negatif!");
                return;
            }

            // Jika input valid, buat objek Barang baru dan tambahkan ke daftarBarang
            Barang barangBaru = new Barang(nama, stok);
            daftarBarang.add(barangBaru);
            System.out.println("Barang berhasil ditambahkan: " + nama + " (Stok: " + stok + ")");
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Membersihkan buffer
            System.out.println("Error: Input stok harus berupa angka!");
        }
    }

    // Implementasi Opsi 2 (Tampilkan Semua Barang)
    private static void tampilkanSemuaBarang(ArrayList<Barang> daftarBarang) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Stok barang kosong.");
            return;
        }

        System.out.println("\n=== DAFTAR STOK BARANG ===");
        for (int i = 0; i < daftarBarang.size(); i++) {
            Barang barang = daftarBarang.get(i);
            System.out.println((i + 1) + ". " + barang.getNama() + " (Stok: " + barang.getStok() + ")");
        }
    }

    // Implementasi Opsi 3 (Kurangi Stok Barang)
    private static void kurangiStokBarang(Scanner scanner, ArrayList<Barang> daftarBarang) {
        // Tampilkan daftar barang dengan indeks
        System.out.println("\n=== DAFTAR BARANG ===");
        for (int i = 0; i < daftarBarang.size(); i++) {
            Barang barang = daftarBarang.get(i);
            System.out.println(i + ". " + barang.getNama() + " (Stok: " + barang.getStok() + ")");
        }

        try {
            System.out.print("Masukkan nomor indeks barang: ");
            int indeks = scanner.nextInt();

            if (indeks < 0 || indeks >= daftarBarang.size()) {
                throw new IndexOutOfBoundsException("Indeks tidak valid untuk daftarBarang.");
            }

            System.out.print("Masukkan jumlah stok yang akan diambil: ");
            int jumlahDiambil = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (jumlahDiambil <= 0) {
                System.out.println("Error: Jumlah yang diambil harus positif!");
                return;
            }

            Barang barang = daftarBarang.get(indeks);

            // Validasi stok cukup
            if (jumlahDiambil > barang.getStok()) {
                throw new StokTidakCukupException("Stok untuk " + barang.getNama() +
                        " hanya tersisa " + barang.getStok());
            }

            // Jika validasi berhasil, kurangi stok
            barang.setStok(barang.getStok() - jumlahDiambil);
            System.out.println("Pengurangan stok berhasil. Stok " + barang.getNama() +
                    " sekarang: " + barang.getStok());

        } catch (InputMismatchException e) {
            scanner.nextLine(); // Membersihkan buffer
            System.out.println("Error: Input harus berupa angka!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (StokTidakCukupException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}