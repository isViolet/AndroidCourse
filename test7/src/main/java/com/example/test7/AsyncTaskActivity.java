package com.example.test7;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskActivity extends Activity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private String url = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=10" +
            "0&size=b4000_4000&sec=1585808662&di=1e69b3ae446f2af31fc5c9d4b0d78c0b&src=http://a3.att." +
            "hudong.com/14/75/01300000164186121366756803686.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        imageView = findViewById(R.id.view);
        progressBar = findViewById(R.id.progress);
        ImageDownloadTask task = new ImageDownloadTask();
        task.execute(url);
    }

    class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            String url = strings[0];
            URLConnection urlConnection;
            InputStream inputStream;
            try{
                urlConnection = new URL(url).openConnection();
                inputStream = urlConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                inputStream.close();
                bufferedInputStream.close();
            }catch (Exception e){
                e.getStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
            Toast.makeText(getApplicationContext(),"asd",Toast.LENGTH_LONG).show();
        }
    }
}


