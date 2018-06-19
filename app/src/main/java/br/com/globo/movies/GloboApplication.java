package br.com.globo.movies;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class GloboApplication extends Application {
    private static GloboApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static GloboApplication getInstance(){return instance;}
}
