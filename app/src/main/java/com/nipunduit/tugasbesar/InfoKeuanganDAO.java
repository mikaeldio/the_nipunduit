package com.nipunduit.tugasbesar;

import android.content.Context;

public class InfoKeuanganDAO {
    int budget_bulanan;
    int frekuensi;
    int target_tabungan;
    String email;
    String error;
    String error_msg;

    public InfoKeuanganDAO(int budget, int frekuensi, int tabungan) {
        this.budget_bulanan = budget;
        this.frekuensi = frekuensi;
        this.target_tabungan = tabungan;
    }

    public int getBudget() {
        return budget_bulanan;
    }

    public void setBudget(int budget) {
        this.budget_bulanan = budget;
    }

    public int getFrekuensi() {
        return frekuensi;
    }

    public void setFrekuensi(int frekuensi) {
        this.frekuensi = frekuensi;
    }

    public int getTabungan() {
        return target_tabungan;
    }

    public void setTabungan(int tabungan) {
        this.target_tabungan = tabungan;
    }

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

    public class val{
        InfoKeuangan result;

        public InfoKeuangan getResult(){
            return result;
        }
    }
}
