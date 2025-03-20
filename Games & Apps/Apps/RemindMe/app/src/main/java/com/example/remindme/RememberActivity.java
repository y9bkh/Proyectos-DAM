package com.example.remindme;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class RememberActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Recordatorio> listaRecordatorios;
    private EditText txtRecordatorio;
    private RecordatorioAdapter adaptador;
    private ImageView addRecordatorio;
    private String nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_remember);

        // Obtener el nombre de usuario desde el Intent
        Intent intent = getIntent();
        nombreUsuario = intent.getStringExtra("NombreUsuario");

        // Si el Intent no lo trae, intenta obtenerlo de SharedPreferences
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
            nombreUsuario = prefs.getString("Nombre", "UsuarioDesconocido");
        }

        Toast.makeText(this, "Bienvenido " + nombreUsuario, Toast.LENGTH_SHORT).show();

        // Inicializar elementos
        txtRecordatorio = findViewById(R.id.txtRecordatorio);
        addRecordatorio = findViewById(R.id.addRecordatorio);
        recyclerView = findViewById(R.id.recyclerView2);

        // Cargar recordatorios guardados
        listaRecordatorios = cargarRecuerdos(nombreUsuario);

        // Configurar RecyclerView
        listaRecordatorios = cargarRecuerdos(nombreUsuario);
        adaptador = new RecordatorioAdapter(listaRecordatorios, position -> eliminarRecordatorio(position));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);

        addRecordatorio.setOnClickListener(v -> {
            añadirRecordatorio();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void añadirRecordatorio() {
        String recuerdo = txtRecordatorio.getText().toString().trim();
        if (!recuerdo.isEmpty()) {
            Recordatorio nuevoRecuerdo = new Recordatorio(recuerdo);
            nuevoRecuerdo.setEstaBorrado(false);
            listaRecordatorios.add(nuevoRecuerdo);


            // Reproducir sonido
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.checksound);
            mediaPlayer.start();


            guardarRecordatorios(nombreUsuario, listaRecordatorios);
            adaptador.notifyDataSetChanged();

            txtRecordatorio.setText(""); // Limpiar campo de entrada
        }
    }

    private void eliminarRecordatorio(int position) {
        listaRecordatorios.remove(position);
        guardarRecordatorios(nombreUsuario, listaRecordatorios); // Guardamos los cambios
        adaptador.notifyItemRemoved(position); // Notificar al RecyclerView

        Toast.makeText(this, "Recordatorio eliminado", Toast.LENGTH_SHORT).show();
    }


    public void guardarRecordatorios(String nombreUsuario, ArrayList<Recordatorio> recordatorios) {
        try {
            SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            String nombreArchivo = "recordatorios_" + nombreUsuario + ".txt"; // Creo el nombre del archivo que se guardara en Sharedpreferences con cada usuario
            File archivo = new File(getFilesDir(), nombreArchivo); // Creo el archivo obteniendo la dirección de ruta donde se guardará, la ruta la obtenemos gracias a getFilesDir() donde me devuelve un objeto Files que me informa donde almacenara esos datos de la app

            // Esto me ayuda a escribir en el archivo creado, es decir, añadir los recordatorios del proyecto
            FileOutputStream fos = new FileOutputStream(archivo);
            for (Recordatorio rec : recordatorios) {
                fos.write((rec.getRecordatorio() + "\n").getBytes());
            }
            fos.close();

            // Aplicamos los cambios
            editor.putString("Recordatorios_" + nombreUsuario, nombreArchivo);
            editor.apply();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Recordatorio> cargarRecuerdos(String nombreUsuario) {
        ArrayList<Recordatorio> listaRecordatorios = new ArrayList<>();

        try {
            // Recojo la info de los recordatorios guardados
            SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);

            String nombreArchivo = prefs.getString("Recordatorios_" + nombreUsuario, "");
            File archivo = new File(getFilesDir(), nombreArchivo);

            if (nombreArchivo.isEmpty()) {
                return listaRecordatorios; // Si no hay ningun archivo guardado en mi lista, devuelve una vacía.
            }

            if (!archivo.exists()) {
                return listaRecordatorios; // Si no existe el archivo que tambien me devuelva una vacía.
            }

            // Leer el archivo
            FileInputStream fis = new FileInputStream(archivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String linea;

            while ((linea = br.readLine()) != null) { // Me permite leer un archivo linea por linea hasta que me devuelva null
                listaRecordatorios.add(new Recordatorio(linea));
            }

            br.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaRecordatorios;
    }


}


