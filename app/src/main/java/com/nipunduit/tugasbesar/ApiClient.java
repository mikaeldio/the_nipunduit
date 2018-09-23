package com.nipunduit.tugasbesar;

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
}
