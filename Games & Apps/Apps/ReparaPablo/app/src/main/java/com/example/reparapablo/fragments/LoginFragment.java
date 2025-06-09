package com.example.reparapablo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.reparapablo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import android.widget.Toast;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;

    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();  // Inicializa FirebaseAuth
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText emailText = view.findViewById(R.id.login_email);
        EditText passwordText = view.findViewById(R.id.login_password);

        TextView btn_textrecuperar = view.findViewById(R.id.btn_textrecuperar);
        TextView btn_textRegistra = view.findViewById(R.id.btn_textRegister);

        Button btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(v -> {
            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();

            // Validaciones
            if (email.isEmpty()) {
                emailText.setError("Debes introducir un correo electrónico.");
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailText.setError("Introduce un correo electrónico válido.");
                return;
            }
            if (password.isEmpty()) {
                passwordText.setError("Debes introducir una contraseña.");
                return;
            } else if (password.length() < 6) {
                passwordText.setError("La contraseña debe tener al menos 6 caracteres.");
                return;
            }

            // Login con Firebase
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            comprobarRolUsuario(user.getUid());
                            Toast.makeText(getContext(), "Login correcto. ¡Bienvenido!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error en login: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        btn_textRegistra.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.fragment_container, new RegistroFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btn_textrecuperar.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.fragment_container, new RecuperarFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }

    private void comprobarRolUsuario(String uid) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("usuarios").document(uid).get()
                .addOnSuccessListener(document -> {
                   if(document.exists()) {
                       String rol = document.getString("rol");
                       if("Técnico".equals(rol)) {
                           FragmentTransaction transaction = requireActivity()
                                   .getSupportFragmentManager()
                                   .beginTransaction();
                           transaction.replace(R.id.fragment_container, new HomeTecnicoFragment());
                           transaction.addToBackStack(null);
                           transaction.commit();
                       } else if("Profesor".equals(rol)) {
                           FragmentTransaction transaction = requireActivity()
                                   .getSupportFragmentManager()
                                   .beginTransaction();
                           transaction.replace(R.id.fragment_container, new HomeProfesorFragment());
                           transaction.addToBackStack(null);
                           transaction.commit();
                       }
                   }
                });
    }
}
