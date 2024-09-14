package com.android_development.passwordgeneratorandmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    Animation top;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        top = AnimationUtils.loadAnimation(this,R.anim.top_anim);

        btn1.setAnimation(top);
        btn2.setAnimation(top);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            //new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //register.class for registration of user...
                Intent intent  = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}