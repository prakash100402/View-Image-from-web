package com.example.downloadwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
ImageView img;

  public void download(View view)
  {
      downloadimg task = new downloadimg();
      Bitmap image;

     try {
         image = task.execute("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw3iIiDJQW4HYJk2osK19O3RQ4Hs4MDSF8PQ&usqp=CAU").get();
         img.setImageBitmap(image);
     }
     catch (Exception e)
     {
         e.printStackTrace();
     }
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView);


    }

    public class downloadimg extends AsyncTask<String,Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {
           try {
               URL url = new URL(urls[0]);
               HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               connection.connect();
               InputStream in = connection.getInputStream();
               Bitmap bitmap = BitmapFactory.decodeStream(in);
               return bitmap;
           }
           catch (Exception e)
           {
               e.printStackTrace();
               return null;
           }
        }
    }

}