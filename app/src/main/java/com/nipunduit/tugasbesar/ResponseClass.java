package com.nipunduit.tugasbesar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ResponseClass {
    @SerializedName("pengeluaranHarian")
    @Expose
    private ArrayList<PengeluaranDAO> pengeluaran = null;

    public ArrayList<PengeluaranDAO> getPengeluaranArrayList() {
        return this.pengeluaran;
    }

    public void setPengeluaranHarian(ArrayList<PengeluaranDAO> pengeluaran) {
        this.pengeluaran = pengeluaran;
    }
}
