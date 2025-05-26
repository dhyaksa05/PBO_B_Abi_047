package sistemperpustakaan;

// Class Buku dalam file terpisah
public class Buku {
    private String judul;
    private String penulis;
    private boolean ketersediaan;
    private String kategori;

    // Constructor
    public Buku(String judul, String penulis, String kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.ketersediaan = true;
    }

    // Getter dan Setter (Encapsulation)
    public String getJudul() {
        return judul;
    }


    public String getPenulis() {
        return penulis;
    }


    public boolean isKetersediaan() {
        return ketersediaan;
    }

    public void setKetersediaan(boolean ketersediaan) {
        this.ketersediaan = ketersediaan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void tampilkanInfo() {
        System.out.println("Informasi Buku:");
        System.out.println("Judul: " + judul);
        System.out.println("Penulis: " + penulis);
        System.out.println("Kategori: " + kategori);
        System.out.println("Ketersediaan: " + (ketersediaan ? "Tersedia" : "Tidak Tersedia"));
    }
}