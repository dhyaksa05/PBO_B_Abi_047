
class Mahasiswa extends User {
    // Constructor using super to initialize name and nim
    public Mahasiswa(String nama, String nim) {
        super(nama, nim);  // Call parent constructor
    }

    // Override login method to check name and nim
    @Override
    public boolean login(String inputNama, String inputNim) {
        // NIM must consist of at least 10 digits
        return !inputNama.trim().isEmpty() && inputNim.matches("\\d{10,}");
    }

    // Override displayInfo method to show login success message
    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil!");
        super.displayInfo();  // Call parent method
        System.out.println("Status: Mahasiswa");
    }
}