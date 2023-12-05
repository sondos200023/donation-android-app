package com.example.donation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Acceuil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu)));
        getSupportActionBar().setTitle("FloraDon");
        final DrawerLayout dw=findViewById(R.id.drawerLayout);
                findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dw.openDrawer(GravityCompat.START);
                    }
                });
    NavigationView nv=findViewById(R.id.navigationView);
    nv.setItemIconTintList(null);
    NavController navController= Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(nv,navController);
        TextView tv=findViewById(R.id.textTitle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                tv.setText(navDestination.getLabel());
            }
        });
    }
}