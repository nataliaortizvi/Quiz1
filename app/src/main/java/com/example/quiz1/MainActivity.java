package com.example.quiz1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btRegistrar;
    private TextView usuarios;
    private String nomUser;
    private String idUser, registrados;
    private int puntajeRiesgo;
    private int puntajeNexo, puntajeSintomas;
    private boolean empezar = false;
    private ArrayList nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciar
        btRegistrar = findViewById(R.id.btRegistrar);
        usuarios = findViewById(R.id.usuarios);

        nombres = new ArrayList<>();

        new Thread(
                () -> {

                        //shared preferences que recibe los puntajes
                        SharedPreferences preferences = getSharedPreferences("puntajeNexo",MODE_PRIVATE);
                        puntajeNexo = preferences.getInt("nexo",0);

                        SharedPreferences preferences1 = getSharedPreferences("puntajeSintomas",MODE_PRIVATE);
                        puntajeSintomas = preferences1.getInt("sintomas",0);

                        puntajeRiesgo = puntajeNexo + puntajeSintomas;

                        SharedPreferences preferences3 = getSharedPreferences("user",MODE_PRIVATE);
                        nomUser = preferences3.getString("nom","");

                        SharedPreferences preferences4 = getSharedPreferences("user",MODE_PRIVATE);
                        idUser = preferences4.getString("id","");

                        registrados = nomUser + "   " + puntajeRiesgo;

                        runOnUiThread(
                                ()->{
                                    usuarios.append(registrados + "\n");
                                }
                        );

                        Log.d("IDDDDDD",""+idUser);
                        Log.d("BOOOOLEANNNN",""+empezar);
                        Log.d("NOMMMMMMMM",""+nomUser);

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                }
        ).start();

        //clic boton registrar
        btRegistrar.setOnClickListener(
                (view) -> {

                    /////////shared preferences que manda la lista que hay de usuarios registrados
                    //se usan en la pantalla de registro para verificar que no se repitan
                    SharedPreferences preferences2 = getSharedPreferences("usuariosRegistrados", MODE_PRIVATE);
                    preferences2.edit().putString("noNew", idUser).apply();

                    Intent i = new Intent(this, Registro.class);
                    empezar = true;
                    startActivityForResult(i,33);

                }
        );
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 33 && resultCode == RESULT_OK){

            nomUser = data.getExtras().getString("nombre");
            idUser = data.getExtras().getString("identificacion \n");

        }
    }*/
}