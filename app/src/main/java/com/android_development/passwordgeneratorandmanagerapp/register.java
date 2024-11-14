package com.android_development.passwordgeneratorandmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mUname,mEmail,mPwd,mCpwd;
    TextView btn2;
    Button btn1;
    ProgressBar pbar;

    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        mUname = findViewById(R.id.uname);
        mEmail = findViewById(R.id.email);
        mPwd = findViewById(R.id.pwd);
        mCpwd = findViewById(R.id.cpwd);

//        pbar.findViewById(R.id.pbar);
        fauth = FirebaseAuth.getInstance();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String uname = mUname.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                String pwd = mPwd.getText().toString().trim();
                String cpwd = mCpwd.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email id is required");
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mEmail.setError("Enter a Valid Email-id");
                }

                if(TextUtils.isEmpty(uname)){
                    mUname.setError("Username is required");
                    return;
                }

                if(TextUtils.isEmpty(pwd)){
                    mPwd.setError("Password is required");
                    return;
                }

                if(pwd.length()<6){
                    mPwd.setError("Password Must be 6 Characters Long");
                    return;
                }

                if(TextUtils.isEmpty(cpwd)){
                    mCpwd.setError("Confirm Password is required");
                    return;
                }

                if(!pwd.equals(cpwd)){
                    mCpwd.setError("Password And Confirm Password Must Be Same");
                    return;
                }

//                pbar.setVisibility(View.VISIBLE);

                fauth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser fuser = fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                //Success (Account Created) Failure (Mail Not Sent)
                                public void onSuccess(Void unused) {
                                    Toast.makeText(register.this, "REGISTRATION SUCCESSFULLY COMPLETED!!!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());

                                }
                            });

                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this, "ERROR!!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this,login.class);
                startActivity(intent);
            }
        });
    }

}


