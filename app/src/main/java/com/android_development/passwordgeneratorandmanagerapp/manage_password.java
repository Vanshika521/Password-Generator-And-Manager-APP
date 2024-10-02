package com.android_development.passwordgeneratorandmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class manage_password extends AppCompatActivity {

    EditText pwd,desc;
    ImageButton saveBtn;
    TextView heading,dltBtn;
    String title1,content1,docId;
    Button generateBtn;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_passw);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        pwd = findViewById(R.id.pwd);
        desc = findViewById(R.id.desc);
        saveBtn = findViewById(R.id.saveBtn);

        generateBtn = findViewById(R.id.generateBtn);

        heading = findViewById(R.id.heading);
        dltBtn = findViewById(R.id.dltBtn);

        //receive data
        title1 = getIntent().getStringExtra("title");
        content1 = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        generateBtn.setVisibility(View.GONE);
        dltBtn.setVisibility(View.GONE);

        if(docId != null && !docId.isEmpty())
        {
            isEditMode = true ;
        }

        pwd.setText(title1);
        desc.setText(content1);
        if(isEditMode)
        {
            heading.setText("Edit Your Note");
            dltBtn.setVisibility(View.VISIBLE);
        }

        if(!isEditMode)
        {
            heading.setText("Add Your Note");
            generateBtn.setVisibility(View.VISIBLE);
        }

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manage_password.this,generate_pwd.class);
                startActivity(intent);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notes_title = pwd.getText().toString();
                String notes_content = desc.getText().toString();

                if(notes_title == null || notes_title.isEmpty())
                {
                    pwd.setError("Password Is Required!!!");
                    return;
                }

                note Note = new note();
                Note.setTitle(notes_title);
                Note.setContent(notes_content);
                Note.setTimestamp(Timestamp.now());
                saveNote(Note);
                Intent intent = new Intent(manage_password.this,SplashScreen.class);
                startActivity(intent);
            }

            void saveNote (note Note)
            {
                DocumentReference documentReference;
                if(isEditMode)
                {

                    //Edit Note
                    documentReference = utility.getCollectionReference().document(docId);
                }
                else
                {
                    //Create Note
                    documentReference = utility.getCollectionReference().document();
                }
                documentReference.set(Note).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            //note is successfully added......
                            finish();
                        }
                        else{
                            utility.showToast(manage_password.this,"Error Occurred While Adding Notes ");
                        }

                    }
                });
            }
        });

        dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manage_password.this,SplashScreen.class);
                startActivity(intent);
                DocumentReference documentReference;
                documentReference = utility.getCollectionReference().document(docId);
                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            //note is successfully deleted......
                            utility.showToast(manage_password.this,"Note Deleted Successfully");



                        }
                        else{
                            utility.showToast(manage_password.this,"Error Occurred While Deleting Notes ");
                        }

                    }
                });
            }
        });


    }


}
