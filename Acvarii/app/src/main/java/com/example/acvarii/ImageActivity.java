package com.example.acvarii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImageActivity extends AppCompatActivity {
    List<Image> imageList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        List<String> imageLink = new ArrayList<>();
        List<Bitmap> imageBitmap = new ArrayList<>();
        List<String> imageDescriere = new ArrayList<>();
        imageLink.add("https://i.pinimg.com/736x/d8/1c/0d/d81c0da291629f3667a4cc81b999d282.jpg");
        imageLink.add("https://i.pinimg.com/736x/db/c8/36/dbc8364834950405f52eb2cb24887023.jpg");
        imageLink.add("https://i.pinimg.com/736x/4f/a2/83/4fa283457d5d0605706926de0359261e.jpg");
        imageLink.add("https://i.pinimg.com/736x/be/26/f9/be26f9b76658e8e7594b0b1f2982a7d5.jpg");
        imageLink.add("https://i.pinimg.com/736x/3c/9f/e4/3c9fe493bc74f1f44ed69d5613d7a42b.jpg");

        imageDescriere.add("Goldeen");
        imageDescriere.add("Magikarp");
        imageDescriere.add("Feebas");
        imageDescriere.add("Vaporeon");
        imageDescriere.add("Seaking");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link:imageLink){
                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL(link);
                        connection = (HttpURLConnection) url.openConnection();

                        InputStream inputStream = connection.getInputStream();
                        imageBitmap.add(BitmapFactory.decodeStream(inputStream));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageList = new ArrayList<>();
                        for (int i = 0; i<imageLink.size(); i++){
                            imageList.add(new Image(imageBitmap.get(i), imageDescriere.get(i), imageLink.get(i)));
                        }

                        ListView listView = findViewById(R.id.LVImageList);
                        ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(), imageList, R.layout.image_adapter);
                        listView.setAdapter(imageAdapter);
                    }
                });
            }
        });
        
        ListView listView = findViewById(R.id.LVImageList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( getApplicationContext(), WebViewActivity.class);
                intent.putExtra("link", imageList.get(position).getLink());
                startActivity(intent);
            }
        });
    }
}