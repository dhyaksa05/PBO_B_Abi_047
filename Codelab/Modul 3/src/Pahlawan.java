// Kelas Pahlawan (Subclass dari KarakterGame)
class Pahlawan extends KarakterGame {
    // Constructor memanggil constructor superclass
    public Pahlawan(String nama, int kesehatan, int atk, String status) {
        super(nama, kesehatan, atk, status);
    }

    // Override method serang dengan pilihan serangan
    @Override
    public void serang(KarakterGame target, int pilihanSerangan, int atk) {
        switch (pilihanSerangan) {
            case 1: // Basic Atk - Boltsunder
                System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan Boltsunder!");
                target.setKesehatan(target.getKesehatan() -  atk  );
                break;
            case 2: // Skill - Waraxe
                System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan Waraxe!");
                target.setKesehatan(target.getKesehatan() -  (atk + (atk * (20 / 100) + 30)));
                break;
            case 3: // Ultimate - Terrasplit
                System.out.println(getNama() + " menggunakan Ultimate Terrasplit terhadap " + target.getNama() + "!");
                target.setKesehatan(target.getKesehatan() -  (atk + (atk * (70 / 100) + 70)));
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
