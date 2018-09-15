package com.nipunduit.tugasbesar;

import com.orm.SugarRecord;

public class PengeluaranDAO extends SugarRecord {

    String Keterangan;
    String Jumlah;
    public PengeluaranDAO(){}

    public PengeluaranDAO(String Keterangan,
                          String Jumlah){
        this.Keterangan=Keterangan;
        this.Jumlah=Jumlah;
    }

    public String getJumlah() {
        return Jumlah;
    }
}
