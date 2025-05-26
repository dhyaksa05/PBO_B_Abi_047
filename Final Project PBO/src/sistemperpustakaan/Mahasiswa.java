package sistemperpustakaan;

// Class Mahasiswa dalam file terpisah
public class Mahasiswa extends Pengguna implements Pencarian {
    private String jurusan;
    private KartuPerpustakaan kartu;

    // Constructor
    public Mahasiswa(String nama, String id, String jurusan) {
        super(nama, id); // Super keyword
        this.jurusan = jurusan;
    }

    // Method overriding
    @Override
    public void tampilkanInfo() {
        System.out.println("Informasi Mahasiswa:");
        System.out.println("Nama: " + nama);
        System.out.println("ID: " + id);
        System.out.println("Jurusan: " + jurusan);
        if (kartu != null) {
            System.out.println("Status Kartu: " + (kartu.isKadaluarsa() ? "Kadaluarsa" : "Aktif"));
            if (!kartu.isKadaluarsa()) {
                System.out.println("Tanggal Kadaluarsa Kartu: " + kartu.getTanggalKadaluarsaFormatted());
            }
        } else {
            System.out.println("Belum memiliki kartu perpustakaan");
        }
    }

    public void belajar() {
        System.out.println(nama + " sedang belajar di perpustakaan.");
    }

    public void pinjamBuku(Buku buku) {
        if (buku.isKetersediaan() && kartu != null && !kartu.isKadaluarsa()) {
            System.out.println(nama + " meminjam buku " + buku.getJudul());
            buku.setKetersediaan(false);
        } else if (kartu == null) {
            System.out.println("Mahasiswa belum memiliki kartu perpustakaan.");
        } else if (kartu.isKadaluarsa()) {
            System.out.println("Kartu perpustakaan sudah kadaluarsa.");
            System.out.println("Silakan perpanjang kartu perpustakaan terlebih dahulu.");
        } else {
            System.out.println("Buku tidak tersedia untuk dipinjam.");
        }
    }

    public void kembalikanBuku(Buku buku) {
        System.out.println(nama + " mengembalikan buku " + buku.getJudul());
        buku.setKetersediaan(true);
    }

    public void setKartu(KartuPerpustakaan kartu) {
        this.kartu = kartu;
    }

    public KartuPerpustakaan getKartu() {
        return kartu;
    }

    // Implementasi interface
    @Override
    public void cari(String kata) {
        System.out.println(nama + " mencari informasi tentang '" + kata + "'");
    }
}