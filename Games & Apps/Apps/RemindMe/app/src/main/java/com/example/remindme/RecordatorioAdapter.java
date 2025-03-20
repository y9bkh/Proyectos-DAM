package com.example.remindme;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordatorioAdapter extends RecyclerView.Adapter<RecordatorioAdapter.ViewHolder> {
    private ArrayList<Recordatorio> listaRecordatorios = new ArrayList<Recordatorio>();
    private OnRecordatorioLongClickListener listener;


    public interface OnRecordatorioLongClickListener { // ayudara a recibir como parametro la posicion del recuerdo
        void onRecordatorioLongClick(int position);
    }

    public RecordatorioAdapter() {
    }                   

    public RecordatorioAdapter(ArrayList<Recordatorio> listaRecordatorios) {
    }

    public RecordatorioAdapter(ArrayList<Recordatorio> listaRecordatorios, OnRecordatorioLongClickListener listener) {
        this.listaRecordatorios = listaRecordatorios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecordatorioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recordatorio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordatorioAdapter.ViewHolder holder, int position) {
        Recordatorio recordatorio = listaRecordatorios.get(position);
        holder.textViewRecordatorio.setText(recordatorio.getRecordatorio());

        if(recordatorio.isEstaBorrado()) {
            holder.itemView.setBackgroundColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnLongClickListener(v -> {
            // Aquí, eliminamos el recordatorio y lo marcamos como eliminado
            recordatorio.setEstaBorrado(true); // Marcamos como eliminado
            listener.onRecordatorioLongClick(position); // Notifica el click largo para eliminar
            holder.itemView.setBackgroundColor(Color.RED); // Poner el color rojo al eliminar
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaRecordatorios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRecordatorio;

        public ViewHolder(View view) {
            super(view);
            textViewRecordatorio = itemView.findViewById(R.id.textViewRecordatorio);
        }
    }
}
