// Package com.praktikum.users
package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

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

    // Method untuk admin menambahkan barang secara manual
    public void addItemManually() {
        System.out.println("\n=== TAMBAH BARANG MANUAL ===");

        try {
            System.out.print("Nama Barang: ");
            String namaBarang = scanner.nextLine();
            if (namaBarang.trim().isEmpty()) {
                throw new IllegalArgumentException("Nama barang tidak boleh kosong!");
            }

            System.out.print("Deskripsi Barang: ");
            String deskripsiBarang = scanner.nextLine();

            System.out.print("Lokasi: ");
            String lokasiBarang = scanner.nextLine();
            if (lokasiBarang.trim().isEmpty()) {
                throw new IllegalArgumentException("Lokasi barang tidak boleh kosong!");
            }

            // Allow admin to choose constructor
            System.out.println("\nPilih Metode Penambahan:");
            System.out.println("1. Tentukan Status Manual");
            System.out.println("2. Gunakan Status Default (Reported)");
            int methodChoice = LoginSystem.getIntInput("Pilih metode (1/2): ");

            Item newItem;

            if (methodChoice == 1) {
                // Manual status selection
                System.out.println("\nPilih Status Barang:");
                System.out.println("1. Reported (Barang Hilang)");
                System.out.println("2. Found (Barang Ditemukan)");
                System.out.println("3. Claimed (Barang Telah Diambil)");
                int statusChoice = LoginSystem.getIntInput("Pilih status (1-3): ");

                String itemStatus;
                switch (statusChoice) {
                    case 1:
                        itemStatus = "Reported";
                        break;
                    case 2:
                        itemStatus = "Found";
                        break;
                    case 3:
                        itemStatus = "Claimed";
                        break;
                    default:
                        throw new IllegalArgumentException("Pilihan status tidak valid!");
                }

                // Create Item with 4 parameters (manual status)
                newItem = new Item(namaBarang, deskripsiBarang, lokasiBarang, itemStatus);
                System.out.println("Barang ditambahkan dengan status: " + itemStatus);

            } else if (methodChoice == 2) {
                // Use default status (3-parameter constructor)
                newItem = new Item(namaBarang, deskripsiBarang, lokasiBarang);
                System.out.println("Barang ditambahkan dengan status default: Reported");

            } else {
                throw new IllegalArgumentException("Pilihan metode tidak valid!");
            }

            // Add to global list
            ArrayList<Item> reportedItems = LoginSystem.getReportedItems();
            reportedItems.add(newItem);

            // Konfirmasi penambahan
            System.out.println("\n=== KONFIRMASI PENAMBAHAN ===");
            System.out.println("Nama Barang: " + newItem.getItemName());
            System.out.println("Deskripsi: " + newItem.getDescription());
            System.out.println("Lokasi: " + newItem.getLocation());
            System.out.println("Status: " + newItem.getStatus());
            System.out.println("Barang berhasil ditambahkan ke sistem!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Silakan coba lagi.");
        }
    }

    // Implementasi interface AdminActions
    @Override
    public void manageItems() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== KELOLA BARANG ===");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil (Claimed)");
            System.out.println("3. Tambah Barang Manual");
            System.out.println("0. Kembali");
            int choice = LoginSystem.getIntInput("Pilih submenu: ");

            switch (choice) {
                case 1:
                    viewAllItems();
                    break;
                case 2:
                    markItemAsClaimed();
                    break;
                case 3:
                    addItemManually();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // Method to view all reported items
    private void viewAllItems() {
        System.out.println("\n=== DAFTAR SEMUA BARANG ===");

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
        int filter = LoginSystem.getIntInput("Pilih filter (1-4): ");

        String statusFilter = null;

        switch (filter) {
            case 2:
                statusFilter = "Reported";
                System.out.println("\n=== DAFTAR BARANG HILANG (REPORTED) ===");
                break;
            case 3:
                statusFilter = "Found";
                System.out.println("\n=== DAFTAR BARANG DITEMUKAN (FOUND) ===");
                break;
            case 4:
                statusFilter = "Claimed";
                System.out.println("\n=== DAFTAR BARANG TELAH DIAMBIL (CLAIMED) ===");
                break;
            default:
                System.out.println("\n=== DAFTAR SEMUA BARANG ===");
                break;
        }

        int index = 0;
        boolean foundItems = false;

        for (Item item : reportedItems) {
            if (statusFilter == null || statusFilter.equals(item.getStatus())) {
                foundItems = true;
                System.out.println("Index [" + index + "]");
                System.out.println("Nama: " + item.getItemName());
                System.out.println("Deskripsi: " + item.getDescription());
                System.out.println("Lokasi: " + item.getLocation());
                System.out.println("Status: " + item.getStatus());
                System.out.println("------------------------");
            }
            index++;
        }

        if (!foundItems) {
            if (statusFilter != null) {
                System.out.println("Tidak ada barang dengan status '" + statusFilter + "'.");
            } else {
                System.out.println("Tidak ada barang yang terdaftar.");
            }
        }
    }

    // Method to mark item as claimed
    private void markItemAsClaimed() {
        System.out.println("\n=== TANDAI BARANG TELAH DIAMBIL ===");

        ArrayList<Item> reportedItems = LoginSystem.getReportedItems();

        if (reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        // Display only "Reported" or "Found" items with their indices
        System.out.println("\nDaftar Barang yang Dapat Diubah Status:");
        boolean hasEligibleItems = false;
        int index = 0;

        for (Item item : reportedItems) {
            if ("Reported".equals(item.getStatus()) || "Found".equals(item.getStatus())) {
                System.out.println("Index [" + index + "] - " + item.getItemName() + " (Status: " + item.getStatus() + ")");
                hasEligibleItems = true;
            }
            index++;
        }

        if (!hasEligibleItems) {
            System.out.println("Tidak ada barang yang dapat diubah statusnya.");
            return;
        }

        // Get index input using the helper method
        int selectedIndex = LoginSystem.getIntInput("\nMasukkan indeks barang yang ingin ditandai sebagai 'Claimed': ");

        // Validate index
        if (selectedIndex >= 0 && selectedIndex < reportedItems.size()) {
            Item selectedItem = reportedItems.get(selectedIndex);

            if ("Reported".equals(selectedItem.getStatus()) || "Found".equals(selectedItem.getStatus())) {
                selectedItem.setStatus("Claimed");
                System.out.println("Berhasil! Status barang \"" + selectedItem.getItemName() +
                        "\" telah diubah menjadi \"Claimed\".");
            } else {
                System.out.println("Barang dengan indeks " + selectedIndex +
                        " tidak dapat diubah statusnya.");
            }
        } else {
            System.out.println("Error: Indeks tidak valid!");
        }
    }

    @Override
    public void manageUsers() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== KELOLA MAHASISWA ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Lihat Daftar Mahasiswa");
            System.out.println("0. Kembali");
            int choice = LoginSystem.getIntInput("Pilih submenu: ");

            switch (choice) {
                case 1:
                    addMahasiswa();
                    break;
                case 2:
                    deleteMahasiswa();
                    break;
                case 3:
                    viewAllMahasiswa();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // Method to add a new student
    private void addMahasiswa() {
        System.out.println("\n=== TAMBAH MAHASISWA ===");

        try {
            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();
            if (nama.trim().isEmpty()) {
                throw new IllegalArgumentException("Nama tidak boleh kosong!");
            }

            System.out.print("Masukkan NIM: ");
            String nim = scanner.nextLine();
            if (!nim.matches("\\d{10,}")) {
                throw new IllegalArgumentException("NIM harus terdiri dari minimal 10 angka!");
            }

            // Check if student with same NIM already exists
            ArrayList<User> userList = LoginSystem.getUserList();
            for (User user : userList) {
                if (user instanceof Mahasiswa && user.getNim().equals(nim)) {
                    throw new IllegalArgumentException("Mahasiswa dengan NIM tersebut sudah ada!");
                }
            }

            // Create and add new student
            Mahasiswa newMahasiswa = new Mahasiswa(nama, nim);
            userList.add(newMahasiswa);

            System.out.println("Mahasiswa berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to delete a student
    private void deleteMahasiswa() {
        System.out.println("\n=== HAPUS MAHASISWA ===");

        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
        String nim = scanner.nextLine();

        ArrayList<User> userList = LoginSystem.getUserList();
        Iterator<User> iterator = userList.iterator();

        boolean found = false;
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user instanceof Mahasiswa && user.getNim().equals(nim)) {
                iterator.remove();
                found = true;
                System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus!");
                break;
            }
        }

        if (!found) {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    // Method to view all students
    private void viewAllMahasiswa() {
        System.out.println("\n=== DAFTAR MAHASISWA ===");

        ArrayList<User> userList = LoginSystem.getUserList();
        boolean foundStudents = false;

        for (User user : userList) {
            if (user instanceof Mahasiswa) {
                foundStudents = true;
                System.out.println("Nama: " + user.getNama());
                System.out.println("NIM: " + user.getNim());
                System.out.println("------------------------");
            }
        }

        if (!foundStudents) {
            System.out.println("Tidak ada mahasiswa terdaftar.");
        }
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
            int choice = LoginSystem.getIntInput("Pilih menu: ");

            switch (choice) {
                case 1:
                    manageItems();
                    break;
                case 2:
                    manageUsers();
                    break;
                case 0:
                    isRunning = false;
                    System.out.println("Logout berhasil!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}