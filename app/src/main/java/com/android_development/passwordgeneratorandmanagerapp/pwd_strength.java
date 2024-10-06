package com.android_development.passwordgeneratorandmanagerapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pwd_strength extends AppCompatActivity {

    TextView textView1, textView2;
    EditText pwd;
    Button btn;
    Animation top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pwd_strength);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView1 = findViewById(R.id.textView1);
        pwd = findViewById(R.id.pwd);
        btn = findViewById(R.id.btn);
        textView2 = findViewById(R.id.textView2);
        top = AnimationUtils.loadAnimation(this,R.anim.top);

        textView2.setAnimation(top);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = pwd.getText().toString().trim();

                if (password.isEmpty()) {
                    Toast.makeText(pwd_strength.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                } else if (password.matches(".*[A-Z].*") && // At least one uppercase letter
                        password.matches(".*[a-z].*") && // At least one lowercase letter
                        password.matches(".*[0-9].*") &&  // At least one digit
                        password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?.].*")) {
                    textView2.setText("🔐 Master-Level Security! 💪");
                } else if (password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*[0-9].*") ||
                        password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?.].*") ||
                        password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?.].*") ||
                        password.matches(".*[a-z].*") && password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?.].*")) {
                    textView2.setText("🔒 You're well-protected! 👍");
                } else if (password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") ||
                        password.matches(".*[a-z].*") && password.matches(".*[0-9].*") ||
                        password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?.].*") ||
                        password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?.].*") ||
                        password.matches(".*[a-z].*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?.].*") ||
                        password.matches(".*[a-z].*") && password.matches(".*[0-9].*")) {
                    textView2.setText("⚠️ Decent, but could be stronger! 🛠️");
                } else {
                    textView2.setText("❗ Exposed to Danger! 🛑");
                }
            }
        });
    }
}
