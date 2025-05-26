package com.praktikum.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.praktikum.HelloWorld;
import com.praktikum.users.User;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.data.Item;

public class LoginSystem {
    // ArrayList statis untuk users dan items
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Item> reportedItems = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Inisialisasi pengguna default
    static {
        // Tambahkan admin dan mahasiswa default
        userList.add(new Admin("Admin User", "202410370110047", "Admin047", "Password047"));
        userList.add(new Mahasiswa("Abi Danadhyaksa", "202410370110047"));
    }

    // Mengambil daftar pengguna
    public static ArrayList<User> getUserList() {
        return userList;
    }

    // Mengambil daftar item yang dilaporkan
    public static ArrayList<Item> getReportedItems() {
        return reportedItems;
    }

    // Method main sebagai titik masuk aplikasi
    public static void main(String[] args) {
        run();
    }

    // Method utama untuk menjalankan sistem login
    public static void run() {
        boolean lanjut = true;

        HelloWorld.printHelloWorld();

        while (lanjut) {
            System.out.println("\n=== SISTEM LOGIN ===");
            System.out.println("Pilih login sebagai:");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan: ");

            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    // Login sebagai Admin
                    loginAdmin();
                    break;

                case "2":
                    // Login sebagai Mahasiswa
                    loginMahasiswa();
                    break;

                case "0":
                    lanjut = false;
                    System.out.println("Program selesai. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    // Method untuk login admin
    private static void loginAdmin() {
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        User loggedInUser = null;

        // Loop melalui userList untuk menemukan admin yang cocok
        for (User user : userList) {
            if (user instanceof Admin && user.login(username, password)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser != null) {
            loggedInUser.displayInfo();
            loggedInUser.displayAppMenu();
        } else {
            System.out.println("Login gagal! Username atau password salah.");
            System.out.println("Format username: Admin + (3 digit)");
            System.out.println("Format password: Password + (3 digit yang sama)");
        }
    }

    // Method untuk login mahasiswa
    private static void loginMahasiswa() {
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        User loggedInUser = null;

        // Loop melalui userList untuk menemukan mahasiswa yang cocok berdasarkan NIM
        for (User user : userList) {
            if (user instanceof Mahasiswa && user.getNim().equals(nim)) {
                loggedInUser = user;
                break;
            }
        }

        // Jika tidak ada pengguna yang ditemukan tetapi info login valid, buat yang baru
        if (loggedInUser == null && validateMahasiswaInfo(nama, nim)) {
            Mahasiswa newMahasiswa = new Mahasiswa(nama, nim);
            userList.add(newMahasiswa);
            loggedInUser = newMahasiswa;
        }

        if (loggedInUser != null) {
            // Pastikan kita menggunakan nama yang benar dari percobaan login untuk pengguna baru
            if (!loggedInUser.getNama().equals(nama) && loggedInUser instanceof Mahasiswa) {
                // Untuk pengguna yang sudah ada, perbarui nama jika tidak cocok
                ((Mahasiswa) loggedInUser).setNama(nama);
            }
            loggedInUser.displayInfo();
            loggedInUser.displayAppMenu();
        } else {
            System.out.println("Login gagal! Nama atau NIM salah.");
            System.out.println("NIM harus terdiri dari minimal 10 angka.");
        }
    }

    // Method pembantu untuk memvalidasi informasi mahasiswa
    private static boolean validateMahasiswaInfo(String nama, String nim) {
        return !nama.trim().isEmpty() && nim.matches("\\d{10,}");
    }
    // Helper method to get integer input with exception handling
    public static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka! Silakan coba lagi.");
            }
        }
    }

}