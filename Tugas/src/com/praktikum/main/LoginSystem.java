// Package com.praktikum.main
package com.praktikum.main;

import java.util.Scanner;

import com.praktikum.HelloWorld;
import com.praktikum.users.User;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean lanjut = true;

        HelloWorld.printHelloWorld();

        // Objek untuk polimorfisme
        User user = null;

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
                    System.out.print("Masukkan username: ");
                    String username = scanner.nextLine();
                    System.out.print("Masukkan password: ");
                    String password = scanner.nextLine();

                    // Create admin object
                    Admin admin = new Admin("Admin User", "000000", username, password);

                    if (admin.login(username, password)) {
                        user = admin; // Polimorfisme
                        user.displayInfo();
                        user.displayAppMenu(); // Panggil menu secara polimorfik
                    } else {
                        System.out.println("Login gagal! Username atau password salah.");
                        System.out.println("Format username: Admin + (3 digit)");
                        System.out.println("Format password: Password + (3 digit yang sama)");
                    }
                    break;

                case "2":
                    // Login sebagai Mahasiswa
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();

                    Mahasiswa mahasiswa = new Mahasiswa(nama, nim);

                    if (mahasiswa.login(nama, nim)) {
                        user = mahasiswa; // Polimorfisme
                        user.displayInfo();
                        user.displayAppMenu(); // Panggil menu secara polimorfik
                    } else {
                        System.out.println("Login gagal! Nama atau NIM salah.");
                        System.out.println("NIM harus terdiri dari minimal 10 angka.");
                    }
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

        scanner.close();
    }
}