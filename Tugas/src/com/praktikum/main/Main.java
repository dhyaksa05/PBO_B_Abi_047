import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean lanjut = true;


        Admin admin = new Admin("com.praktikum.users.Admin com.praktikum.users.User", "000000", "placeholder", "placeholder"); // objek admin default

        User user = null; //polimorfisme

        while (lanjut) {
            System.out.println("=== SISTEM LOGIN ===");
            System.out.println("Pilih login sebagai:");
            System.out.println("1. com.praktikum.users.Admin");
            System.out.println("2. com.praktikum.users.Mahasiswa");
            System.out.print("Masukkan pilihan: ");

            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                // Login sebagai com.praktikum.users.Admin
                System.out.print("Masukkan username: ");
                String username = scanner.nextLine();
                System.out.print("Masukkan password: ");
                String password = scanner.nextLine();

                // Re-initialize admin with proper values
                admin = new Admin("com.praktikum.users.Admin com.praktikum.users.User", "000000", username, password);

                if (admin.login(username, password)) {
                    user = admin;
                    user.displayInfo();
                } else {
                    System.out.println("Login gagal! Username atau password salah.");
                    System.out.println("Format username: com.praktikum.users.Admin + (3 digit)");
                    System.out.println("Format password: Password + (3 digit yang sama)");
                }
            } else if (pilihan.equals("2")) {
                // Login sebagai com.praktikum.users.Mahasiswa
                System.out.print("Masukkan Nama: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan NIM: ");
                String nim = scanner.nextLine();

                Mahasiswa mahasiswa = new Mahasiswa(nama, nim);

                if (mahasiswa.login(nama, nim)) {
                    user = mahasiswa;
                    user.displayInfo();
                } else {
                    System.out.println("Login gagal! Nama atau NIM salah.");
                    System.out.println("NIM harus terdiri dari minimal 10 angka.");
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
        System.out.println("Program selesai. Terima kasih!");
    }
}