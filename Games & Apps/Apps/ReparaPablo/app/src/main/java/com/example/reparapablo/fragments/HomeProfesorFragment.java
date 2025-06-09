package com.example.reparapablo.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reparapablo.models.Incidencia;
import com.example.reparapablo.R;
import com.example.reparapablo.adapters.IncidenciaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeProfesorFragment extends Fragment {
    private RecyclerView recyclerView;
    private IncidenciaAdapter adapter;
    private List<Incidencia> listaIncidencias = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_profesor, container, false);

        // Variables
        TextView saludoText = view.findViewById(R.id.saludoUsuarioText);
        TextView fechaText = view.findViewById(R.id.textFecha);
        TextView contadorActivas = view.findViewById(R.id.contadorActivas);
        TextView contadorResueltas = view.findViewById(R.id.contadorResueltas);
        TextView subtituloActivas = view.findViewById(R.id.subtituloActivas);
        TextView subtituloResueltas = view.findViewById(R.id.subtituloResueltas);

        recyclerView = view.findViewById(R.id.recyclerIncidencias);

        FloatingActionButton btn_addIncidencia = view.findViewById(R.id.addIncidencia);
        Button btn_filtrar = view.findViewById(R.id.filtrarProfesor);
        Button btn_ordenar = view.findViewById(R.id.ordenarProfesor);
        EditText buscador = view.findViewById(R.id.buscadorProfesor);

        RelativeLayout relativeActivas = view.findViewById(R.id.relativeActivasProfesor);
        RelativeLayout relativeResueltas = view.findViewById(R.id.relativeResueltasProfesor);

        // Recuperamos el nombre del usuario, para que aparezca en pantalla al logearse.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String nombreUsuario = user.getDisplayName();

        if(user!=null) {
            user.getDisplayName();
            if(nombreUsuario!=null && !nombreUsuario.isEmpty()) {
                saludoText.setText("Hola " + nombreUsuario);
            }
        }

        //Obtenemos la fecha del día de hoy, para que aparezca en pantalla al logearse.
        Locale locale = new Locale("es", "Es");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", locale);
        final String fecha = dateFormat.format(date);

        final String fechaFormateada = fecha.substring(0, 1).toUpperCase() + fecha.substring(1);

        fechaText.setText(fechaFormateada);

        // Contador
        db.collection("incidencias")
                .addSnapshotListener((querySnapshot, error) -> {
                    int incidenciaActiva = 0;
                    int incidenciaResuelta = 0;

                    int incidenciaActivaHoy = 0;
                    int incidenciaResueltaHoy = 0;


                    if (querySnapshot != null) {
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                        for(DocumentSnapshot doc : querySnapshot) {
                            Incidencia incidencia = doc.toObject(Incidencia.class);
                            if (incidencia != null) {
                                String fechaAlmacenadaBBDD = incidencia.getFecha();

                                try {
                                    Date fechaIncidencia = formatoFecha.parse(fechaAlmacenadaBBDD);

                                    SimpleDateFormat formatoFechaSinHora = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                    String incidenciasFechasHoy = formatoFechaSinHora.format(new Date());
                                    String fechaIncidenciaSoloFecha = formatoFechaSinHora.format(fechaIncidencia);

                                    if ("Resuelto".equalsIgnoreCase(incidencia.getEstado())) {
                                        incidenciaResuelta++;
                                        if (incidenciasFechasHoy.equals(fechaIncidenciaSoloFecha)) {
                                            incidenciaResueltaHoy++;
                                        }
                                    } else if ("En proceso".equalsIgnoreCase(incidencia.getEstado())) {
                                        incidenciaActiva++;
                                        if (incidenciasFechasHoy.equals(fechaIncidenciaSoloFecha)) {
                                            incidenciaActivaHoy++;
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace(); // O manejar el error si el formato es incorrecto
                                }
                            }
                        }
                    }

                    contadorActivas.setText(String.valueOf(incidenciaActiva));
                    contadorResueltas.setText(String.valueOf(incidenciaResuelta));

                    subtituloActivas.setText(String.valueOf(incidenciaActivaHoy) + " activas hoy");
                    subtituloResueltas.setText(String.valueOf(incidenciaResueltaHoy) + " resueltas hoy");
                });


        // Botones
        btn_addIncidencia.setOnClickListener(v-> {
            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.fragment_container, new CrearIncidenciaFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        relativeActivas.setOnClickListener(v -> {
            db.collection("incidencias")
                    .addSnapshotListener((querySnapshot, error) ->{
                        if(querySnapshot != null) {
                            listaIncidencias.clear();
                            for (QueryDocumentSnapshot doc : querySnapshot) {
                                Incidencia incidencia = doc.toObject(Incidencia.class);
                                incidencia.setId(doc.getId());

                                if("En proceso".equalsIgnoreCase(incidencia.getEstado())) {
                                    listaIncidencias.add(incidencia);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
        });

        relativeResueltas.setOnClickListener(v -> {
            db.collection("incidencias")
                    .addSnapshotListener((querySnapshot, error) ->{
                        if(querySnapshot != null) {
                            listaIncidencias.clear();
                            for (QueryDocumentSnapshot doc : querySnapshot) {
                                Incidencia incidencia = doc.toObject(Incidencia.class);
                                incidencia.setId(doc.getId());

                                if("Resuelto".equalsIgnoreCase(incidencia.getEstado())) {
                                    listaIncidencias.add(incidencia);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
        });

        btn_filtrar.setOnClickListener(v -> {
            String buscadortexto = buscador.getText().toString().trim();

            db.collection("incidencias")
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        listaIncidencias.clear();
                        for (QueryDocumentSnapshot doc : querySnapshot) {
                            Incidencia incidencia = doc.toObject(Incidencia.class);
                            if(incidencia.getTitulo().contains(buscadortexto)) {
                                listaIncidencias.add(incidencia);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Error al buscar incidencias", Toast.LENGTH_SHORT).show();
                    });
        });

        btn_ordenar.setOnClickListener(v -> {
            db.collection("incidencias")
                    .orderBy("fecha")
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        listaIncidencias.clear();
                        for (QueryDocumentSnapshot doc : querySnapshot) {
                            Incidencia incidencia = doc.toObject(Incidencia.class);
                            listaIncidencias.add(incidencia);
                        }
                        adapter.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Error al cargar incidencias", Toast.LENGTH_SHORT).show();
                    });
        });



        // Cargamos las incidencias creadas en pantalla.
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new IncidenciaAdapter(listaIncidencias, false, getParentFragmentManager());
        recyclerView.setAdapter(adapter);

        cargarIncidenciasDesdeFirestore();

        return view;
    }

    private void cargarIncidenciasDesdeFirestore() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return;

        String profesorId = user.getUid();

        db.collection("incidencias")
                .whereEqualTo("uid", profesorId)
                .addSnapshotListener((querySnapshot, error) ->{
                    if (error != null) {
                        Toast.makeText(getContext(), "Error al cargar incidencias", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (querySnapshot != null) {
                        listaIncidencias.clear();
                        for (QueryDocumentSnapshot doc : querySnapshot) {
                            Incidencia incidencia = doc.toObject(Incidencia.class);
                            incidencia.setId(doc.getId());
                            listaIncidencias.add(incidencia);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}