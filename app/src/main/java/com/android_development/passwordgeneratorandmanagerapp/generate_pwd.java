package com.android_development.passwordgeneratorandmanagerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class generate_pwd extends AppCompatActivity {

    TextView textView1, textView2, tvPasswordLength;
    Button btn;
    CheckBox cbtnA, cbtnB, cbtnC, cbtnD;
    SeekBar seekBarLength;
    int passwordLength = 8; // Default password length

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pwd);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        tvPasswordLength = findViewById(R.id.tvPasswordLength);
        btn = findViewById(R.id.btn1);
        cbtnA = findViewById(R.id.cbtnA);
        cbtnB = findViewById(R.id.cbtnB);
        cbtnC = findViewById(R.id.cbtnC);
        cbtnD = findViewById(R.id.cbtnD);
        seekBarLength = findViewById(R.id.seekBar);

        // Set default password length in the TextView
        tvPasswordLength.setText("Password Length: " + passwordLength);

        // Set up the SeekBar listener
        seekBarLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update the password length value as the user moves the SeekBar
                passwordLength = progress;
                // Update the TextView to show the current password length
                tvPasswordLength.setText("Password Length: " + passwordLength);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // This can be left empty if no action is required when tracking starts
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // This can be left empty if no action is required when tracking stops
            }
        });

        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String characters = "";
                if (cbtnA.isChecked()) {
                    characters = lowerCase + characters;
                }

                if (cbtnB.isChecked()) {
                    characters = upperCase + characters;
                }

                if (cbtnC.isChecked()) {
                    characters = number + characters;
                }

                if (cbtnD.isChecked()) {
                    characters = specialChars + characters;
                }

                if (characters.isEmpty()) {
                    Toast.makeText(generate_pwd.this, "Please select at least one option", Toast.LENGTH_SHORT).show();
                    return;
                }

                Random random = new Random();
                StringBuilder password = new StringBuilder();

                for (int i = 0; i < passwordLength; i++) {
                    int randomIndex = random.nextInt(characters.length());
                    char randomChar = characters.charAt(randomIndex);
                    password.append(randomChar);
                }

                textView2.setText("Your Generated Password is: " + password.toString());
            }
        });
    }
}
