package sistemperpustakaan;

// Class abstrak Pengguna dalam file terpisah
public abstract class Pengguna {
    protected String nama;
    protected String id;

    // Constructor
    public Pengguna(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    // Getter dan Setter (Encapsulation)
    public String getNama() {
        return nama;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Method abstrak
    public abstract void tampilkanInfo();


}