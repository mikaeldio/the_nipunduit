package com.nipunduit.tugasbesar;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.List;

public class PengeluaranDAO extends SugarRecord {

    @SerializedName("keterangan")
    String Keterangan;
    @SerializedName("nominal")
    Integer Jumlah;
    String email;

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

    public PengeluaranDAO(){}

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PengeluaranDAO(String Keterangan,
                          Integer Jumlah){
        this.Keterangan=Keterangan;
        this.Jumlah=Jumlah;
    }

    public Integer getJumlah() {
        return Jumlah;
    }

    public String getJumlahS() {
        return Jumlah.toString();
    }

    @Override
    public String toString() {
        return "PengeluaranDAO{" +
                "Keterangan='" + Keterangan + '\'' +
                ", Jumlah=" + Jumlah +
                '}';
    }

    public class Value{
        List<PengeluaranDAO> result;
        public List<PengeluaranDAO> getResult(){
            return result;
        }
    }

}
