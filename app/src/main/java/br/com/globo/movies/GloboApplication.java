package br.com.globo.movies;

import android.app.Application;

public class GloboApplication extends Application {
    private static GloboApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static GloboApplication getInstance(){return instance;}
}
