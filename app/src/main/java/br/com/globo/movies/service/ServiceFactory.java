package br.com.globo.movies.service;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import br.com.globo.movies.BuildConfig;
import br.com.globo.movies.service.annotation.URL;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {
    public static okhttp3.OkHttpClient getClient(int timeout) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG)
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        return okHttpClient;
    }

    public static Gson getGsonBuilder() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();
        return gson;
    }

    public static <T> T createRetrofitService(final Class<T> clazz, String baseUrl) {
        return createService(clazz, baseUrl);
    }

    public static <T> T createRetrofitService(final Class<T> clazz) {
        String url = loadUrlFromServiceAnnotation(clazz);

        return createService(clazz, url);
    }

    @NonNull
    private static <T> String loadUrlFromServiceAnnotation(Class<T> clazz) {
        Annotation[] annotations = clazz.getAnnotations();

        String url = "";

        for (Annotation a : annotations) {
            if (a instanceof URL) {
                url = ((URL) a).value();
                break;
            }
        }
        return url;
    }

    private static <T> T createService(Class<T> clazz, String url) {
        Retrofit retrofit = getRetrofit(url);

        return retrofit.create(clazz);
    }

    @NonNull
    private static Retrofit getRetrofit(String url) {
        Gson gsonBuilder = getGsonBuilder();
        OkHttpClient okHttpClient = getClient(30);

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory( GsonConverterFactory.create(gsonBuilder))
                .addCallAdapterFactory( RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
