// Package com.praktikum.users
package com.praktikum.users;

import java.util.Scanner;

// Abstract class User
public abstract class User {
    // Encapsulation - private attributes
    private String nama;
    private String nim;

    // Constructor
    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    // Get last 3 digits of NIM
    public String getLast3DigitsNim() {
        if (nim.length() >= 3) {
            return nim.substring(nim.length() - 3);
        } else {
            return nim;
        }
    }

    // Abstract methods
    public abstract boolean login(String inputData1, String inputData2);

    // Abstract method untuk menampilkan menu spesifik peran
    public abstract void displayAppMenu();

    // Method to display user information
    public void displayInfo() {
        System.out.println("Informasi Pengguna:");
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + getLast3DigitsNim());  // Hanya menampilkan 3 digit terakhir
    }
}