package com.android_development.passwordgeneratorandmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText email,pwd;
    Button btn1;
    TextView btn2;
    ProgressBar pbar;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);

//        pbar.findViewById(R.id.pbar);

        fauth = FirebaseAuth.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = email.getText().toString().trim();
                String password = pwd.getText().toString().trim();

                if(TextUtils.isEmpty(mail)){
                    email.setError("Email Is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    pwd.setError("Password Is Required");
                    return;
                }
             //   pbar.setVisibility(View.VISIBLE);

                fauth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(login.this,"LOGIN SUCCESSFULLY DONE!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),SplashScreen.class));
                        }

                        else {
                            Toast.makeText(login.this,"ERROR!!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//                            pbar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);
            }
        });

    }
}