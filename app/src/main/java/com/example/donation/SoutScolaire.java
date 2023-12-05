package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class SoutScolaire extends AppCompatActivity {
    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText emailEditText;
    private EditText telephoneEditText;
    private Spinner matiereSpinner;
    private Spinner niveauSpinner;
    private Button submitButton;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private CollectionReference collectionReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sout_scolaire);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        collectionReference = db.collection("Tutoring");

        nomEditText = findViewById(R.id.edit_text_nom);
        prenomEditText = findViewById(R.id.edit_text_prenom);
        emailEditText = findViewById(R.id.edit_text_email);
        telephoneEditText = findViewById(R.id.edit_text_telephone);
        matiereSpinner = findViewById(R.id.spinnerMatiere);
        niveauSpinner = findViewById(R.id.spinnerNiveau);
        submitButton = findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTutoringOffer();
            }
        });
    }

    private void saveTutoringOffer() {
        String nom = nomEditText.getText().toString().trim();
        String prenom = prenomEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String telephone = telephoneEditText.getText().toString().trim();
        String matiere = matiereSpinner.getSelectedItem().toString();
        String niveau = niveauSpinner.getSelectedItem().toString();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> tutoringOffer = new HashMap<>();
        tutoringOffer.put("nom", nom);
        tutoringOffer.put("prenom", prenom);
        tutoringOffer.put("email", email);
        tutoringOffer.put("telephone", telephone);
        tutoringOffer.put("matiere", matiere);
        tutoringOffer.put("niveau", niveau);

        collectionReference
                .add(tutoringOffer)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText( SoutScolaire.this, "Offre de soutien scolaire ajoutée", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( SoutScolaire.this, "Erreur lors de l'ajout de l'offre", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearFields() {
        nomEditText.setText("");
        prenomEditText.setText("");
        emailEditText.setText("");
        telephoneEditText.setText("");
        matiereSpinner.setSelection(0);
        niveauSpinner.setSelection(0);
    }
}