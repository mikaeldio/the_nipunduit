package com.nipunduit.tugasbesar;

import com.orm.SugarRecord;

public class PengeluaranDAO extends SugarRecord {

    String Keterangan;
    Integer Jumlah;
    public PengeluaranDAO(){}

    public PengeluaranDAO(String Keterangan,
                          Integer Jumlah){
        this.Keterangan=Keterangan;
        this.Jumlah=Jumlah;
    }

    public Integer getJumlah() {
        return Jumlah;
    }

}
