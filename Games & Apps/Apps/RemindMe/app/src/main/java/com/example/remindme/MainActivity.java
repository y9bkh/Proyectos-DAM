package com.example.remindme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    EditText txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        btnIniciar = findViewById(R.id.btnIniciar);
        txtNombre = findViewById(R.id.txtNombre);

        btnIniciar.setOnClickListener(v -> {
            String nombre = txtNombre.getText().toString().trim();

            if(nombre.isEmpty()){
                Toast.makeText(getApplicationContext(), "Campo no rellenado", Toast.LENGTH_SHORT).show();
            } else {
                // Hago uso del SharedPreferences ya que me ayuda a guardar el usuario
                SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Nombre", nombre);
                editor.apply();

                // Una vez guardada el nombre, notifico que al cumplirse la condición y se inicie paase de la plantilla "a" a la "b"
                Intent intent = new Intent(getApplicationContext(), RememberActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}