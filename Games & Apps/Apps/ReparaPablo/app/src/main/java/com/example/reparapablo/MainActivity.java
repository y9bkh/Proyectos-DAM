package com.example.reparapablo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.reparapablo.fragments.LoginFragment;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            FirebaseApp.initializeApp(this);

        // Solo cargar el fragmento si es la primera vez
        if (savedInstanceState == null) {
            Fragment loginFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, loginFragment)
                    .commit();
        }
    }
}
