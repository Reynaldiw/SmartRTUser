package com.reynaldiwijaya.smartrt.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.reynaldiwijaya.smartrt.ui.Intro.IntroActivity;
import com.reynaldiwijaya.smartrt.ui.Login.LoginActivity;

public class SessionIntro {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String SHARE_NAME = "introPref";
    private Context _context;

    public SessionIntro(Context _context) {
        this._context = _context;
        sp = _context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeIntro() {
        editor.putBoolean(Constant.KEY_INTRO, true);
        editor.commit();
    }

    public Boolean getIntro() {
        return sp.getBoolean(Constant.KEY_INTRO, false);
    }

    }



