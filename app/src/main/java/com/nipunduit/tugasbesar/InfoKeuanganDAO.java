package com.nipunduit.tugasbesar;

import android.content.Context;

public class InfoKeuanganDAO {
    int budget;
    int frekuensi;
    int tabungan;

    public InfoKeuanganDAO(int budget, int frekuensi, int tabungan) {
        this.budget = budget;
        this.frekuensi = frekuensi;
        this.tabungan = tabungan;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getFrekuensi() {
        return frekuensi;
    }

    public void setFrekuensi(int frekuensi) {
        this.frekuensi = frekuensi;
    }

    public int getTabungan() {
        return tabungan;
    }

    public void setTabungan(int tabungan) {
        this.tabungan = tabungan;
    }

    public class val{
        InfoKeuangan result;

        public InfoKeuangan getResult(){
            return result;
        }
    }
}
