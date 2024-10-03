package com.android_development.passwordgeneratorandmanagerapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class generate_pwd extends AppCompatActivity {

    TextView textView1, textView2, tvPasswordLength;
    Button btn;
    ImageButton copy;
    CheckBox cbtnA, cbtnB, cbtnC, cbtnD;
    SeekBar seekBarLength;
    int passwordLength = 8; // Default password length
    ClipboardManager clipboardManager; // Declare ClipboardManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pwd);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        tvPasswordLength = findViewById(R.id.pwdLength);

        btn = findViewById(R.id.btn1);
        cbtnA = findViewById(R.id.cbtnA);
        cbtnB = findViewById(R.id.cbtnB);
        cbtnC = findViewById(R.id.cbtnC);
        cbtnD = findViewById(R.id.cbtnD);
        copy = findViewById(R.id.copy);

        seekBarLength = findViewById(R.id.seekBar);

        // Initialize the ClipboardManager
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        // Set default password length in the TextView
        tvPasswordLength.setText("Password Length: " + passwordLength);

        // Set up the SeekBar listener
        seekBarLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                passwordLength = progress;
                tvPasswordLength.setText("Password Length: " + passwordLength);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
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
                    characters += lowerCase;
                }
                if (cbtnB.isChecked()) {
                    characters += upperCase;
                }
                if (cbtnC.isChecked()) {
                    characters += number;
                }
                if (cbtnD.isChecked()) {
                    characters += specialChars;
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

                textView2.setText("Your Generated Password is: \n" + password.toString());
            }
        });

        // Copy the generated password when the copy button is clicked
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordText = textView2.getText().toString();

                if (!passwordText.isEmpty()) {
                    // Remove the "Your Generated Password is:" part from the textView2 content
                    String passwordToCopy = passwordText.replace("Your Generated Password is: \n", "");

                    // Copy the password to clipboard
                    ClipData clip = ClipData.newPlainText("Password", passwordToCopy);
                    clipboardManager.setPrimaryClip(clip);

                    Toast.makeText(generate_pwd.this, "Password copied to clipboard", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(generate_pwd.this, "No password to copy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
