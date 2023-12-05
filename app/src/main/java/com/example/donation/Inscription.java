package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class Inscription extends AppCompatActivity {
private FirebaseAuth auth;
private EditText nom,prenom,email,mdp;
private Button login,ins;
private FirebaseAuth firebaseAuth;
private CollectionReference collectionReference ;
private  FirebaseFirestore db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu)));
        getSupportActionBar().setTitle("FloraDon");
        setContentView(R.layout.activity_inscription);
        auth=FirebaseAuth.getInstance();
        ins=findViewById(R.id.inscription);
        login=findViewById(R.id.inscription);
        nom=findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        email=findViewById(R.id.email);
        mdp=findViewById(R.id.mdp);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),Login.class);
                startActivity(i1);
            }
        });
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=email.getText().toString().trim();
                String pass=mdp.getText().toString().trim();
                String nom1=nom.getText().toString().trim();
                String prenom1=prenom.getText().toString().trim();
                db= FirebaseFirestore.getInstance();
                collectionReference = db.collection("Donation");
                if(user.isEmpty()){
                   email.setError("Remplir champ email");
               }
                if(pass.isEmpty()){
                    mdp.setError("Remplir champ mot de passe");
                }
                if(nom1.isEmpty()){
                    nom.setError("Remplir champ nom");
                }
                if(prenom1.isEmpty()){
                    prenom.setError("Remplir champ prenom");
                }
                else {
                    Map<String, String> donation = new HashMap<>();
                    donation.put("nom", nom1);
                    donation.put("prenom", prenom1);
                    donation.put("email", user);
                    donation.put("mdp", pass);


                    db.collection("Utilisateurs")
                            .add(donation)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Inscription.this, "Don ajouté", Toast.LENGTH_SHORT).show();
                                    // startActivity(new Intent(nourriture.this, DonationListActivity.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Inscription.this, "Error d'ajout de don", Toast.LENGTH_SHORT).show();
                                }
                            });

                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()){
                              Toast.makeText(Inscription.this, "inscription validé ", Toast.LENGTH_SHORT).show();
                              Intent i1=new Intent(Inscription.this,Login.class);
                              startActivity(i1);
                          }
                          else {
                              Toast.makeText(Inscription.this, "inscription échoué "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                          }
                        }
                    });
                }
          }
        });
    }
}