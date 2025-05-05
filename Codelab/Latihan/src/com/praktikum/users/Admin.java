// Package com.praktikum.users
package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import java.util.Scanner;

// Admin class implementing AdminActions interface
public class Admin extends User implements AdminActions {
    // Additional attributes
    private String username;
    private String password;
    private Scanner scanner;

    // Constructor using super to initialize name and nim
    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);  // Memanggil constructor dari kelas induk
        this.username = username;
        this.password = password;
        this.scanner = new Scanner(System.in);
    }

    // Getter dam Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Override login method to check username and password
    @Override
    public boolean login(String inputUsername, String inputPassword) {
        // Username valid: "Admin" + (3-digit NIM terakhir)
        // Password valid: "Password" + (3-digit NIM terakhir)
        if (inputUsername.startsWith("Admin") && inputPassword.startsWith("Password")) {
            if (inputUsername.length() >= 8 && inputPassword.length() >= 11) {
                String usernameDigits = inputUsername.substring(5);
                String passwordDigits = inputPassword.substring(8);

                // Verify the digits match and are 3 digits
                if (usernameDigits.equals(passwordDigits) && usernameDigits.matches("\\d{3}")) {
                    // Update the NIM based on login input
                    String baseNim = "12345";  // Base NIM prefix
                    setNim(baseNim + usernameDigits);
                    return true;
                }
            }
        }
        return false;
    }

    // Override displayInfo method to show login success message
    @Override
    public void displayInfo() {
        System.out.println("Login Admin berhasil!");
        super.displayInfo();  // Memanggil method displayInfo dari kelas induk
        System.out.println("Status: Admin");
    }

    // Implementasi interface AdminActions
    @Override
    public void manageItems() {
        System.out.println(">> Fitur Kelola Barang Belum Tersedia <<");
    }

    @Override
    public void manageUsers() {
        System.out.println(">> Fitur Kelola Mahasiswa Belum Tersedia <<");
    }

    // Implementasi displayAppMenu untuk Admin
    @Override
    public void displayAppMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageItems();
                    break;
                case "2":
                    manageUsers();
                    break;
                case "0":
                    isRunning = false;
                    System.out.println("Logout berhasil!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}