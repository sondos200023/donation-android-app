package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class material extends AppCompatActivity {
    private EditText Nom;
    private EditText Prenom;
    private EditText Email;
    private EditText Tel;
    private RadioGroup matr;
    private SeekBar mQuantiteSeekBar;
    private TextView mQuantiteTextView;
    private Button envoyé;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;
    private CollectionReference collectionReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        db= FirebaseFirestore.getInstance();
        collectionReference = db.collection("Donation");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        Nom = findViewById(R.id.edit_text_nom);
        Prenom = findViewById(R.id.edit_text_prenom);
        Email = findViewById(R.id.edit_text_email);
        Tel= findViewById(R.id.edit_text_telephone);
        matr = findViewById(R.id.radio_group_materiel);
        mQuantiteSeekBar = findViewById(R.id.seek_bar_quantite);
        mQuantiteTextView = findViewById(R.id.text_view_quantite);
        envoyé=findViewById(R.id.button_envoyer);
        envoyé.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDonation();

            }
        });

        mQuantiteSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mQuantiteTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        authStateListener = firebaseAuth -> {
            user = firebaseAuth.getCurrentUser();
            if(user != null){
                Log.d("userins", "onCreate: "+user.getEmail());
            }else{

            }
        };
    }

    private void saveDonation() {
        String nom = Nom.getText().toString().trim();
        String prenom = Prenom.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String tel = Tel.getText().toString().trim();
        int quantite = mQuantiteSeekBar.getProgress();
        String materialType = "";
        int selectedId = matr.getCheckedRadioButtonId();

        if(selectedId == -1){
            Toast.makeText(this, "svp sélectionné le type", Toast.LENGTH_SHORT).show();
            return;
        } else if(selectedId == R.id.radio_button_vetements) {
            materialType = "vetement";
        } else if(selectedId == R.id.radio_button_jouets) {
            materialType  = "Jouets";
        } else if(selectedId == R.id.radio_button_materiel_scolaire) {
            materialType  = "Materiels scolaire";
        }else if(selectedId == R.id.radio_button_autre                            ) {
            materialType  = "Autre";
        }

        if(TextUtils.isEmpty(nom) || TextUtils.isEmpty(prenom) || TextUtils.isEmpty(email) || TextUtils.isEmpty(tel)) {
            Toast.makeText(this, "svp entré tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> donation = new HashMap<>();
        donation.put("nom", nom);
        donation.put("prenom", prenom);
        donation.put("email", email);
        donation.put("telephone", tel);
        donation.put("materialType", materialType);
        donation.put("quantite", String.valueOf(quantite));

        db.collection("MaterialDonation")
                .add(donation)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(material.this, "Don ajouté", Toast.LENGTH_SHORT).show();
                        // startActivity(new Intent(nourriture.this, DonationListActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(material.this, "Error d'ajout de don", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        user = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);

    }
}

