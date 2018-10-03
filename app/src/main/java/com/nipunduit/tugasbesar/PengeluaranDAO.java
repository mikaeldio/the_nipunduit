package com.nipunduit.tugasbesar;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import java.util.List;

public class PengeluaranDAO extends SugarRecord {
    @SerializedName("nominal")
    Integer Jumlah;
    @SerializedName("keterangan")
    String Keterangan;
    String email;
    String error;
    String error_msg;

    public class Value {
        List<PengeluaranDAO> result;

        public List<PengeluaranDAO> getResult() {
            return this.result;
        }
    }

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

    public String getKeterangan() {
        return this.Keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.Keterangan = keterangan;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PengeluaranDAO(String Keterangan, Integer Jumlah) {
        this.Keterangan = Keterangan;
        this.Jumlah = Jumlah;
    }

    public Integer getJumlah() {
        return this.Jumlah;
    }

    public String getJumlahS() {
        return this.Jumlah.toString();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PengeluaranDAO{Keterangan='");
        stringBuilder.append(this.Keterangan);
        stringBuilder.append('\'');
        stringBuilder.append(", Jumlah=");
        stringBuilder.append(this.Jumlah);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
