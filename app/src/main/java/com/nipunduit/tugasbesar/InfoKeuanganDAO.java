package com.nipunduit.tugasbesar;

public class InfoKeuanganDAO {
    int budget_bulanan;
    String email;
    String error;
    String error_msg;
    int frekuensi;
    int target_tabungan;

    public class val {
        InfoKeuangan result;

        public InfoKeuangan getResult() {
            return this.result;
        }
    }

    public InfoKeuanganDAO(int budget, int frekuensi, int tabungan) {
        this.budget_bulanan = budget;
        this.frekuensi = frekuensi;
        this.target_tabungan = tabungan;
    }

    public int getBudget() {
        return this.budget_bulanan;
    }

    public void setBudget(int budget) {
        this.budget_bulanan = budget;
    }

    public int getFrekuensi() {
        return this.frekuensi;
    }

    public void setFrekuensi(int frekuensi) {
        this.frekuensi = frekuensi;
    }

    public int getTabungan() {
        return this.target_tabungan;
    }

    public void setTabungan(int tabungan) {
        this.target_tabungan = tabungan;
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
}
