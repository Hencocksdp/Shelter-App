package com.example.phoneauth;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    TextView pName,pemail,pphone;
    Toolbar toolbar;
    ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        pName = findViewById(R.id.profileFullName);
        pemail = findViewById(R.id.profileEmail);
        pphone = findViewById(R.id.profilePhone);
        imageView = findViewById(R.id.imageView);

        final DocumentReference docRef =fStore.collection("user").document(firebaseAuth.getCurrentUser().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String fullName = documentSnapshot.getString("firstName") + " " + documentSnapshot.getString("lastName");
                    String mail = documentSnapshot.getString("emailaddress");

                    pName.setText(fullName);

                    pemail.setText(mail);
                    pphone .setText(firebaseAuth.getCurrentUser().getPhoneNumber());

                }else {
                    Log.d(TAG, "Retrieving Data: Profile Data Not Found ");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Registrer.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}