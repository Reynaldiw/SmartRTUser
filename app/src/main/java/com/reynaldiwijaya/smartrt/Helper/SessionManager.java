package com.reynaldiwijaya.smartrt.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.reynaldiwijaya.smartrt.MainActivity;
import com.reynaldiwijaya.smartrt.ui.Intro.IntroActivity;
import com.reynaldiwijaya.smartrt.ui.Login.LoginActivity;


public class SessionManager extends MyFunction {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String KEY_USERNAME = "username";
    private static final String is_login = "loginstatus";
    private final String SHARE_NAME = "loginsession";
    private final int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager(Context context) {
        this._context = context;
        sp = _context.getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * Membuat method yang berfungsi untuk menyimpan data username dan *****
     */

    public void storeLogin(String user) {
        editor.putBoolean(is_login, true);
        editor.putString(KEY_USERNAME, user);
        editor.commit();
    }

    public void setImageUser(String url) {
        editor.putString("imageuser", url);
        editor.commit();
    }

    public String getImageUser() {
        return sp.getString("imageuser", "");
    }

    public void setNameUser(String nameUser) {
        editor.putString("nameuser", nameUser);
        editor.commit();
    }

    public String getNameUser() {
        return sp.getString("nameuser", "");
    }

    public void setIdUser(String idUser) {
        editor.putBoolean(is_login, true);
        editor.putString("idUser", idUser);
        editor.commit();
    }

    public String getIdUser() {
        return sp.getString("idUser", "");
    }


    public void setNoKtp(String noKtp) {
        editor.putString("noKtp", noKtp);
        editor.commit();
    }

    public String getNoKtp() {
        return sp.getString("noKtp", "");
    }

    public void setAlamat(String alamat) {
        editor.putString("alamat", alamat);
        editor.commit();
    }

    public String getAlamat() {
        return sp.getString("alamat", "");
    }

    public void setStatus(String status) {
        editor.putString("status", status);
        editor.commit();
    }

    public String getStatus() {
        return sp.getString("status", "");
    }

    public void setTglLahir(String tglLahir) {
        editor.putString("tglLahir", tglLahir);
        editor.commit();
    }

    public String getTglLahir() {
        return sp.getString("tglLahir", "");
    }

    public void setJenkel(String jenkel) {
        editor.putString("jenkel", jenkel);
        editor.commit();
    }

    public String getJenkel() {
        return sp.getString("jenkel", "");
    }

    public void setProfesi(String profesi) {
        editor.putString("profesi", profesi);
        editor.commit();
    }

    public String getProfesi() {
        return sp.getString("profesi", "");
    }

    public void setNoTlp(String noTlp) {
        editor.putString("noTlp", noTlp);
        editor.commit();
    }

    public String getNoTlp() {
        return sp.getString("noTlp", "");
    }

    public void setEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public String getEmail() {
        return sp.getString("email", "");
    }

    public void setLevel(String level) {
        editor.putString("level", level);
        editor.commit();
    }

    public String getLevel() {
        return sp.getString("level", "");
    }

    public void setKonfirmasi(String konfirmasi) {
        editor.putString("konfirmasi", konfirmasi);
        editor.commit();
    }

    public String getKonfirmasi() {
        return sp.getString("konfirmasi", "");
    }


    public void checkLogin() {
            if (!this.Login()) {
                Intent intent = new Intent(_context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(intent);
            } else {
                Intent intent = new Intent(_context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(intent);
            }
    }

    public Boolean Login() {
        return sp.getBoolean(is_login, false);
    }

    public void Logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

}
