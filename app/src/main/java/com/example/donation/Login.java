package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
private FirebaseAuth auth;
private EditText email,mdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu)));
        getSupportActionBar().setTitle("FloraDon");
        email=findViewById(R.id.email);
        mdp=findViewById(R.id.mdp);
        Button ins=findViewById(R.id.inscription);
        Button log=findViewById(R.id.loginButton);
        auth=FirebaseAuth.getInstance();

      log.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email1=email.getText().toString();
        String pass=mdp.getText().toString();
        if(!email1.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            if(!pass.isEmpty()){
                auth.signInWithEmailAndPassword(email1,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Login.this, "Login validé ", Toast.LENGTH_SHORT).show();
                        Intent i1=new Intent(Login.this,Acceuil.class);
                        startActivity(i1);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Login échoué ", Toast.LENGTH_SHORT).show();

                    }
                });
            }else {
                mdp.setError("Rempli champ mot de passe");
            }
        } else if (email1.isEmpty()) {
            email.setError("Rempli champ email");

        }else {
            email.setError("Svp entré un email valide");

        }


    }
});

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),Inscription.class);
                startActivity(i1);

            }
        });


    }
}