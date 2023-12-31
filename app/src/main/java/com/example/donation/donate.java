package com.example.donation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link donate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class donate extends Fragment {
    String[] don = {"Faire un don de Matériel", "Faire un don de Nourriture", "Faire un don de Service"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public donate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment donate.
     */
    // TODO: Rename and change types and number of parameters
    public static donate newInstance(String param1, String param2) {
        donate fragment = new donate();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate, container, false);


    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Trouvez une vue en utilisant son ID
        ListView lv =view.findViewById(R.id.liste);
        Button b=view.findViewById(R.id.retour);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),Acceuil.class);
                startActivity(i1);
            }
        });
        int xml = androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item;
        ArrayAdapter adp = new ArrayAdapter<>(requireActivity(), xml, don);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Class[] activityClasse = new Class[]{
                        DonMaterial.class,
                        DonNourr.class,
                        DonService.class,

                };


                Intent intent = new Intent(requireActivity(), activityClasse[i]);
                startActivity(intent);

            }
        });
    }
}