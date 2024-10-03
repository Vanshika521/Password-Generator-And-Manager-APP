package com.android_development.passwordgeneratorandmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

public class SplashScreen extends AppCompatActivity {

    FloatingActionButton fBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;
    adapter Adapter;  // Renamed variable to follow Java naming conventions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        menuBtn = findViewById(R.id.menuBtn);
        recyclerView = findViewById(R.id.recycleView);
        fBtn = findViewById(R.id.add_notes);

        fBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SplashScreen.this, manage_password.class);
            startActivity(intent);
        });

        menuBtn.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(SplashScreen.this, menuBtn);
            popupMenu.getMenu().add("Logout");
            popupMenu.getMenu().add("Generate");
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getTitle().equals("Logout")) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(SplashScreen.this, login.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                if(menuItem.getTitle().equals("Generate")){
                    Intent intent = new Intent(SplashScreen.this, generate_pwd.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            });
        });

        setupRecyclerView();
    }

    void setupRecyclerView() {
        Query query = utility.getCollectionReference().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<note> options = new FirestoreRecyclerOptions.Builder<note>().setQuery(query, note.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter = new adapter(options, this);  // Instantiating the inner Adapter class
        recyclerView.setAdapter(Adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Adapter.notifyDataSetChanged();
    }



}
