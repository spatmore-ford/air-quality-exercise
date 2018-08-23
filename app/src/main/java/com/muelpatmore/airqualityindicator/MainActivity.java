package com.muelpatmore.airqualityindicator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    public String String = "https://api.waqi.info/feed/shanghai/";
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Retrofit.Builder().baseUrl(String).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(aqicninterface.class).whatsupinsingapore().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(data ->
            ((TextView)findViewById(R.id.text)).setText("The dominant pollutant in "+data.data.city.name+" is "+data.data.dominentpol+ " ("+data.data.aqi+")"));
    }

    public interface aqicninterface {
        String String = "?token=91af471d6b5444b7c01388f6d50ccfcbd18e8150";
        @GET(String) Observable<Response> whatsupinsingapore();
    }

    class Attribution {
        @SerializedName("name") public String name;
        @SerializedName("url") public String url;
    }

    class City {
        @SerializedName("name") public String name;
        @SerializedName("url") public String url;
        @SerializedName("geo") public List<String> geo = null;
    }

    class Data {
        @SerializedName("idx") public Integer idx;
        @SerializedName("aqi") public Integer aqi;
        @SerializedName("time") public Time time;
        @SerializedName("city") public City city;
        @SerializedName("attributions") public List<Attribution> attributions = null;
        @SerializedName("iaqi") public Iaqi iaqi;
        @SerializedName("dominentpol") public String dominentpol;
    }

    class Response {
        @SerializedName("status") public String status;
        @SerializedName("data") public Data data ;
    }

    class H {
        @SerializedName("v") public Integer v;
    }

    class Iaqi {
        @SerializedName("pm25") public Pm25 pm25;
        @SerializedName("o3") public O3 o3;
        @SerializedName("no2") public No2 no2;
        @SerializedName("t") public T t;
        @SerializedName("p") public P p;
        @SerializedName("h") public H h;
        @SerializedName("w") public W w;
    }

    class No2 {
        @SerializedName("v") public Integer v;
    }

    class O3 {
        @SerializedName("v") public Integer v;
    }

    class P {
        @SerializedName("v") public Integer v;
    }

    class Pm25 {
        @SerializedName("v") public Integer v;
    }

    class T {
        @SerializedName("v") public Integer v;
    }

    class Time {
        @SerializedName("v") public Integer v;
        @SerializedName("s") public String s;
        @SerializedName("tz") public String tz;
    }

    class W {
        @SerializedName("v") public Integer v;
    }
}
