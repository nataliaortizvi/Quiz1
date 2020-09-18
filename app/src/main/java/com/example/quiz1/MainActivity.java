package com.example.quiz1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btRegistrar;
    private TextView usuarios;
    private String nomUser;
    private String idUser;
    private String pt;
    private int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciar
        btRegistrar = findViewById(R.id.btRegistrar);
        usuarios = findViewById(R.id.usuarios);

        usuarios.setMovementMethod(new ScrollingMovementMethod());

        //clic boton registrar
        btRegistrar.setOnClickListener(
                (view) -> {
                    Intent i = new Intent(this, Registro.class);
                    startActivity(i);
                }
        );
    }



    //antes que la actividad sea visible
    @Override
    protected void onResume() {
        super.onResume();
        //shared preferences que recibe los puntajes
        SharedPreferences preferencesPuntaje = getSharedPreferences("puntos",MODE_PRIVATE);
        pt = preferencesPuntaje.getString("todosLosPuntos","");

        //shared preferences que recibe datos del usuario
        SharedPreferences preferencesUser = getSharedPreferences("user",MODE_PRIVATE);
        nomUser = preferencesUser.getString("registro","");
        idUser = preferencesUser.getString("identificacion","");


        //arreglos variables del usuario
        String[] numbers = idUser.split(":");
        String[] names = nomUser.split(":");
        String[] points = pt.split(":");

        usuarios.setText(" ");


        //
        for(int i=0; i < names.length; i++) {
            cont = i;
            usuarios.append(names[cont] + points[cont] + "\n");
        }






    }
}