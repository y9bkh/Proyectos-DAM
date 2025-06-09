package com.example.reparapablo.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reparapablo.fragments.EditarIncidenciaFragment;
import com.example.reparapablo.fragments.VerIncidenciaFragment;
import com.example.reparapablo.models.Incidencia;
import com.example.reparapablo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class IncidenciaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static List<Incidencia> lista;
    private boolean estecnico;
    private FragmentManager fragmentManager;

    public IncidenciaAdapter(List<Incidencia> lista, boolean estecnico, FragmentManager fragmentManager) {
        this.lista = lista;
        this.estecnico = estecnico;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
        if (estecnico == true) {
            return 1;
        } else {
            return 0;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_incidencia_tecnico, parent, false);
            return new TecnicoViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_incidencia, parent, false);
            return new ProfesorViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Incidencia incidencia = lista.get(position);
        if(holder instanceof TecnicoViewHolder) {
            ((TecnicoViewHolder) holder).bind(incidencia, fragmentManager, this);
        } else {
            ((ProfesorViewHolder) holder).bind(incidencia, fragmentManager, this);
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ProfesorViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtUbicacion, txtFecha,txtEstado, txtAsignadoA;
        View viewStatus;

        public ProfesorViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloitem);
            txtUbicacion = itemView.findViewById(R.id.txtUbicacionitem);
            txtFecha = itemView.findViewById(R.id.txtFechaitem);
            txtEstado = itemView.findViewById(R.id.txtEstado);
            txtAsignadoA = itemView.findViewById(R.id.txtAsignadoA);
            viewStatus = itemView.findViewById(R.id.viewStatus);
        }

        public void bind(Incidencia incidencia, FragmentManager fragmentManager, IncidenciaAdapter adapter) {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.white));

            txtTitulo.setText(incidencia.getTitulo());
            txtUbicacion.setText(incidencia.getUbicacion());
            txtFecha.setText(incidencia.getFecha());
            txtAsignadoA.setText(incidencia.getAsignadoA());

            String estadoActual = incidencia.getEstado();
            if ("Pendiente".equalsIgnoreCase(estadoActual)) {
                txtEstado.setText("Pendiente");
                txtEstado.setBackgroundResource(R.drawable.bg_estado_pendiente);
                txtEstado.setTextColor(Color.parseColor("#856404"));
                viewStatus.setBackgroundResource(R.color.estado_pendiente);
            } else if ("En proceso".equalsIgnoreCase(estadoActual)) {
                txtEstado.setText("En proceso");
                txtEstado.setBackgroundResource(R.drawable.bg_estado_en_proceso);
                txtEstado.setTextColor(Color.parseColor("#0c5460"));
                viewStatus.setBackgroundResource(R.color.estado_en_proceso);
            } else if ("Resuelto".equalsIgnoreCase(estadoActual)) {
                txtEstado.setText("Resuelto");
                txtEstado.setBackgroundResource(R.drawable.bg_estado_resuelto);
                txtEstado.setTextColor(Color.parseColor("#155724"));
                viewStatus.setBackgroundResource(R.color.estado_resuelto);
            }

            itemView.setOnLongClickListener(v -> {
                if ("Resuelto".equalsIgnoreCase(incidencia.getEstado())) {
                    int posicion = getAdapterPosition();
                    if (posicion == RecyclerView.NO_POSITION || posicion >= adapter.lista.size()) {
                        return true;
                    }

                    Incidencia incidenciaActual = adapter.lista.get(posicion);

                    if("Resuelto".equalsIgnoreCase(incidenciaActual.getEstado())) {
                        itemView.setBackgroundResource(R.drawable.button_resuelto);
                        if (posicion != RecyclerView.NO_POSITION) {
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("incidencias").document(incidencia.getId())
                                    .delete()
                                    .addOnSuccessListener(unused -> {
                                        if (posicion >= 0 && posicion < adapter.lista.size()) {
                                            adapter.lista.remove(posicion);
                                            adapter.notifyItemRemoved(posicion);
                                        }
                                    })
                                    .addOnFailureListener(e -> {
                                        itemView.setBackgroundColor(Color.TRANSPARENT);
                                        Toast.makeText(itemView.getContext(), "Error al eliminar", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        Toast.makeText(itemView.getContext(), "Solo se pueden eliminar incidencias resueltas", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            });

            itemView.setOnClickListener(v -> {
                EditarIncidenciaFragment fragment = EditarIncidenciaFragment.newInstance(incidencia);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            });
        }
    }

    static class TecnicoViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtUbicacion, txtFecha, txtReportadoPor, txtEstado;
        Button btn_estado;
        View viewStatus;

        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        String nombreUsuario = usuario.getDisplayName();

        public TecnicoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloitem);
            txtUbicacion = itemView.findViewById(R.id.txtUbicacionitem);
            txtFecha = itemView.findViewById(R.id.txtFechaitem);
            txtReportadoPor = itemView.findViewById(R.id.txtReportadopor);
            btn_estado = itemView.findViewById(R.id.btn_estado);
            txtEstado = itemView.findViewById(R.id.txtEstado);
            viewStatus = itemView.findViewById(R.id.viewStatus);
        }

        public void bind(Incidencia incidencia, FragmentManager fragmentManager, IncidenciaAdapter adapter) {
            txtTitulo.setText(incidencia.getTitulo());
            txtUbicacion.setText(incidencia.getUbicacion());
            txtFecha.setText(incidencia.getFecha());
            txtReportadoPor.setText("Reportado por: " + incidencia.getReportadoPor());

            actualizarEstado(incidencia.getEstado());

            btn_estado.setOnClickListener(v -> {
                String estadoActual = incidencia.getEstado();
                if (estadoActual == null || estadoActual.isEmpty()) {
                    estadoActual = "Pendiente";
                }

                String nuevoEstado;
                if ("Pendiente".equals(estadoActual)) {
                    nuevoEstado = "En proceso";
                } else if ("En proceso".equals(estadoActual)) {
                    nuevoEstado = "Resuelto";
                } else {
                    nuevoEstado = estadoActual;
                }

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("incidencias").document(incidencia.getId())
                        .update("estado", nuevoEstado, "asignadoA", nombreUsuario)
                        .addOnSuccessListener(unused -> {
                            incidencia.setEstado(nuevoEstado);
                            incidencia.setAsignadoA(nombreUsuario);
                            actualizarEstado(nuevoEstado);
                        })
                        .addOnFailureListener(e -> Log.e("ESTADO_UPDATE", "Error al actualizar estado", e));
            });


            itemView.setOnClickListener(v -> {
                VerIncidenciaFragment fragment = VerIncidenciaFragment.newInstance(incidencia);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            });
        }

        private void actualizarEstado(String estadoActual) {
            if ("Pendiente".equals(estadoActual)) {
                txtEstado.setText("Pendiente");
                txtEstado.setBackgroundResource(R.drawable.bg_estado_pendiente);
                txtEstado.setTextColor(Color.parseColor("#856404"));
                viewStatus.setBackgroundResource(R.color.estado_pendiente);
                btn_estado.setVisibility(View.VISIBLE);
                btn_estado.setText("Iniciar");
            } else if ("En proceso".equals(estadoActual)) {
                txtEstado.setText("En proceso");
                txtEstado.setBackgroundResource(R.drawable.bg_estado_en_proceso);
                txtEstado.setTextColor(Color.parseColor("#0c5460"));
                viewStatus.setBackgroundResource(R.color.estado_en_proceso);
                btn_estado.setBackgroundTintList(null);
                btn_estado.setBackgroundResource(R.drawable.button_resuelto);
                btn_estado.setText("Resolver");
            } else if ("Resuelto".equals(estadoActual)) {
                txtEstado.setText("Resuelto");
                txtEstado.setBackgroundResource(R.drawable.bg_estado_resuelto);
                txtEstado.setTextColor(Color.parseColor("#155724"));
                viewStatus.setBackgroundResource(R.color.estado_resuelto);
                btn_estado.setVisibility(View.GONE);
            }
        }
    }
}
