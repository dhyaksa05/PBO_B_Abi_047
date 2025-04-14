class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String inputUsername, String inputPassword) {
        // Validasi username dan password Admin
        // Username valid: "Admin" + (3-digit NIM terakhir)
        // Password valid: "Password" + (3-digit NIM terakhir)
        if (inputUsername.startsWith("Admin") && inputPassword.startsWith("Password")) {
            if (inputUsername.length() >= 5 && inputPassword.length() >= 8) {
                String usernameDigits = inputUsername.substring(5);
                String passwordDigits = inputPassword.substring(8);
                return usernameDigits.equals(passwordDigits) && usernameDigits.length() == 3;
            }
        }
        return false;
    }
}
