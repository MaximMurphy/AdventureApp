package com.example.adventureapp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {

    private Context ct;
    public LanguageManager(Context ctx){
        ct = ctx;
    }

    public void updateResource(String code){
        Locale locale = new Locale(code);
        locale.setDefault(locale);
        Resources resources = ct.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
