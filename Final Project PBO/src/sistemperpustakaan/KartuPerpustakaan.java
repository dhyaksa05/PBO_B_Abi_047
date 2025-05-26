package sistemperpustakaan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Class KartuPerpustakaan dalam file terpisah
public class KartuPerpustakaan {
    private String idMahasiswa;
    private LocalDate tanggalPembuatan;
    private LocalDate tanggalKadaluarsa;
    private boolean aktif;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Constructor dengan tanggal kadaluarsa otomatis 7 hari setelah pembuatan
    public KartuPerpustakaan(String idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
        this.tanggalPembuatan = LocalDate.now();
        this.tanggalKadaluarsa = tanggalPembuatan.plusDays(7); // Kadaluarsa setelah 7 hari
        this.aktif = true;
    }

    // Getter dan Setter (Encapsulation)

    public String getTanggalPembuatanFormatted() {
        return tanggalPembuatan.format(formatter);
    }


    public String getTanggalKadaluarsaFormatted() {
        return tanggalKadaluarsa.format(formatter);
    }




    public boolean isKadaluarsa() {
        // Periksa tanggal kadaluarsa dengan tanggal saat ini
        return LocalDate.now().isAfter(tanggalKadaluarsa) || !aktif;
    }

    public void perpanjangMasa(int hari) {
        this.tanggalKadaluarsa = tanggalKadaluarsa.plusDays(hari);
        System.out.println("Masa berlaku kartu perpustakaan diperpanjang " + hari + " hari.");
        System.out.println("Tanggal kadaluarsa baru: " + getTanggalKadaluarsaFormatted());
    }

    public void tampilkanInfo() {
        System.out.println("Informasi Kartu Perpustakaan:");
        System.out.println("ID Mahasiswa: " + idMahasiswa);
        System.out.println("Tanggal Pembuatan: " + getTanggalPembuatanFormatted());
        System.out.println("Tanggal Kadaluarsa: " + getTanggalKadaluarsaFormatted());
        System.out.println("Status: " + (isKadaluarsa() ? "Kadaluarsa" : "Aktif"));

        // Informasi tambahan tentang sisa masa berlaku
        if (!isKadaluarsa()) {
            long sisaHari = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), tanggalKadaluarsa);
            System.out.println("Sisa masa berlaku: " + sisaHari + " hari");
        }
    }
}