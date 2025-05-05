// Package com.praktikum.users
package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {
    private Scanner scanner;

    // Constructor using super to initialize name and nim
    public Mahasiswa(String nama, String nim) {
        super(nama, nim);  // Call parent constructor
        this.scanner = new Scanner(System.in);
    }

    // Override login method to check name and nim
    @Override
    public boolean login(String inputNama, String inputNim) {
        // NIM must consist of at least 10 digits
        return !inputNama.trim().isEmpty() && inputNim.matches("\\d{10,}");
    }

    // Override displayInfo method to show login success message
    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil!");
        super.displayInfo();  // Call parent method
        System.out.println("Status: Mahasiswa");
    }

    // Implementasi interface MahasiswaActions
    @Override
    public void reportItem() {
        System.out.println("\n=== LAPORKAN BARANG ===");

        System.out.print("Nama Barang: ");
        String namaBarang = scanner.nextLine();

        System.out.print("Deskripsi Barang: ");
        String deskripsiBarang = scanner.nextLine();

        System.out.print("Lokasi Terakhir/Ditemukan: ");
        String lokasiBarang = scanner.nextLine();

        // Konfirmasi laporan
        System.out.println("\nKonfirmasi Laporan:");
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Deskripsi: " + deskripsiBarang);
        System.out.println("Lokasi: " + lokasiBarang);
        System.out.println("Laporan berhasil disimpan!");
    }

    @Override
    public void viewReportedItems() {
        System.out.println(">> Fitur Lihat Laporan Belum Tersedia <<");
    }

    // Implementasi displayAppMenu untuk Mahasiswa
    @Override
    public void displayAppMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== MENU MAHASISWA ===");
            System.out.println("1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    reportItem();
                    break;
                case "2":
                    viewReportedItems();
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