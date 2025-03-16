// Kelas Pahlawan2 (Subclass dari KarakterGame)
class Pahlawan2 extends KarakterGame {
    // Constructor memanggil constructor superclass
    public Pahlawan2 (String nama, int kesehatan, int atk, String status) {
        super(nama, kesehatan, atk, status);
    }

    // Override method serang dengan pilihan serangan
    @Override
    public void serang(KarakterGame target, int pilihanSerangan, int atk) {
        switch (pilihanSerangan) {
            case 1: // Basic Atk - Wingflip White Noise
                System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan Wingflip White Noise!");
                target.setKesehatan(target.getKesehatan() - atk);
                System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
                break;
            case 2: // Skill - Pinion's Aria
                System.out.println(getNama() + " memberikan buff kepada " + target.getNama() + " menggunakan Pinion's Aria!");
                target.setBuff(target.getAtk() + (target.getAtk() * 5 / 100));
                System.out.println(target.getNama() + " sekarang memiliki atk " + target.getAtk());
                break;
            case 3: // Ultimate - Ultimate: Vox Harmonique, Opus Cosmique
                System.out.println(getNama() + " menggunakan Ultimate: Vox Harmonique, Opus Cosmique terhadap " + target.getNama() + "!");
                target.setBuff(target.getAtk() + (target.getAtk() * 10 / 100) + 20);
                System.out.println(target.getNama() + " sekarang memiliki atk " + target.getAtk());
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

    }
}
