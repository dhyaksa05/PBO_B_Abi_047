class Admin extends User {
    // Additional attributes
    private String username;
    private String password;

    // Constructor using super to initialize name and nim
    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);  // Memanggil constructor dari kelas induk
        this.username = username;
        this.password = password;
    }

    // Getter dam Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Override login method to check username and password
    @Override
    public boolean login(String inputUsername, String inputPassword) {
        // Username valid: "Admin" + (3-digit NIM terakhir)
        // Password valid: "Password" + (3-digit NIM terakhir)
        if (inputUsername.startsWith("Admin") && inputPassword.startsWith("Password")) {
            if (inputUsername.length() >= 8 && inputPassword.length() >= 11) {
                String usernameDigits = inputUsername.substring(5);
                String passwordDigits = inputPassword.substring(8);

                // Verify the digits match and are 3 digits
                if (usernameDigits.equals(passwordDigits) && usernameDigits.matches("\\d{3}")) {
                    // Update the NIM based on login input
                    String baseNim = "12345";  // Base NIM prefix
                    setNim(baseNim + usernameDigits);
                    return true;
                }
            }
        }
        return false;
    }

    // Override displayInfo method to show login success message
    @Override
    public void displayInfo() {
        System.out.println("Login Admin berhasil!");
        super.displayInfo();  // Memanggil method displayInfo dari kelas induk
        System.out.println("Status: Admin");
    }
}