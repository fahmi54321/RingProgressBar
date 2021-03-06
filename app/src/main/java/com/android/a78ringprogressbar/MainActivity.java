package com.android.a78ringprogressbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class MainActivity extends AppCompatActivity {


    RingProgressBar ringProgressBar1,ringProgressBar2;
    int progress = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what==0){
                if (progress<100){
                    progress++;
                    ringProgressBar1.setProgress(progress);
                    ringProgressBar2.setProgress(progress);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ringProgressBar1 = findViewById(R.id.progress_bar_1);
        ringProgressBar2 = findViewById(R.id.progress_bar_2);

        ringProgressBar1.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_SHORT).show();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    try {
                        Thread.sleep(100);
                        handler.sendEmptyMessage(0);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}