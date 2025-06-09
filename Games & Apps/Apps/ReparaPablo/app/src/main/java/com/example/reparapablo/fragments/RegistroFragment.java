package com.example.reparapablo.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.reparapablo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroFragment extends Fragment {

    private FirebaseAuth mAuth;

    public RegistroFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance(); // Inicializa FirebaseAuth
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        AutoCompleteTextView autoCompleteRol = view.findViewById(R.id.autoCompleteRol);
        EditText nombreText = view.findViewById(R.id.register_nombre);
        EditText emailText = view.findViewById(R.id.register_email);
        EditText passwordText = view.findViewById(R.id.register_password);
        EditText passwordConfirmText = view.findViewById(R.id.register_password2);
        Button btn_register = view.findViewById(R.id.btn_register);
        TextView btn_txtInicia = view.findViewById(R.id.btn_textLogin);

        String[] roles = {"Técnico", "Profesor"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                roles
        );

        autoCompleteRol.setAdapter(adapter);
        autoCompleteRol.setOnClickListener(v -> autoCompleteRol.showDropDown());

        btn_register.setOnClickListener(v -> {
            String nombre = nombreText.getText().toString().trim();
            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();
            String passwordConfirm = passwordConfirmText.getText().toString().trim();
            String rol = autoCompleteRol.getText().toString().trim();

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
            if (!password.equals(passwordConfirm)) {
                passwordConfirmText.setError("Las contraseñas no coinciden.");
                return;
            }
            if (rol.isEmpty()) {
                Toast.makeText(getContext(), "Debes seleccionar un rol.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Registro con Firebase
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            Map<String, Object> userData = new HashMap<>();
                            userData.put("uid", user.getUid());
                            userData.put("nombre", nombre);
                            userData.put("email", email);
                            userData.put("password", password);
                            userData.put("rol", rol);

                            db.collection("usuarios").document(user.getUid())
                                    .set(userData)
                                    .addOnSuccessListener(unused -> {
                                        // Ya lo manejas con el fragment Login, así que no necesitas hacer nada aquí
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(getContext(), "Error al guardar datos del usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });

                            if (user != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nombre)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(profileTask -> {
                                            if (profileTask.isSuccessful()) {
                                                Toast.makeText(getContext(), "Registro correcto. ¡Bienvenido " + nombre + "!", Toast.LENGTH_SHORT).show();


                                                FragmentTransaction transaction = requireActivity()
                                                        .getSupportFragmentManager()
                                                        .beginTransaction();
                                                transaction.replace(R.id.fragment_container, new LoginFragment());
                                                transaction.addToBackStack(null);
                                                transaction.commit();
                                            } else {
                                                Toast.makeText(getContext(), "Error al guardar el nombre de usuario.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }

                    });
        });

        btn_txtInicia.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.fragment_container, new LoginFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }
}