package com.nipunduit.tugasbesar;

public class UserDAO {
    String email;
    String error;
    String error_msg;
    String name;
    String password;
    String phone;

    public class val {
        UserDAO result;

        public UserDAO getResult() {
            return this.result;
        }
    }

    public UserDAO(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getNama() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelp() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }

    public String getError() {
        return this.error;
    }

    public String getError_msg() {
        return this.error_msg;
    }

    public void setNama(String nama) {
        this.name = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelp(String telp) {
        this.phone = telp;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setError_message(String error_msg) {
        this.error_msg = error_msg;
    }

    public void setError(String error) {
        this.error = error;
    }
}
