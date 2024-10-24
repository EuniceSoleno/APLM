package com.example.simpleimagedowloand;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity; // alterado

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements Handler.Callback{ // alterad
    public static final String KEY_HANDLER_MSG = "status";
    private static final String IMAGE_SOURCE = "https://android.com/images/froyo.png";
    private Button btnDownloadFile;
    private Button btnDownloadFileAsync;
    private TextView statusTextView;
    private ImageView imageView;
    // declare handler
    private Handler handler = new Handler((Handler.Callback) this);

    private Runnable imageDownloader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownloadFile = findViewById(R.id.btnDownloadFile);
        btnDownloadFileAsync = findViewById(R.id.btnDownloadFileAsync);
        imageView = findViewById(R.id.image_view);
        statusTextView = findViewById(R.id.status);

        // exercise 1 - step 1
        btnDownloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(imageDownloader, "Download thread").start();
                statusTextView.setText(getString(R.string.download_started));
            }
        });

        // exercise 1 - step 1
        imageDownloader = new Runnable() {
            public void run() {
                // exercise 1 - step 1
                downloadImage(IMAGE_SOURCE);
            }
        };

        // exercise 1 - step 2 to implement
        btnDownloadFileAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implementação para o download assíncrono
            }
        });

        // initialize handler
    }

    private void downloadImage(String urlStr){
        try {
            URL imageUrl = new URL(urlStr);
            Bitmap image = BitmapFactory.decodeStream(imageUrl.openStream());
            if (image != null) {
                Log.i("DL", getString(R.string.download_success));
                // Atualiza a ImageView no thread principal
                runOnUiThread(() -> imageView.setImageBitmap(image)); // Exiba a imagem baixada
            } else {
                Log.i("DL", getString(R.string.download_failed_stream));
            }
        } catch (Exception e) {
            Log.i("DL", getString(R.string.download_failed));
            e.printStackTrace();
        }
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        String text = msg.getData().getString("status");
        TextView statusText = (TextView) findViewById(R.id.status);
        statusText.setText(text);
        return true;
    }

    private void sendMessage(String what)
    {
        Bundle bundle = new Bundle();
        bundle.putString("status",what);
        Message message = new Message();
        message.setData(bundle);
        handler.sendMessage(message);


    }
}
