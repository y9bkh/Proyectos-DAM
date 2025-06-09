package com.example.reparapablo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reparapablo.R;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarFragment extends Fragment {

    private FirebaseAuth mAuth;

    public RecuperarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance(); // Inicializa FirebaseAuth
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recuperar, container, false);

        EditText emailRecoveryText = view.findViewById(R.id.login_emailrecovery);
        Button btn_continuarRecovery = view.findViewById(R.id.btn_recuperar);

        btn_continuarRecovery.setOnClickListener(v-> {
            String email = emailRecoveryText.getText().toString().trim();

            // Validaciones
            if (email.isEmpty()) {
                emailRecoveryText.setError("Debes introducir un correo electrónico.");
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailRecoveryText.setError("Introduce un correo electrónico válido.");
                return;
            }

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(getActivity(),task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Se ha enviado un correo para restablecer la contraseña.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Error al enviar correo: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

        return view;
    }
}