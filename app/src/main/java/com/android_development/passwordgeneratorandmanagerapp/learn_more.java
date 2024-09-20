package com.android_development.passwordgeneratorandmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class learn_more extends AppCompatActivity {

    TextView textView1,textView2;
    Button btn1,btn2;

    String title[]={"Abc1","Abc2"};
    String description[]={"def1","def2"};

    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_learn_more);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        textView1 = findViewById(R.id.textView1);
        textView2 =findViewById(R.id.textView2);

        textView1.setText(title[flag]);
        textView2.setText(description[flag]);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag++;
                if (flag >= title.length) {
                    flag = 0; // back to the first question
                }

                // Set the text of TextViews
                textView1.setText(title[flag]);
                textView2.setText(description[flag]);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(learn_more.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}