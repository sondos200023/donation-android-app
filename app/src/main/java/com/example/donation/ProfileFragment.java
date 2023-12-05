package com.example.donation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    private TextView textViewUsername;
    private TextView textViewEmail;
    private Button buttonEditProfile;
    private Button buttonLogout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        buttonEditProfile = view.findViewById(R.id.buttonEditProfile);
        buttonLogout = view.findViewById(R.id.buttonLogout);



        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Déconnecter l'utilisateur et rediriger vers l'écran de connexion
                firebaseAuth.signOut();
                Toast.makeText(getActivity(), "Déconnexion réussie", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (currentUser != null) {
            // Mettre à jour les informations de profil avec les données de l'utilisateur actuel
            String username = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            // Utiliser une bibliothèque d'image telle que Picasso ou Glide pour charger l'image de profil depuis Firebase Storage

            textViewUsername.setText(username);
            textViewEmail.setText(email);
        } else {
            // L'utilisateur n'est pas connecté, rediriger vers l'écran de connexion
            startActivity(new Intent(getActivity(), Login.class));
            getActivity().finish();
        }
    }

    }

