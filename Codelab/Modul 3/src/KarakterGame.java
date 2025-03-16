// Kelas KarakterGame (Superclass)
public class KarakterGame {
    private String nama;
    private int kesehatan;
    private int atk;
    private String status;

    // Constructor
    public KarakterGame(String nama, int kesehatan, int atk, String status) {
        this.nama = nama;
        this.atk = atk;
        this.kesehatan = kesehatan;
        this.status = status;
    }

    // Getter untuk nama
    public String getNama() {
        return nama;
    }

    // Getter untuk kesehatan
    public int getKesehatan() {
        return kesehatan;
    }

    // Setter untuk kesehatan
    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }
    // Getter untuk attack
    public int getAtk() {
       return atk;
    }

    // Setter untuk memberikan buff
    public void setBuff(int atk) {
        this.atk = atk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
         this.status = status;
    }

    // Method serang yang akan di-override oleh subclass
    public void serang(KarakterGame target, int pilihanSerangan, int atk) {
        // Implementasi dasar, akan di-override oleh subclass
    }

    // Method untuk cek apakah karakter masih hidup
    public boolean isAlive() {return kesehatan > 0;
    }
}

