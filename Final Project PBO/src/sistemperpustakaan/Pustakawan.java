package sistemperpustakaan;

// Class Pustakawan dalam file terpisah
public class Pustakawan extends Pengguna implements Pencarian {
    private String jabatan;

    // Constructor
    public Pustakawan(String nama, String id, String jabatan) {
        super(nama, id);
        this.jabatan = jabatan;
    }

    // Method overriding
    @Override
    public void tampilkanInfo() {
        System.out.println("Informasi Pustakawan:");
        System.out.println("Nama: " + nama);
        System.out.println("ID: " + id);
        System.out.println("Jabatan: " + jabatan);
    }

    public void bantuPengguna(Pengguna pengguna) {
        System.out.println(nama + " membantu " + pengguna.getNama());
    }

    public void kelolaBuku(Buku buku, String aksi) {
        System.out.println(nama + " melakukan " + aksi + " pada buku " + buku.getJudul());
    }

    public KartuPerpustakaan buatKartu(Mahasiswa mahasiswa) {
        KartuPerpustakaan kartu = new KartuPerpustakaan(mahasiswa.getId());
        mahasiswa.setKartu(kartu);
        System.out.println(nama + " membuat kartu perpustakaan untuk " + mahasiswa.getNama());
        System.out.println("Kartu akan kadaluarsa pada: " + kartu.getTanggalKadaluarsaFormatted());
        return kartu;
    }

    public void perpanjangKartu(Mahasiswa mahasiswa, int hari) {
        if (mahasiswa.getKartu() != null) {
            mahasiswa.getKartu().perpanjangMasa(hari);
            System.out.println(nama + " memperpanjang kartu perpustakaan untuk " + mahasiswa.getNama());
        } else {
            System.out.println("Mahasiswa belum memiliki kartu perpustakaan.");
        }
    }

    // Method overloading
    public void kelolaBuku(Buku buku) {
        System.out.println(nama + " memeriksa buku " + buku.getJudul());
    }

    public void kelolaBuku(Buku[] daftarBuku) {
        System.out.println(nama + " mengelola " + daftarBuku.length + " buku");
        for (Buku buku : daftarBuku) {
            System.out.println("- " + buku.getJudul());
        }
    }

    // Implementasi interface
    @Override
    public void  (String kata) {
        System.out.println(nama + " mencari katalog buku dengan kata kunci '" + kata + "'");
    }
}