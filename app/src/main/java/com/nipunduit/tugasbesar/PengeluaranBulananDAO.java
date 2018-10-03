package com.nipunduit.tugasbesar;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class PengeluaranBulananDAO extends SugarRecord {
    @SerializedName("totalPengeluaran_perhari")
    Integer Jumlah;
    @SerializedName("tanggal")
    String Tanggal;
    String error;
    String error_msg;

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_msg() {
        return this.error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getTanggal() {
        return this.Tanggal;
    }

    public void setTanggal(String tanggal) {
        this.Tanggal = tanggal;
    }

    public Integer getJumlah() {
        return this.Jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.Jumlah = jumlah;
    }

    public PengeluaranBulananDAO(String tanggal, Integer jumlah) {
        this.Tanggal = tanggal;
        this.Jumlah = jumlah;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PengeluaranDAO{Keterangan='");
        stringBuilder.append(this.Tanggal);
        stringBuilder.append('\'');
        stringBuilder.append(", Jumlah=");
        stringBuilder.append(this.Jumlah);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
