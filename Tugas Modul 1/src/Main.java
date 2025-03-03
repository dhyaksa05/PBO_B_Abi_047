
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inisialisasi Scanner
        Scanner scanner = new Scanner(System.in);
        // Variabel kontrol loop
        boolean lanjut = true;

        while (lanjut) {
            // Menampilkan pilihan login
            System.out.println("Pilih login:");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.print("Masukkan pilihan: ");

            // Membaca pilihan pengguna
            String pilihan = scanner.nextLine();

            // Menggunakan percabangan if-else untuk memproses pilihan
            if (pilihan.equals("1")) {
                // Login sebagai Admin
                System.out.print("Masukkan username: ");
                String username = scanner.nextLine();
                System.out.print("Masukkan password: ");
                String password = scanner.nextLine();

                // Validasi username dan password Admin
                // Username valid: "Admin" + (3-digit NIM terakhir kalian)
                // Password valid: "Password" + (3-digit NIM terakhir kalian)
                if (username.startsWith("Admin") && password.startsWith("Password")) {
                    // Memeriksa jika 3 karakter terakhir username dan password sama
                    if (username.length() >= 5 && password.length() >= 8) {
                        String usernameDigits = username.substring(5);
                        String passwordDigits = password.substring(8);

                        if (usernameDigits.equals(passwordDigits) && usernameDigits.length() == 3) {
                            System.out.println("Login Admin berhasil!");
                        } else {
                            System.out.println("Login gagal! Username atau password salah.");
                        }
                    } else {
                        System.out.println("Login gagal! Username atau password salah.");
                    }
                } else {
                    System.out.println("Login gagal! Username atau password salah.");
                }
            } else if (pilihan.equals("2")) {
                // Login sebagai Mahasiswa
                System.out.print("Masukkan Nama: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan NIM: ");
                String nim = scanner.nextLine();

                // Validasi nama dan NIM Mahasiswa
                // NIM harus terdiri dari minimal 10 angka
                if (!nama.trim().isEmpty() && nim.matches("\\d{10,}")) {
                    // Login berhasil jika nama tidak kosong dan NIM berupa minimal 10 angka
                    System.out.println("Login Mahasiswa berhasil!");
                    System.out.println("Nama: " + nama);
                    System.out.println("NIM: " + nim);
                } else {
                    System.out.println("Login gagal! Nama atau NIM salah.");
                }
            } else {
                // Pilihan tidak valid
                System.out.println("Pilihan tidak valid.");
            }

            System.out.println("Process finished with exit code 0");

            // Tanyakan apakah ingin melanjutkan
            System.out.print("\nIngin mencoba lagi? (y/n): ");
            String jawaban = scanner.nextLine();
            lanjut = jawaban.equalsIgnoreCase("y");
            System.out.println();
        }

        //scanner.close();
    }
}