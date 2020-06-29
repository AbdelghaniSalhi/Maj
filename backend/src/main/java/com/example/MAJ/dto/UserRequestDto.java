package com.example.MAJ.dto;

public class UserRequestDto {
    private String email;
    private String login;
    private String pwd;
    private String role;

    public UserRequestDto() {}

    public UserRequestDto(String email, String login, String pwd, String role) {
        this.email = email;
        this.login=login;
        this.pwd = pwd;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}