package com.nipunduit.tugasbesar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClient {
    //CRUD untuk user
    @POST("register.php")
    @FormUrlEncoded
    Call<UserDAO> registerUser(
            @Field("name") String nama,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );

    @POST("login.php")
    @FormUrlEncoded
    Call<UserDAO> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("view-profil.php")
    Call<UserDAO> getProfil(@Query("email") String email);


    @POST("edit-profil.php")
    @FormUrlEncoded
    Call<UserDAO> editUser(
            @Field("name") String nama,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );

    //CRUD untuk info budget
    @POST("add-budget.php")
    @FormUrlEncoded
    Call<InfoKeuanganDAO> addBudget(
            @Field("email") String email,
            @Field("budget_bulanan") int budget_bulanan,
            @Field("target_tabungan") int target_tabungan,
            @Field("frekuensi") int frekuensi
    );

    @GET("view-bulan.php")
    Call<List<PengeluaranBulananDAO>> getPengeluaranBulan(
            @Query("email") String email
    );

    @GET("view-home.php")
    Call<InfoKeuanganDAO> getInfoKeuangan(@Query("email") String email);

    //CRUD untuk tambah pengeluaran (sehari-hari)
    @POST("add-pengeluaran.php")
    @FormUrlEncoded
    Call<PengeluaranDAO> addPengeluaran( //fungsi ini digunakan untuk menambah sekaligus mengedit budget perbulan
            @Field("email") String email,
            @Field("keterangan") String keterangan,
            @Field("nominal") int nominal,
            @Field("tanggal") String tanggal
    );

    @GET("view-hari.php")
    Call<List<PengeluaranDAO>> getPengeluaranHari(
            @Query("email") String email
    );

    @POST("edit-pengeluaranHarian.php")
    @FormUrlEncoded
    Call<PengeluaranDAO> editPengeluaran(
            @Field("email") String email,
            @Field("old_ket") String old_ket, //didapat dari keterangan sebelum diedit (bundle)
            @Field("old_nominal") int old_nominal, //didapat dari nominal sebelum diedit (bundle)
            @Field("tanggal") String tanggal,
            @Field("new_ket") String new_ket, //didapat setelah user menginputkan/mengedit keterangan
            @Field("new_nominal") int new_nominal //didapat setelah user menginputkan/mengedit nominal
    );

    @POST("delete-pengeluaranHarian.php")
    @FormUrlEncoded
    Call<PengeluaranDAO> deletePengeluaran(
            @Field("email") String email,
            @Field("keterangan") String keterangan,
            @Field("nominal") String nominal,
            @Field("tanggal") String tanggal
    );
}
