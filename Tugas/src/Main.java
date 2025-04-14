import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean lanjut = true;

        // Membuat objek Admin dan Mahasiswa
        Admin admin = new Admin("Admin123", "Password123"); // Ganti 123 dengan 3 digit NIM terakhir Anda
        Mahasiswa mahasiswa = new Mahasiswa("", "");

        while (lanjut) {
            System.out.println("Pilih login:");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.print("Masukkan pilihan: ");

            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                // Login sebagai Admin
                System.out.print("Masukkan username: ");
                String username = scanner.nextLine();
                System.out.print("Masukkan password: ");
                String password = scanner.nextLine();

                if (admin.login(username, password)) {
                    System.out.println("Login Admin berhasil!");
                } else {
                    System.out.println("Login gagal! Username atau password salah.");
                }
            } else if (pilihan.equals("2")) {
                // Login sebagai Mahasiswa
                System.out.print("Masukkan Nama: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan NIM: ");
                String nim = scanner.nextLine();

                if (mahasiswa.login(nama, nim)) {
                    mahasiswa = new Mahasiswa(nama, nim);
                    System.out.println("Login Mahasiswa berhasil!");
                    mahasiswa.displayInfo();
                } else {
                    System.out.println("Login gagal! Nama atau NIM salah.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }

            System.out.print("\nIngin mencoba lagi? (y/n): ");
            String jawaban = scanner.nextLine();
            lanjut = jawaban.equalsIgnoreCase("y");
            System.out.println();
        }

        scanner.close();
    }
}