// Mengimpor kelas Scanner untuk membaca input dari pengguna
import java.util.Scanner;
// Mengimpor kelas LocalDate untuk operasi tanggal
import java.time.LocalDate;

// Mendefinisikan kelas utama bernama DataDiri
public class Main{
    // Metode main, entry point program
    public static void main(String[] args) {
        // Inisialisasi Scanner untuk membaca input dari System.in (keyboard)
        Scanner scanner = new Scanner(System.in);

        // Menampilkan prompt untuk input nama
        System.out.print("Masukkan nama: ");
        // Membaca input nama dari pengguna sebagai string
        String nama = scanner.nextLine();

        // Menampilkan prompt untuk input jenis kelamin
        System.out.print("Masukkan jenis kelamin (P/L): ");
        // Membaca input jenis kelamin dan mengubahnya ke huruf kapital
        String jenisKelamin = scanner.nextLine().toUpperCase();

        // Mendeklarasikan variabel untuk menyimpan jenis kelamin lengkap
        String jenisKelaminLengkap;
        // Memeriksa apakah jenis kelamin adalah L (Laki-laki)
        if (jenisKelamin.equals("L")) {
            // Menetapkan jenis kelamin lengkap ke "Laki-laki"
            jenisKelaminLengkap = "Laki-laki";
            // Memeriksa apakah jenis kelamin adalah P (Perempuan)
        } else if (jenisKelamin.equals("P")) {
            // Menetapkan jenis kelamin lengkap ke "Perempuan"
            jenisKelaminLengkap = "Perempuan";
            // Menangani kasus di mana input tidak valid
        } else {
            // Menetapkan jenis kelamin lengkap ke "Tidak valid" jika input bukan P atau L
            jenisKelaminLengkap = "Tidak valid";
            // Menampilkan pesan kesalahan untuk input yang tidak valid
            System.out.println("Input jenis kelamin tidak valid. Program akan menggunakan 'Tidak valid'.");
        }

        // Menampilkan prompt untuk input tahun lahir
        System.out.print("Masukkan tahun lahir: ");
        // Mendeklarasikan variabel untuk menyimpan tahun lahir
        int tahunLahir;

        // Mencoba blok kode untuk mengkonversi input string ke integer
        try {
            // Mencoba mengkonversi input ke integer untuk tahun lahir
            tahunLahir = Integer.parseInt(scanner.nextLine());
            // Menangkap exception jika input bukan angka
        } catch (NumberFormatException e) {
            // Menggunakan tahun sekarang sebagai default jika input tidak valid
            tahunLahir = LocalDate.now().getYear();
            // Menampilkan pesan kesalahan untuk input yang tidak valid
            System.out.println("Input tahun lahir tidak valid. Program akan menggunakan tahun sekarang.");
        }

        // Mendapatkan tahun sekarang dari sistem
        int tahunSekarang = LocalDate.now().getYear();
        // Menghitung umur dengan mengurangkan tahun lahir dari tahun sekarang
        int umur = tahunSekarang - tahunLahir;

        // Menutup Scanner untuk mencegah kebocoran sumber daya
        scanner.close();

        // Menampilkan header untuk output data diri
        System.out.println("\nData Diri:");
        // Menampilkan nama pengguna dengan format yang sesuai
        System.out.println("Nama       : " + nama);
        // Menampilkan jenis kelamin pengguna dengan format yang sesuai
        System.out.println("Jenis Kelamin: " + jenisKelaminLengkap);
        // Menampilkan umur pengguna dengan format yang sesuai
        System.out.println("Umur       : " + umur + " tahun");
    }
}