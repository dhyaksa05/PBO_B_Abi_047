// File: Main.java
import java.util.Scanner; // Mengimpor kelas Scanner untuk input dari pengguna

/**
 * Kelas Main sebagai kelas utama program
 */
public class Main {
    /**
     * Method main sebagai entry point program
     * @param args parameter standar command line
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Membuat objek Scanner untuk membaca input pengguna

        // Input untuk rekening1
        System.out.println("=== Input Data Rekening 1 ==="); // Menampilkan judul untuk input rekening pertama
        String noRek1 = getValidNomorRekening(input); // Meminta dan memvalidasi nomor rekening pertama

        System.out.print("Masukkan nama pemilik: "); // Prompt untuk nama pemilik
        String nama1 = input.nextLine(); // Membaca input nama pemilik

        System.out.print("Masukkan saldo awal (angka positif): "); // Prompt untuk saldo awal
        double saldo1 = input.nextDouble(); // Membaca input saldo awal
        input.nextLine(); // Membersihkan buffer setelah membaca double

        // Input untuk rekening2
        System.out.println("\n=== Input Data Rekening 2 ==="); // Menampilkan judul untuk input rekening kedua
        String noRek2 = getValidNomorRekening(input); // Meminta dan memvalidasi nomor rekening kedua

        System.out.print("Masukkan nama pemilik: "); // Prompt untuk nama pemilik
        String nama2 = input.nextLine(); // Membaca input nama pemilik

        System.out.print("Masukkan saldo awal (angka positif): "); // Prompt untuk saldo awal
        double saldo2 = input.nextDouble(); // Membaca input saldo awal

        // Membuat dua objek RekeningBank
        RekeningBank rekening1 = new RekeningBank(noRek1, nama1, saldo1); // Membuat objek rekening pertama
        RekeningBank rekening2 = new RekeningBank(noRek2, nama2, saldo2); // Membuat objek rekening kedua

        System.out.println("\n=== Informasi Rekening ==="); // Menampilkan judul untuk informasi rekening
        // Memanggil metode pada kedua objek
        rekening1.tampilkanInfo(); // Menampilkan informasi rekening pertama
        System.out.println(); // Membuat baris kosong
        rekening2.tampilkanInfo(); // Menampilkan informasi rekening kedua
        System.out.println(); // Membuat baris kosong

        // Melakukan transaksi setor uang
        rekening1.setorUang(200000.0); // Melakukan setoran pada rekening pertama
        rekening2.setorUang(500000.0); // Melakukan setoran pada rekening kedua
        System.out.println(); // Membuat baris kosong

        // Melakukan transaksi tarik uang
        rekening1.tarikUang(800000.0); // Melakukan penarikan pada rekening pertama
        rekening2.tarikUang(300000.0); // Melakukan penarikan pada rekening kedua
        System.out.println(); // Membuat baris kosong

        // Menampilkan informasi setelah transaksi
        rekening1.tampilkanInfo(); // Menampilkan informasi rekening pertama setelah transaksi
        System.out.println(); // Membuat baris kosong
        rekening2.tampilkanInfo(); // Menampilkan informasi rekening kedua setelah transaksi

        input.close(); // Menutup Scanner untuk mencegah resource leak
    }

    /**
     * Method untuk memvalidasi nomor rekening
     * @param input objek Scanner untuk membaca input
     * @return nomor rekening yang valid
     */
    private static String getValidNomorRekening(Scanner input) {
        String nomorRekening; // Variabel untuk menyimpan nomor rekening
        boolean isValid = false; // Flag untuk menandai validitas input

        do {
            System.out.print("Masukkan nomor rekening (minimal 10 angka): "); // Prompt untuk nomor rekening
            nomorRekening = input.nextLine(); // Membaca input nomor rekening

            // Validasi: minimal 10 karakter dan hanya berisi angka
            if (nomorRekening.length() < 10) { // Memeriksa panjang nomor rekening
                System.out.println("Error: Nomor rekening harus memiliki minimal 10 angka."); // Pesan error untuk panjang
            } else if (!nomorRekening.matches("\\d+")) { // Memeriksa apakah hanya berisi angka
                System.out.println("Error: Nomor rekening harus hanya berisi angka."); // Pesan error untuk format
            } else {
                isValid = true; // Menandai bahwa input valid
            }
        } while (!isValid); // Mengulang jika input tidak valid

        return nomorRekening; // Mengembalikan nomor rekening yang valid
    }
}