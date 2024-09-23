package com.android_development.passwordgeneratorandmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;
    Animation blur,top;
    TextView txtView;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        img = findViewById(R.id.img);
        txtView = findViewById(R.id.txtView);

        blur = AnimationUtils.loadAnimation(this,R.anim.blur);
        top = AnimationUtils.loadAnimation(this,R.anim.top);

        txtView.setAnimation(blur);
        img.startAnimation(top);
        btn1.setAnimation(top);
        btn2.setAnimation(top);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,learn_more.class);
                startActivity(intent);
            }
        });

//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            //new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //register.class for registration of user...
//                Intent intent  = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },10000);


    }
}
