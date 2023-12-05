package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DonService extends AppCompatActivity {
    private ImageView don,don1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_service);
        don = findViewById(R.id.don);
        don1 = findViewById(R.id.don1);

        don1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonService.this, SoutScolaire.class);
                startActivity(intent);
            }
        });
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonService.this, Accompagnement.class);
                startActivity(intent);
            }
        });
    }
}