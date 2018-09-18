package com.nipunduit.tugasbesar;

public class UserDAO {
    String nama;
    String email;
    String telp;
    String password;

    public UserDAO(String nama, String email, String telp, String password){
        this.nama=nama;
        this.email=email;
        this.telp=telp;
        this.password=password;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getTelp() {
        return telp;
    }

    public String getPassword() {
        return password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
