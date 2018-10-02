package com.nipunduit.tugasbesar;

import java.util.Date;
import com.orm.SugarRecord;

public class PengeluaranBulananDAO extends SugarRecord{
    String Tanggal;
    Integer Jumlah;

    //cek response jeson
    String error;
    String error_msg;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public Integer getJumlah() {
        return Jumlah;
    }

    public void setJumlah(Integer jumlah) {
        Jumlah = jumlah;
    }

    public PengeluaranBulananDAO(String tanggal, Integer jumlah) {

        Tanggal = tanggal;
        Jumlah = jumlah;
    }

    public PengeluaranBulananDAO() {

    }
}
