package com.example.reparapablo.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reparapablo.models.Incidencia;
import com.example.reparapablo.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CrearIncidenciaFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    private final int MAX_IMAGES = 3;
    private ArrayList<Uri> imageUris = new ArrayList<>();

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imagePickerLauncher = registerForActivityResult(
                new androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == android.app.Activity.RESULT_OK && result.getData() != null) {
                        android.content.ClipData clipData = result.getData().getClipData();
                        Uri imageUri = result.getData().getData();

                        if (clipData != null) {
                            for (int i = 0; i < clipData.getItemCount() && imageUris.size() < MAX_IMAGES; i++) {
                                imageUris.add(clipData.getItemAt(i).getUri());
                            }
                        } else if (imageUri != null && imageUris.size() < MAX_IMAGES) {
                            imageUris.add(imageUri);
                        }

                        mostrarImagenesSeleccionadas();

                        if (!imageUris.isEmpty()) {
                            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                            Uri uriParaSubir = imageUris.get(0);
                            StorageReference imageRef = storageRef.child("incidencias/imagen1.jpg");

                            imageRef.putFile(uriParaSubir)
                                    .addOnSuccessListener(taskSnapshot -> {
                                        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                                            String downloadUrl = uri.toString();
                                            Log.d("URL_IMAGEN", downloadUrl);
                                        });
                                    })
                                    .addOnFailureListener(e -> Log.e("ERROR_UPLOAD", "Error al subir imagen", e));
                        }
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crear_incidencia, container, false);

        Button btn_enviar = view.findViewById(R.id.btn_enviar);
        AutoCompleteTextView autoCompleteUbicacion = view.findViewById(R.id.autoCompleteUbicacion);
        MaterialButton btnAdjuntarImagen = view.findViewById(R.id.btnAdjuntarImagen);

        ArrayList<String> ubicaciones = null;
        try {
            ubicaciones = cargarUbicaciones();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                ubicaciones
        );

        autoCompleteUbicacion.setAdapter(adapter);
        autoCompleteUbicacion.setOnClickListener(v -> autoCompleteUbicacion.showDropDown());

        btn_enviar.setOnClickListener(v -> {
            String titulo = ((EditText) view.findViewById(R.id.tituloText)).getText().toString();
            String ubicacion = ((AutoCompleteTextView) view.findViewById(R.id.autoCompleteUbicacion)).getText().toString();
            String descripcion = ((EditText) view.findViewById(R.id.editTextDescripcion)).getText().toString();
            String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

            String uid = mauth.getCurrentUser() != null ? mauth.getCurrentUser().getUid() : "usuario-anónimo";
            FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
            String nombreUsuario = usuario.getDisplayName();

            String id = db.collection("incidencias").document().getId();
            Incidencia incidencia = new Incidencia(titulo, ubicacion, descripcion, fecha, uid);
            incidencia.setId(id);
            incidencia.setReportadoPor(nombreUsuario);
            incidencia.setEstado("Pendiente");


            db.collection("incidencias").document(id).set(incidencia)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getContext(), "Incidencia guardada", Toast.LENGTH_SHORT).show();
                        FragmentTransaction transaction = requireActivity()
                                .getSupportFragmentManager()
                                .beginTransaction();
                        requireActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        transaction.replace(R.id.fragment_container, new HomeProfesorFragment());
                        transaction.commit();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        btnAdjuntarImagen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            imagePickerLauncher.launch(intent);
        });

        return view;
    }

    private void mostrarImagenesSeleccionadas() {
        for (Uri uri : imageUris) {
            Log.d("IMAGEN_SELECCIONADA", uri.toString());
        }
    }

    private ArrayList<String> cargarUbicaciones() throws IOException {
        ArrayList<String> ubicacionesList = new ArrayList<>();
        try {
            InputStream is = getResources().openRawResource(R.raw.ubicaciones_aulas);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea;

            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] separacion = linea.split(",");
                if (separacion.length >= 2) {
                    ubicacionesList.add(separacion[1].trim());
                }
            }
            br.close();
        } catch (IOException e) {
            Log.e("CSV_ERROR", "Error al leer el archivo CSV", e);
        }
        return ubicacionesList;
    }
}
