package com.android_development.passwordgeneratorandmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    EditText mUname,mEmail,mPwd,mCpwd;
    TextView btn2;
    Button btn1;
  //  ProgressBar mPbar;

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

//        btn1.findViewById(R.id.btn1);
//        btn2.findViewById(R.id.btn2);
//        mUname.findViewById(R.id.uname);
//        mEmail.findViewById(R.id.email);
//        mPwd.findViewById(R.id.pwd);
//        mCpwd.findViewById(R.id.cpwd);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        mUname = findViewById(R.id.uname);
        mEmail = findViewById(R.id.email);
        mPwd = findViewById(R.id.pwd);
        mCpwd = findViewById(R.id.cpwd);

      //  mPbar.findViewById(R.id.pbar);
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
                    mPwd.setError("Password And Confirm Password Must Be Same");
                    return;
                }




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


/*
 <ProgressBar
        android:id="@+id/progressbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:progressDrawable="@drawable/pbar"
        style="?android:attr/progressBarStyleHorizontal"
        android:visibility="visible"
        android:outlineSpotShadowColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/btn2"
        app:flow_verticalBias="0.26" />
 */