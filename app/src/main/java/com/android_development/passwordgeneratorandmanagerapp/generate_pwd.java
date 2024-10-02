package com.android_development.passwordgeneratorandmanagerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class generate_pwd extends AppCompatActivity {

TextView textView1,textView2;
Button btn;
CheckBox cbtnA,cbtnB,cbtnC,cbtnD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  EdgeToEdge.enable(this);
        setContentView(R.layout.activity_generate_pwd);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        btn = findViewById(R.id.btn1);

        cbtnA = findViewById(R.id.cbtnA);
        cbtnB = findViewById(R.id.cbtnB);
        cbtnC = findViewById(R.id.cbtnC);
        cbtnD = findViewById(R.id.cbtnD);


        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";


        String characters = "";
        if (cbtnA.isSelected()) {
            characters = lowerCase+characters;  }

        if (cbtnB.isSelected())     {
            characters = upperCase+characters;  }

        if (cbtnC.isSelected()) {
            characters = number+characters; }

        if (cbtnD.isSelected()) {
            characters = specialChars+characters; }

        // If no character type is selected, show an error message
        if (characters.isEmpty()) {

        }

    }
}