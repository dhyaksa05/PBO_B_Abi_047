import java.util.Objects;
import java.util.Scanner;
import java.util.Random;
// Kelas Main (Kelas Utama)
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membuat karakter Pahlawan dan Musuh dengan nilai kesehatan yang diperbarui
        Pahlawan feixiao = new Pahlawan("Feixiao", 375, 60, "Hidup");
        Pahlawan2 robin  = new Pahlawan2 ("Robin", 300, 30, "Hidup");
        Musuh hoolay = new Musuh("Hoolay", 700, 0, "Hidup");

        // Menampilkan status awal kesehatan
        System.out.println("Status awal:");
        System.out.println(feixiao.getNama() + " memiliki kesehatan: " + feixiao.getKesehatan() + " dengan serangan dasar sebesar : " + feixiao.getAtk());
        System.out.println(robin.getNama() + " memiliki kesehatan: " + robin.getKesehatan() + " dengan serangan dasar sebesar : " + robin.getAtk());
        System.out.println(hoolay.getNama() + " memiliki kesehatan: " + hoolay.getKesehatan());
        System.out.println();

        int ronde = 1;

        // Perulangan akan terus berjalan selama salah satu pahlawan  masih hidup
        while ((feixiao.isAlive() || robin.isAlive()) && hoolay.isAlive()) {
            System.out.println("==== RONDE " + ronde + " ====");

            if (robin.isAlive()){
                // Giliran Pahlawan (Robin) menyerang
                System.out.println("Pilih serangan untuk " + robin.getNama() + ":");
                System.out.println("1. Basic Atk: Wingflip White Noise (-" + robin.getAtk() + " poin)");
                System.out.println("2. Skill: Pinion's Aria (Buff meningkatkan serangan kerusakan 5%)");
                System.out.println("3. Ultimate: Vox Harmonique, Opus Cosmique (Buff meningkatkan serangan kerusakan sebesar 10% + flat damage 30%)");
                System.out.print("Pilihan Anda (1/2/3): ");
                int pilihanPahlawan2 = scanner.nextInt();

                if (pilihanPahlawan2 == 1) {
                    robin.serang(hoolay, pilihanPahlawan2, robin.getAtk());
                } else {
                    robin.serang(feixiao, pilihanPahlawan2, robin.getAtk());
                }


                System.out.println();

                // Cek apakah Musuh sudah kalah
                if (!hoolay.isAlive()) {
                    System.out.println(hoolay.getNama() + " telah dikalahkan!");
                    break;
                }
            }
            if (feixiao.isAlive()){
                // Giliran Pahlawan (Feixiao) menyerang
                System.out.println("Pilih serangan untuk " + feixiao.getNama() + ":");
                System.out.println("1. Basic Atk : Boltsunder (-" + feixiao.getAtk() + " poin)");
                System.out.println("2. Skill: Waraxe (-" + (feixiao.getAtk() + (feixiao.getAtk() * (20 / 100) + 30)) + " poin)");
                System.out.println("3. Ultimate: Terrasplit (-" + (feixiao.getAtk() + (feixiao.getAtk() * (70 / 100) + 70)) + " poin)");
                System.out.print("Pilihan Anda (1/2/3): ");
                int pilihanPahlawan = scanner.nextInt();

                feixiao.serang(hoolay, pilihanPahlawan, feixiao.getAtk());
                System.out.println();

                // Cek apakah Musuh sudah kalah
                if (!hoolay.isAlive()) {
                    System.out.println(hoolay.getNama() + " telah dikalahkan!");
                    break;
                }
            }

            Random random = new Random();
            int pilihanMusuh;
            pilihanMusuh = random.nextInt(2) + 1;

            Random random2 = new Random();
            int target;
            target = random2.nextInt(2) + 1;

            if (target == 1) {
                if (feixiao.isAlive()){
                    hoolay.serang(feixiao, pilihanMusuh, hoolay.getAtk());
                    System.out.println("Musuh menyerang Feixiao");
                } else {
                    hoolay.serang(robin, pilihanMusuh, hoolay.getAtk());
                    System.out.println("Musuh menyerang Robin");
                }
            } else {
                if (robin.isAlive()){
                    hoolay.serang(robin, pilihanMusuh, hoolay.getAtk());
                    System.out.println("Musuh menyerang Robin");
                } else {
                    hoolay.serang(feixiao, pilihanMusuh, hoolay.getAtk());
                    System.out.println("Musuh menyerang Feixiao");
                }
            }

            System.out.println();
            int urutan = 0;

            // Cek apakah Pahlawan sudah kalah
            if (Objects.equals(feixiao.getStatus(), "Hidup")){
                if (!feixiao.isAlive()){
                    System.out.println(feixiao.getNama() + " telah dikalahkan!");
                    feixiao.setStatus("Mati");
                }
            }
            if (Objects.equals(robin.getStatus(), "Hidup")){
                if (!robin.isAlive()) {
                    System.out.println(robin.getNama() + " telah dikalahkan!");
                    robin.setStatus("Mati");
                }
            }
            if (!feixiao.isAlive() && !robin.isAlive()) {
                break;
            }

            // Tampilkan status kesehatan terkini
            System.out.println("Status terkini:");
            System.out.println(feixiao.getNama() + " memiliki kesehatan: " + feixiao.getKesehatan());
            System.out.println(robin.getNama() + " memiliki kesehatan: " + robin.getKesehatan());
            System.out.println(hoolay.getNama() + " memiliki kesehatan: " + hoolay.getKesehatan());
            System.out.println();

            ronde++;
        }

        // Tampilkan hasil akhir pertarungan
        System.out.println("\n==== HASIL PERTARUNGAN ====");
        if (!feixiao.isAlive() && !robin.isAlive() && !hoolay.isAlive()) {
            System.out.println("Pertarungan berakhir seri! Semua karakter telah kalah.");
        } else if (!feixiao.isAlive() && !robin.isAlive()) {
            System.out.println(feixiao.getNama() + " dan " + robin.getNama() + " telah dikalahkan!");
            System.out.println(hoolay.getNama() + " memenangkan pertarungan!");
        } else if (!hoolay.isAlive()) {
            System.out.println(feixiao.getNama() + " dan " + robin.getNama() + " memenangkan pertarungan!");
        }
        System.out.println("\nPertarungan berakhir setelah " + ronde + " ronde!");
        scanner.close();
    }
}