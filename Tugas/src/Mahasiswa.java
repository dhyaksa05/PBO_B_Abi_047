class Mahasiswa {
    private String nama;
    private String nim;

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public boolean login(String inputNama, String inputNim) {
        // Validasi nama dan NIM Mahasiswa
        // NIM harus terdiri dari minimal 10 angka
        return !inputNama.trim().isEmpty() && inputNim.matches("\\d{10,}");
    }

    public void displayInfo() {
        System.out.println("Informasi Mahasiswa:");
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
    }
}
