package com.example.praktiklogin.Model;

public class Modeluser {

    private String Username;
    private String Password;
    private String Nomor;
    private String Alamat;
    private Int id;

    public Modeluser(String Username, String Password, String Alamat, String Nomor) {
        this.Username = Username;
        this.Password = Password;
        this.Alamat = Alamat;
        this.Nomor = Nomor;
    }

    public Modeluser() {

    }

    public Int getId() {
        return id;
    }

    public void setId(Int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNomor() {
        return Nomor;
    }

    public void setNomor(String nomor) {
        Nomor = nomor;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public void setId(int parseInt) {
        this.id = id;
    }

    private class Int {
    }
}
