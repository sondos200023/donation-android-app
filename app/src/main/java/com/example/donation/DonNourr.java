package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DonNourr extends AppCompatActivity {
    private ImageView don;
    private ImageView act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_nourr);
        act = findViewById(R.id.act);
        don = findViewById(R.id.don);

        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonNourr.this, nourriture.class);
                startActivity(intent);
            }
        });

    }
}