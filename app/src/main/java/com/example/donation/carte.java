package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class carte extends AppCompatActivity {
    private EditText card_number;
    private EditText card_name;
    private EditText valid_through;
    private EditText cvn_code;
    private RadioButton accpeted;
    private Button addcard;
    private ProgressBar pay_progress;

    private String currentUserId;
    private String currentUserName;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;


    //Connection to Firestore
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final CollectionReference collectionReference = db.collection("PaymentCards");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);
    }
}