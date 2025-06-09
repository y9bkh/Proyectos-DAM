package com.example.reparapablo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reparapablo.R;
import com.example.reparapablo.models.Incidencia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;


public class VerIncidenciaFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    private Incidencia incidencia;

    public static VerIncidenciaFragment newInstance(Incidencia incidencia) {
        VerIncidenciaFragment fragment = new VerIncidenciaFragment();
        Bundle args = new Bundle();
        args.putSerializable("incidencia", (Serializable) incidencia);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            incidencia = (Incidencia) getArguments().getSerializable("incidencia");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_incidencia, container, false);

        TextView editTexttitulo = view.findViewById(R.id.tituloTextIncidencia);
        TextView autoCompleteUbicacion = view.findViewById(R.id.autoCompleteUbicacionIncidencia);
        TextView editTextDescripcion = view.findViewById(R.id.editTextDescripcionIncidencia);

        if (incidencia != null) {
            editTexttitulo.setText(incidencia.getTitulo());
            autoCompleteUbicacion.setText(incidencia.getUbicacion());
            editTextDescripcion.setText(incidencia.getDescripcion());
        }

        return view;
    }
}