// Kelas Musuh (Subclass dari KarakterGame)
class Musuh extends KarakterGame {
    // Constructor memanggil constructor superclass
    public Musuh(String nama, int kesehatan, int atk, String status) {
        super(nama, kesehatan, atk, status);
    }

    // Override method serang dengan pilihan serangan
    @Override
    public void serang(KarakterGame target, int pilihanSerangan, int atk) {
        switch (pilihanSerangan) {
            case 1: // Skill - Severed Iron as Claws
                System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan Severed Iron as Claws!");
                target.setKesehatan(target.getKesehatan() - 75);
                break;
            case 2: // Ultimate - Decimation of Roaring Storms
                System.out.println(getNama() + " menggunakan Ultimate: Decimation of Roaring Storms terhadap " + target.getNama() + "!");
                target.setKesehatan(target.getKesehatan() - 150);
                break;
            default:
                System.out.println("Pilihan serangan tidak valid!");
                return;
        }

        // Pastikan kesehatan tidak negatif
        if (target.getKesehatan() < 0) {
            target.setKesehatan(0);
        }

        // Menampilkan kesehatan terbaru target
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }
}
