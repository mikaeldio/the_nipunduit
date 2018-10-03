package com.nipunduit.tugasbesar;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClient {
    @FormUrlEncoded
    @POST("add-budget.php")
    Call<InfoKeuanganDAO> addBudget(@Field("email") String str, @Field("budget_bulanan") int i, @Field("target_tabungan") int i2, @Field("frekuensi") int i3);

    @FormUrlEncoded
    @POST("add-pengeluaran.php")
    Call<PengeluaranDAO> addPengeluaran(@Field("email") String str, @Field("keterangan") String str2, @Field("nominal") int i, @Field("tanggal") String str3);

    @FormUrlEncoded
    @POST("edit-profil.php")
    Call<UserDAO> editUser(@Field("name") String str, @Field("email") String str2, @Field("phone") String str3, @Field("password") String str4);

    @GET("view-home.php")
    Call<InfoKeuanganDAO> getInfoKeuangan(@Query("email") String str);

    @GET("view-bulan.php")
    Call<List<PengeluaranBulananDAO>> getPengeluaranBulan(@Query("email") String str);

    @GET("view-hari.php")
    Call<List<PengeluaranDAO>> getPengeluaranHari(@Query("email") String str);

    @GET("view-profil.php")
    Call<UserDAO> getProfil(@Query("email") String str);

    @FormUrlEncoded
    @POST("login.php")
    Call<UserDAO> loginUser(@Field("email") String str, @Field("password") String str2);

    @FormUrlEncoded
    @POST("register.php")
    Call<UserDAO> registerUser(@Field("name") String str, @Field("email") String str2, @Field("phone") String str3, @Field("password") String str4);
}
