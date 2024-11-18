package com.example.acvarii;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListImages extends AppCompatActivity {

    private List<Bitmap> imagini = null;
    private List<ImaginiAcvariu> lista = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_images);
        List<Bitmap> imagini = new ArrayList<>();
        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://i.pinimg.com/736x/8a/31/d6/8a31d66f47421dd868ca8e720b08209b.jpg");
        linkuriImagini.add("https://i.pinimg.com/736x/df/91/9f/df919fa72a97336aea27749841b1d7af.jpg");
        linkuriImagini.add("https://i.pinimg.com/736x/d8/b6/da/d8b6dae610b765c133df3fbbfb184993.jpg");
        linkuriImagini.add("https://i.pinimg.com/736x/04/9e/aa/049eaa500f495fd75063d89373df80a0.jpg");
        linkuriImagini.add("https://i.pinimg.com/736x/ac/21/9a/ac219a00ba17f7acd0b1701fbf75316b.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        handler.post(new Runnable() {

            @Override
            public void run() {
                lista = new ArrayList<>();
                lista.add(new ImaginiAcvariu("Magikarp", imagini.get(0), "\"https://i.pinimg.com/736x/8a/31/d6/8a31d66f47421dd868ca8e720b08209b.jpg\""));
                lista.add(new ImaginiAcvariu("Goldeen", imagini.get(0), "\"https://i.pinimg.com/736x/df/91/9f/df919fa72a97336aea27749841b1d7af.jpg\""));
                lista.add(new ImaginiAcvariu("Seaking", imagini.get(0), "\"https://i.pinimg.com/736x/d8/b6/da/d8b6dae610b765c133df3fbbfb184993.jpg\""));
                lista.add(new ImaginiAcvariu("Finneon", imagini.get(0), "\"https://i.pinimg.com/736x/04/9e/aa/049eaa500f495fd75063d89373df80a0.jpg\""));
                lista.add(new ImaginiAcvariu("Lumineon", imagini.get(0), "\"https://i.pinimg.com/736x/ac/21/9a/ac219a00ba17f7acd0b1701fbf75316b.jpg\""));
            }
        });
    }
}