package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
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

public class Accompagnement extends AppCompatActivity {
    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText emailEditText;
    private EditText telephoneEditText;
    private EditText descriptionEditText;
    private Spinner availabilitySpinner;
    private Button submitButton;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private CollectionReference collectionReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accompagnement);
        db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("Accompagnement");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        nomEditText = findViewById(R.id.edit_text_nom);
        prenomEditText = findViewById(R.id.edit_text_prenom);
        emailEditText = findViewById(R.id.edit_text_email);
        telephoneEditText = findViewById(R.id.edit_text_telephone);
        descriptionEditText = findViewById(R.id.editTextDescription);
        availabilitySpinner = findViewById(R.id.spinnerAvailability);
        submitButton = findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAccompaniment();
            }
        });
    }

    private void submitAccompaniment() {
        String nom = nomEditText.getText().toString().trim();
        String prenom = prenomEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String telephone = telephoneEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String availability = availabilitySpinner.getSelectedItem().toString();

        if (user == null) {
            Toast.makeText(this, "Veuillez vous connecter pour soumettre l'offre d'accompagnement", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = user.getUid();

        Map<String, Object> accompaniment = new HashMap<>();
        accompaniment.put("userId", userId);
        accompaniment.put("nom", nom);
        accompaniment.put("prenom", prenom);
        accompaniment.put("email", email);
        accompaniment.put("telephone", telephone);
        accompaniment.put("description", description);
        accompaniment.put("availability", availability);

        collectionReference.add(accompaniment)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Accompagnement .this, "Offre d'accompagnement soumise avec succès", Toast.LENGTH_SHORT).show();
                        // Réinitialiser les champs de saisie après la soumission
                        clearFields();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Accompagnement.this, "Erreur lors de la soumission de l'offre d'accompagnement", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearFields() {
        nomEditText.setText("");
        prenomEditText.setText("");
        emailEditText.setText("");
        telephoneEditText.setText("");
        descriptionEditText.setText("");
        availabilitySpinner.setSelection(0);
    }
}