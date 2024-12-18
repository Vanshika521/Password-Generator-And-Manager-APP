package com.android_development.passwordgeneratorandmanagerapp;
import android.content.Context;
import android.widget.Toast;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class utility {
    static void showToast(Context context, String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    static CollectionReference getCollectionReference()
    {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("passwords").document(currentUser.getUid()).collection("pwd");
    }
    public static String timestampToString(Timestamp timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(timestamp.toDate());
    }
}

