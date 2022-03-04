package com.example.phoneauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText firstName,lastName,email;
    Button saveBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.emailAddress);
        saveBtn = findViewById(R.id.saveBtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        final DocumentReference docRef= fStore.collection("user").document(userID);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firstName.getText().toString().isEmpty() && !lastName.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){
                    String first = firstName.getText().toString();
                    String last = lastName.getText().toString();
                    String userEmail = email.getText().toString();

                    Map<String,Object> user =new HashMap<>();
                    user.put("firstName",first);
                    user.put("lastName",last);
                    user.put("emailaddress",userEmail);

                    docRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(Details.this,"Data is not Inserted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

                DocumentReference docRef = fStore.collection("users").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("first",firstName.getText().toString());
                user.put("last",lastName.getText().toString());
                user.put("email",email.getText().toString());

                //add user to database
                docRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: User Profile Created." + userID);
                        startActivity(new Intent(getApplicationContext(),Scrollactivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: Failed to Create User " + e.toString());
                    }
                });
            }
        });
    }
}