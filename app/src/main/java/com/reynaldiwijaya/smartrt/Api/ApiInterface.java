package com.reynaldiwijaya.smartrt.Api;

import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.model.Agenda.ResponseAgenda;
import com.reynaldiwijaya.smartrt.model.Agenda.ResponseGetAgenda;
import com.reynaldiwijaya.smartrt.model.Informasi.ResponseGetInformasi;
import com.reynaldiwijaya.smartrt.model.Informasi.ResponseInformasi;
import com.reynaldiwijaya.smartrt.model.Laporan.ResponseLaporan;
import com.reynaldiwijaya.smartrt.model.Laporan.ResponseLaporanKonfirmasi;
import com.reynaldiwijaya.smartrt.model.NewsIndonesia.ResponseNewsIndonesia;
import com.reynaldiwijaya.smartrt.model.Profile.ResponseProfile;
import com.reynaldiwijaya.smartrt.model.Store.ResponseDataStore;
import com.reynaldiwijaya.smartrt.model.Store.ResponseStore;
import com.reynaldiwijaya.smartrt.model.Warga.ResponseWarga;
import com.reynaldiwijaya.smartrt.model.login.ResponseLogin;
import com.reynaldiwijaya.smartrt.model.register.ResponseRegister;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

//    @Multipart
//    @POST("register.php")
//    Call<ResponseRegister> registerUser(
//            @Part("nama_lengkap") RequestBody nama,
//            @Part("no_ktp") RequestBody nomor_ktp,
//            @Part("alamat") RequestBody alamat,
//            @Part("status") RequestBody status,
//            @Part("tgl_lahir") RequestBody tgl_lahir,
//            @Part("jenkel") RequestBody jenkel,
//            @Part("profesi") RequestBody profesi,
//            @Part("no_tlp") RequestBody no_tlp,
//            @Part("email") RequestBody email,
//            @Part("username") RequestBody username,
//            @Part("password") RequestBody password,
//            @Part MultipartBody.Part foto,
//            @Part("konfirmasi") RequestBody konfirmasi,
//            @Part("level") RequestBody level
//    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> loginUser(
            @Field("edtusername") String username,
            @Field("edtpassword") String password
    );

    @FormUrlEncoded
    @POST("laporan.php")
    Call<ResponseLaporan> laporan(
            @Field("id_user") String iduser,
            @Field("judul") String judul,
            @Field("laporan") String laporan,
            @Field("tgl_lapor") String date
    );

    @FormUrlEncoded
    @POST("insertnews.php")
    Call<ResponseInformasi> informasi(
            @Field("id_user") String idUser,
            @Part MultipartBody.Part foto,
            @Field("judul") String judul,
            @Field("content") String content,
            @Field("tgl_nulis") String date,
            @Field("konfirmasi") String konfirmasi
    );

    @GET("getDataWarga.php")
    Call<ResponseWarga> warga();

    @FormUrlEncoded
    @POST("getDataUser.php")
    Call<ResponseProfile> profile(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("agenda.php")
    Call<ResponseAgenda> agenda(
            @Field("judul") String judul,
            @Field("content") String content,
            @Field("tempat") String tempat,
            @Field("tanggal") String tanggal,
            @Field("konfirmasi") int konfirmasi
    );

    @GET("getagenda.php")
    Call<ResponseGetAgenda> getAgenda();

    @FormUrlEncoded
    @POST("insertstore.php")
    Call<ResponseStore> sendDataStore(
            @Field("id_user") String id_user,
            @Field("nama_toko") String nama_toko,
            @Field("deskripsi") String deskripsi,
            @Field("alamat") String alamat,
            @Field("no_tlp") String no_tlp
    );

    @GET("konfirmasistore.php")
    Call<ResponseDataStore> getDataStore();

    @GET("getinformasi.php")
    Call<ResponseGetInformasi> getInformasi();

    @GET("getlaporankonfirmasi.php")
    Call<ResponseLaporanKonfirmasi> getLaporanKonfirmasi();

    @GET("v2/top-headlines?country=id&apiKey=" + Constant.API_INDONESIA_NEWS)
    Call<ResponseNewsIndonesia> getNewsIndonesia();

}
