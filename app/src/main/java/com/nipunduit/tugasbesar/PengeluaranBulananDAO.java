package com.nipunduit.tugasbesar;

import java.util.Date;

public class PengeluaranBulananDAO {
    Date Tanggal;
    Integer Jumlah;

    public Date getTanggal() {
        return Tanggal;
    }

    public void setTanggal(Date tanggal) {
        Tanggal = tanggal;
    }

    public Integer getJumlah() {
        return Jumlah;
    }

    public void setJumlah(Integer jumlah) {
        Jumlah = jumlah;
    }

    public PengeluaranBulananDAO(Date tanggal, Integer jumlah) {

        Tanggal = tanggal;
        Jumlah = jumlah;
    }

    public PengeluaranBulananDAO() {

    }
}
