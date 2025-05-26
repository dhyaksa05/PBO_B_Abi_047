package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;

import java.util.ArrayList;
import java.util.Scanner;


public class Mahasiswa extends User implements MahasiswaActions {
    private Scanner scanner;

    // Constructor using super to initialize name and nim
    public Mahasiswa(String nama, String nim) {
        super(nama, nim);  // Call parent constructor
        this.scanner = new Scanner(System.in);
    }

    // Override login method untuk mengecek NIM dan Nama
    @Override
    public boolean login(String inputNama, String inputNim) {
        // NIM must consist of at least 10 digits
        return !inputNama.trim().isEmpty() && inputNim.matches("\\d{10,}");
    }

    // Override displayInfo
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

        try {
            System.out.print("Nama Barang: ");
            String namaBarang = scanner.nextLine();
            if (namaBarang.trim().isEmpty()) {
                throw new IllegalArgumentException("Nama barang tidak boleh kosong!");
            }

            System.out.print("Deskripsi Barang: ");
            String deskripsiBarang = scanner.nextLine();

            System.out.print("Lokasi Terakhir/Ditemukan: ");
            String lokasiBarang = scanner.nextLine();
            if (lokasiBarang.trim().isEmpty()) {
                throw new IllegalArgumentException("Lokasi barang tidak boleh kosong!");
            }

            // Allow user to select status or use default
            System.out.println("\nPilih Metode Pelaporan:");
            System.out.println("1. Pilih Status Manual");
            System.out.println("2. Gunakan Status Default (Reported)");
            System.out.print("Pilih metode (1/2): ");
            String methodChoice = scanner.nextLine();

            Item newItem;

            if (methodChoice.equals("1")) {
                // Manual status selection
                System.out.println("\nPilih Status Barang:");
                System.out.println("1. Reported (Barang Hilang)");
                System.out.println("2. Found (Barang Ditemukan)");
                System.out.print("Pilih status (1/2): ");
                String statusChoice = scanner.nextLine();

                String itemStatus;
                if (statusChoice.equals("1")) {
                    itemStatus = "Reported";
                } else if (statusChoice.equals("2")) {
                    itemStatus = "Found";
                } else {
                    throw new IllegalArgumentException("Pilihan status tidak valid!");
                }

                // Create Item with 4 parameters (manual status)
                newItem = new Item(namaBarang, deskripsiBarang, lokasiBarang, itemStatus);
                System.out.println("Barang dilaporkan dengan status: " + itemStatus);

            } else if (methodChoice.equals("2")) {
                // Use default status (3-parameter constructor)
                newItem = new Item(namaBarang, deskripsiBarang, lokasiBarang);
                System.out.println("Barang dilaporkan dengan status default: Reported");

            } else {
                throw new IllegalArgumentException("Pilihan metode tidak valid!");
            }

            // Add to global list
            ArrayList<Item> reportedItems = LoginSystem.getReportedItems();
            reportedItems.add(newItem);

            // Konfirmasi laporan
            System.out.println("\n=== KONFIRMASI LAPORAN ===");
            System.out.println("Nama Barang: " + newItem.getItemName());
            System.out.println("Deskripsi: " + newItem.getDescription());
            System.out.println("Lokasi: " + newItem.getLocation());
            System.out.println("Status: " + newItem.getStatus());
            System.out.println("Laporan berhasil disimpan!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Silakan coba lagi.");
        }
    }

    @Override
    public void viewReportedItems() {
        System.out.println("\n=== DAFTAR BARANG ===");

        ArrayList<Item> reportedItems = LoginSystem.getReportedItems();

        if (reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        System.out.println("\nFilter Berdasarkan Status:");
        System.out.println("1. Semua Barang");
        System.out.println("2. Barang Hilang (Reported)");
        System.out.println("3. Barang Ditemukan (Found)");
        System.out.println("4. Barang Telah Diambil (Claimed)");
        System.out.print("Pilih filter (1-4): ");

        String filter = scanner.nextLine();
        String statusFilter = null;

        switch (filter) {
            case "2":
                statusFilter = "Reported";
                System.out.println("\n=== DAFTAR BARANG HILANG (REPORTED) ===");
                break;
            case "3":
                statusFilter = "Found";
                System.out.println("\n=== DAFTAR BARANG DITEMUKAN (FOUND) ===");
                break;
            case "4":
                statusFilter = "Claimed";
                System.out.println("\n=== DAFTAR BARANG TELAH DIAMBIL (CLAIMED) ===");
                break;
            default:
                System.out.println("\n=== DAFTAR SEMUA BARANG ===");
                break;
        }

        // Use enhanced for-loop to display items with filtered status
        int count = 1;
        for (Item item : reportedItems) {
            if (statusFilter == null || statusFilter.equals(item.getStatus())) {
                System.out.println(count + ". Nama: " + item.getItemName());
                System.out.println("   Deskripsi: " + item.getDescription());
                System.out.println("   Lokasi: " + item.getLocation());
                System.out.println("   Status: " + item.getStatus());
                System.out.println("------------------------");
                count++;
            }
        }

        if (count == 1) {
            if (statusFilter != null) {
                System.out.println("Tidak ada barang dengan status '" + statusFilter + "'.");
            } else {
                System.out.println("Tidak ada barang yang terdaftar.");
            }
        }
    }

    // Method untuk melaporkan barang dengan cepat (menggunakan konstruktor 3 parameter)
    public void quickReportItem() {
        System.out.println("\n=== LAPORAN CEPAT BARANG ===");
        System.out.println("(Status otomatis: Reported)");

        try {
            System.out.print("Nama Barang: ");
            String namaBarang = scanner.nextLine();
            if (namaBarang.trim().isEmpty()) {
                throw new IllegalArgumentException("Nama barang tidak boleh kosong!");
            }

            System.out.print("Deskripsi Barang: ");
            String deskripsiBarang = scanner.nextLine();

            System.out.print("Lokasi Terakhir: ");
            String lokasiBarang = scanner.nextLine();
            if (lokasiBarang.trim().isEmpty()) {
                throw new IllegalArgumentException("Lokasi barang tidak boleh kosong!");
            }

            // Menggunakan konstruktor dengan 3 parameter (status default "Reported")
            Item newItem = new Item(namaBarang, deskripsiBarang, lokasiBarang);

            // Add to global list
            ArrayList<Item> reportedItems = LoginSystem.getReportedItems();
            reportedItems.add(newItem);

            // Konfirmasi laporan
            System.out.println("\n=== KONFIRMASI LAPORAN CEPAT ===");
            System.out.println("Nama Barang: " + newItem.getItemName());
            System.out.println("Deskripsi: " + newItem.getDescription());
            System.out.println("Lokasi: " + newItem.getLocation());
            System.out.println("Status: " + newItem.getStatus() + " (Default)");
            System.out.println("Laporan cepat berhasil disimpan!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Silakan coba lagi.");
        }
    }

    // Implementasi displayAppMenu untuk Mahasiswa
    @Override
    public void displayAppMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== MENU MAHASISWA ===");
            System.out.println("1. Laporkan Barang (Manual)");
            System.out.println("2. Laporan Cepat Barang (Status Default)");
            System.out.println("3. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    reportItem();
                    break;
                case "2":
                    quickReportItem();
                    break;
                case "3":
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