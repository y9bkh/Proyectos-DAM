package com.example.reparapablo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reparapablo.models.Incidencia;
import com.example.reparapablo.R;
import com.example.reparapablo.adapters.IncidenciaAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeTecnicoFragment extends Fragment {
    private RecyclerView recyclerView;
    private IncidenciaAdapter adapter;
    private List<Incidencia> listaIncidencias = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_tecnico, container, false);

        // Variables
        Button btn_filtrar = view.findViewById(R.id.filtrarTecnico);
        Button btn_ordenar = view.findViewById(R.id.ordenarTecnico);
        Button btn_verSinAsignar = view.findViewById(R.id.verSinAsignar);

        RelativeLayout relativeAsignadas = view.findViewById(R.id.relativeAsignadas);
        RelativeLayout relativeResueltas = view.findViewById(R.id.relativeResueltas);
        RelativeLayout relativeSinAsignar = view.findViewById(R.id.relativeSinAsignar);

        TextView buscador = view.findViewById(R.id.buscadorTecnico);
        TextView contadorActivas = view.findViewById(R.id.contadorActivasTecnico);
        TextView contadorResueltas = view.findViewById(R.id.contadorResueltasTecnico);
        TextView contadorSinAsignar = view.findViewById(R.id.contadorSinAsignarTecnico);
        TextView subtituloActivas = view.findViewById(R.id.subtituloActivasTecnico);
        TextView subtituloResueltas = view.findViewById(R.id.subtituloResueltasTecnico);
        TextView subtituloSinAsignar = view.findViewById(R.id.subtituloSinAsignarTecnico);

        // Recuperamos el nombre del usuario, para que aparezca en pantalla al logearse.
        TextView saludoText = view.findViewById(R.id.saludoUsuarioTextTecnico);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String nombreUsuario = user.getDisplayName();

        if(user != null) {
            user.getDisplayName();
            if(nombreUsuario!=null && !nombreUsuario.isEmpty()) {
                saludoText.setText("Hola " + nombreUsuario);
            }
        }

        //Obtenemos la fecha del día de hoy, para que aparezca en pantalla al logearse.
        TextView fechaText = view.findViewById(R.id.textFechaTecnico);

        Locale locale = new Locale("es", "Es");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", locale);
        String fechaFormateada = dateFormat.format(date);

        fechaFormateada = fechaFormateada.substring(0, 1).toUpperCase() + fechaFormateada.substring(1);

        fechaText.setText(fechaFormateada);

        // Cargamos incidencias desde FireStore (todas, tendras que cambiarlo cuando tengas el diseño del item)
        recyclerView = view.findViewById(R.id.recyclerIncidenciasTecnico);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new IncidenciaAdapter(listaIncidencias,true, getParentFragmentManager());
        recyclerView.setAdapter(adapter);

        cargarIncidenciasDesdeFirestore();

        // Contador
        db.collection("incidencias")
                .addSnapshotListener((querySnapshot, error) -> {
                    int incidenciaActiva = 0;
                    int incidenciaResuelta = 0;
                    int incidenciaSinAsignar = 0;

                    int incidenciaActivaHoy = 0;
                    int incidenciaResueltaHoy = 0;
                    int incidenciaSinAsignarHoy = 0;


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
                                    } else if ("Pendiente".equalsIgnoreCase(incidencia.getEstado())) {
                                        incidenciaSinAsignar++;
                                        if(incidenciasFechasHoy.equalsIgnoreCase(fechaIncidenciaSoloFecha)) {
                                            incidenciaSinAsignarHoy++;
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
                    contadorSinAsignar.setText(String.valueOf(incidenciaSinAsignar));

                    subtituloActivas.setText(String.valueOf(incidenciaActivaHoy) + " activas hoy");
                    subtituloResueltas.setText(String.valueOf(incidenciaResueltaHoy) + " resueltas hoy");
                    subtituloSinAsignar.setText(String.valueOf(incidenciaSinAsignarHoy) + " sin asignar hoy");
                });

        // Botones
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

        btn_verSinAsignar.setOnClickListener(v -> {
            db.collection("incidencias")
                    .addSnapshotListener((querySnapshot, error) ->{
                        if(querySnapshot != null) {
                            listaIncidencias.clear();
                            for (QueryDocumentSnapshot doc : querySnapshot) {
                                Incidencia incidencia = doc.toObject(Incidencia.class);
                                incidencia.setId(doc.getId());

                                if("Pendiente".equalsIgnoreCase(incidencia.getEstado())) {
                                    listaIncidencias.add(incidencia);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
        });

        relativeAsignadas.setOnClickListener(v -> {
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

        relativeSinAsignar.setOnClickListener(v -> {
            db.collection("incidencias")
                    .addSnapshotListener((querySnapshot, error) ->{
                        if(querySnapshot != null) {
                            listaIncidencias.clear();
                            for (QueryDocumentSnapshot doc : querySnapshot) {
                                Incidencia incidencia = doc.toObject(Incidencia.class);
                                incidencia.setId(doc.getId());

                                if("Pendiente".equalsIgnoreCase(incidencia.getEstado())) {
                                    listaIncidencias.add(incidencia);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
        });

        return view;
    }

    private void cargarIncidenciasDesdeFirestore() {
        db.collection("incidencias")
                .addSnapshotListener((querySnapshot, error) -> {
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