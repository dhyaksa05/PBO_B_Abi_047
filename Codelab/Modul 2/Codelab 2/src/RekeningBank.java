// File: RekeningBank.java
/**
 * Kelas RekeningBank untuk menyimpan informasi dan operasi rekening bank
 */
public class RekeningBank {
    // Atribut kelas RekeningBank
    private String nomorRekening; // Menyimpan nomor rekening
    private String namaPemilik;   // Menyimpan nama pemilik rekening
    private double saldo;         // Menyimpan saldo rekening

    /**
     * Constructor untuk inisialisasi objek RekeningBank
     * @param nomorRekening nomor rekening nasabah
     * @param namaPemilik nama pemilik rekening
     * @param saldo saldo awal rekening
     */
    public RekeningBank(String nomorRekening, String namaPemilik, double saldo) {
        this.nomorRekening = nomorRekening; // Menginisialisasi nomor rekening
        this.namaPemilik = namaPemilik;     // Menginisialisasi nama pemilik
        this.saldo = saldo;                 // Menginisialisasi saldo awal
    }

    /**
     * Method untuk menampilkan informasi rekening
     */
    public void tampilkanInfo() {
        System.out.println("Nomor Rekening: " + nomorRekening); // Menampilkan nomor rekening
        System.out.println("Nama Pemilik: " + namaPemilik);     // Menampilkan nama pemilik
        System.out.println("Saldo: Rp" + saldo);                // Menampilkan saldo rekening
    }

    /**
     * Method untuk menambahkan jumlah ke saldo (transaksi setoran)
     * @param jumlah jumlah uang yang disetor
     */
    public void setorUang(double jumlah) {
        saldo += jumlah; // Menambahkan jumlah ke saldo
        System.out.println(namaPemilik + " menyetor Rp" + jumlah + ". Saldo sekarang: Rp" + saldo); // Menampilkan informasi transaksi
    }

    /**
     * Method untuk mengurangi jumlah dari saldo (transaksi penarikan)
     * @param jumlah jumlah uang yang ditarik
     */
    public void tarikUang(double jumlah) {
        if (saldo >= jumlah) { // Memeriksa apakah saldo mencukupi
            saldo -= jumlah; // Mengurangi saldo
            System.out.println(namaPemilik + " menarik Rp" + jumlah + ". (Berhasil) Saldo sekarang: Rp" + saldo); // Informasi penarikan berhasil
        } else {
            System.out.println(namaPemilik + " menarik Rp" + jumlah + ". (Gagal, Saldo tidak mencukupi) Saldo saat ini: Rp" + saldo); // Informasi penarikan gagal
        }
    }
}
