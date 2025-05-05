class User {
    // Encapsulation - private attributes
    private String nama;
    private String nim;

    // Constructor
    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    // Getter methods
    public void setNim(String nim) {
        this.nim = nim;
    }

    // Get last 3 digits of NIM
    public String getLast3DigitsNim() {
        if (nim.length() >= 3) {
            return nim.substring(nim.length() - 3);
        } else {
            return nim;
        }
    }

    // Method that will be overridden by subclasses
    public boolean login(String inputData1, String inputData2) {
        // Base implementation
        return false;
    }

    // Method to display user information
    public void displayInfo() {
        System.out.println("Informasi Pengguna:");
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + getLast3DigitsNim());  // Hanya menampilkan 3 digit terakhir
    }
}