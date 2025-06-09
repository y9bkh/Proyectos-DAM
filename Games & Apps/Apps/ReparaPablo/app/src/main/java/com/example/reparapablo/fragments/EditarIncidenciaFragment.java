package com.example.reparapablo.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EditarIncidenciaFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    private final int MAX_IMAGES = 3;
    private ArrayList<Uri> imageUris = new ArrayList<>();

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    private Incidencia incidencia;

    public static EditarIncidenciaFragment newInstance(Incidencia incidencia) {
        EditarIncidenciaFragment fragment = new EditarIncidenciaFragment();
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
                    }
                }
        );
    }

    private void mostrarImagenesSeleccionadas() {
        for (Uri uri : imageUris) {
            Log.d("IMAGEN_SELECCIONADA", uri.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_incidencia, container, false);

        Button btn_editar = view.findViewById(R.id.btn_Editar);
        MaterialButton btnAdjuntarImagen = view.findViewById(R.id.btnAdjuntarImagenEditar);

        EditText editTexttitulo = view.findViewById(R.id.tituloTextEditar);
        AutoCompleteTextView autoCompleteUbicacion = view.findViewById(R.id.autoCompleteUbicacionEditar);
        EditText editTextDescripcion = view.findViewById(R.id.editTextDescripcionEditar);

        if (incidencia != null) {
            editTexttitulo.setText(incidencia.getTitulo());
            autoCompleteUbicacion.setText(incidencia.getUbicacion());
            editTextDescripcion.setText(incidencia.getDescripcion());
        }

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

        // BOTONES
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View currentView = getView();
                if (currentView == null) {
                    Toast.makeText(getContext(), "No se pudo acceder a la vista actual", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Obtener la incidencia desde los argumentos del fragmento
                Incidencia incidencia = (Incidencia) getArguments().getSerializable("incidencia");
                if (incidencia == null) {
                    Toast.makeText(getContext(), "No se pudo obtener la incidencia para editar", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Obtener datos actualizados desde los campos de texto
                String titulo = ((EditText) currentView.findViewById(R.id.tituloTextEditar)).getText().toString().trim();
                String ubicacion = ((AutoCompleteTextView) currentView.findViewById(R.id.autoCompleteUbicacionEditar)).getText().toString().trim();
                String descripcion = ((EditText) currentView.findViewById(R.id.editTextDescripcionEditar)).getText().toString().trim();
                String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()); // Fecha de última modificación

                // Validar que el título no esté vacío
                if (titulo.isEmpty()) {
                    Toast.makeText(getContext(), "El título es obligatorio", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Actualizar los datos
                Map<String, Object> datosActualizados = new HashMap<>();
                datosActualizados.put("titulo", titulo);
                datosActualizados.put("ubicacion", ubicacion);
                datosActualizados.put("descripcion", descripcion);
                datosActualizados.put("fecha", fecha);

                db.collection("incidencias").document(incidencia.getId())
                        .update(datosActualizados)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(getContext(), "Incidencia actualizada correctamente", Toast.LENGTH_SHORT).show();

                            FragmentTransaction transaction = requireActivity()
                                    .getSupportFragmentManager()
                                    .beginTransaction();
                            transaction.replace(R.id.fragment_container, new HomeProfesorFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(getContext(), "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });


        btnAdjuntarImagen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            imagePickerLauncher.launch(intent);
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

    private ArrayList<String> cargarUbicaciones() throws IOException {
        ArrayList<String> ubicacionesList = new ArrayList<>();
        try{

            InputStream is = getResources().openRawResource(R.raw.ubicaciones_aulas);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea;

            br.readLine();

            while((linea = br.readLine()) != null) {
                String[] separacion = linea.split(",");
                if(separacion.length >= 2){
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
