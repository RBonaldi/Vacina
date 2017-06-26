package com.adm.vacina_digital;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by rafael on 18/06/17.
 */

public class CPF {

    private SharedPreferences prefs;

    public CPF(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit().putString("CPF", usename).commit();
    }

    public String getusename() {
        String usename = prefs.getString("CPF","");
        return usename;
    }
}
