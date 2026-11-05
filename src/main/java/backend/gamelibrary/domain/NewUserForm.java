package backend.gamelibrary.domain;

import jakarta.validation.constraints.NotEmpty;

public class NewUserForm {

    @NotEmpty
    private String username = "";
    @NotEmpty
    private String email = "";
    @NotEmpty
    private String password = "";
    @NotEmpty
    private String passwordCheck = "";
    @NotEmpty
    private String role = "LoggedInUser";

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

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NewUserForm [username=" + username + ", email=" + email + ", password=" + password + ", passwordCheck="
                + passwordCheck + ", role=" + role + "]";
    }

}