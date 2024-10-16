package com.example.stars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.stars.ListActivity;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); 

        ImageView logo = findViewById(R.id.logo);

        logo.animate().rotation(360f).setDuration(2000); 
        logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000); 
        logo.animate().translationYBy(1000f).setDuration(2000); 
        logo.animate().alpha(0f).setDuration(6000); 

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    // Pause de 5 secondes
                    sleep(5000);

                    Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                    startActivity(intent);

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace(); 
                }
            }
        };
        t.start(); // Démarrer le thread
    }
}
