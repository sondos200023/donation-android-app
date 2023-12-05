package com.example.donation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Acceuill#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Acceuill extends Fragment {
    private FirebaseAuth auth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Acceuill() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Acceuill.
     */        private FirebaseAuth firebaseAuth;

    // TODO: Rename and change types and number of parameters
    public static Acceuill newInstance(String param1, String param2) {
        Acceuill fragment = new Acceuill();
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

    ///


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            firebaseAuth = FirebaseAuth.getInstance();
            View view = inflater.inflate(R.layout.fragment_acceuill, container, false);
            MaterialCardView decnx=view.findViewById(R.id.cardLogout);
                    decnx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            firebaseAuth.signOut();
                            Toast.makeText(getActivity(), "Déconnexion réussie", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), Login.class));
                            getActivity().finish();
                        }
                    });
            MaterialCardView donateCard = view.findViewById(R.id.cardDonate);
            donateCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Add donation functionality here
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new donate());
                    transaction.commit();
                }
            });

            MaterialCardView profileCard = view.findViewById(R.id.cardProfile);
            profileCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     ProfileFragment p=new ProfileFragment();
                    // TODO: Navigate to profile fragment here
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, p);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            MaterialCardView helpCard = view.findViewById(R.id.cardhelp);
            helpCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Help h = new Help();
                    // TODO: Add help functionality here
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,  h);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            MaterialCardView homeCard = view.findViewById(R.id.cardacc);
            homeCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Navigate to home fragment here
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new Acceuill());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            MaterialCardView aboutUsCard = view.findViewById(R.id.cardAboutus);
            aboutUsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Navigate to about us fragment here
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new AboutUs());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            return view;
        }
}