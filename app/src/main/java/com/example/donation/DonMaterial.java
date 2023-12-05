package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DonMaterial extends AppCompatActivity {
    private ImageView don,don1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_material);
        don = findViewById(R.id.don);
        don1 = findViewById(R.id.don1);

        don1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMaterial.this, material.class);
                startActivity(intent);
            }
        });
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMaterial.this, carte.class);
                startActivity(intent);
            }
        });
    }
}