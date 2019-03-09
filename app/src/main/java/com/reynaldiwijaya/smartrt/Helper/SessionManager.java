package com.reynaldiwijaya.smartrt.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

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

    public void storeIntro() {
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    public boolean intro() {
        return sp.getBoolean("isIntroOpened", false);
    }


    public void checkLogin() {
        if (!this.intro()){
            Intent intent = new Intent(_context, IntroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            _context.startActivity(intent);
        } else {

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
