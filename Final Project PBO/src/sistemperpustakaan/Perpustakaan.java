package sistemperpustakaan;

// Class Perpustakaan dalam file terpisah
public class Perpustakaan {
    private String nama;
    private String alamat;
    private Buku[] koleksiBuku;
    private int jumlahBuku;
    private final int KAPASITAS = 100; // Konstanta

    // Constructor
    public Perpustakaan(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.koleksiBuku = new Buku[KAPASITAS];
        this.jumlahBuku = 0;
    }

    // Getter dan Setter (Encapsulation)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getJumlahBuku() {
        return jumlahBuku;
    }

    public Buku getBuku(int indeks) {
        if (indeks >= 0 && indeks < jumlahBuku) {
            return koleksiBuku[indeks];
        }
        return null;
    }

    public void tambahBuku(Buku buku) {
        if (jumlahBuku < KAPASITAS) {
            koleksiBuku[jumlahBuku] = buku;
            jumlahBuku++;
            System.out.println("Buku " + buku.getJudul() + " ditambahkan ke perpustakaan.");
        } else {
            System.out.println("Perpustakaan sudah penuh!");
        }
    }

    public void tampilkanDaftarBuku() {
        System.out.println("Daftar Buku di " + nama + ":");
        if (jumlahBuku == 0) {
            System.out.println("Tidak ada buku di perpustakaan.");
            return;
        }

        for (int i = 0; i < jumlahBuku; i++) {
            System.out.println((i+1) + ". " + koleksiBuku[i].getJudul() +
                    " oleh " + koleksiBuku[i].getPenulis() +
                    " (" + (koleksiBuku[i].isKetersediaan() ? "Tersedia" : "Dipinjam") + ")");
        }
    }

    public Buku[] getKoleksiBuku() {
        Buku[] hasil = new Buku[jumlahBuku];
        for (int i = 0; i < jumlahBuku; i++) {
            hasil[i] = koleksiBuku[i];
        }
        return hasil;
    }

    public void tampilkanInfo() {
        System.out.println("Informasi Perpustakaan:");
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("Jumlah Buku: " + jumlahBuku);
    }
}