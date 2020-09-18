package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Sintomas extends AppCompatActivity {

    private Button btContinuar, btFinalizar;
    private CheckBox opcion1n, opcion2n, opcion3n, opcion4n, opcion5n, opcion6n, opcion7n;
    private int puntajeS = 0;
    private int puntosT = 0;
    private String totalP, totalYa;
    private boolean conti = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas);

        //referenciar
        btContinuar = findViewById(R.id.btContinuar);
        btFinalizar = findViewById(R.id.btFinalizar);
        opcion1n = findViewById(R.id.opcion1n);
        opcion2n = findViewById(R.id.opcion2n);
        opcion3n = findViewById(R.id.opcion3n);
        opcion4n = findViewById(R.id.opcion4n);
        opcion5n = findViewById(R.id.opcion5n);
        opcion6n = findViewById(R.id.opcion6n);
        opcion7n = findViewById(R.id.opcion7n);

        puntosT = getIntent().getExtras().getInt("puntoN");

        new Thread(
                () ->{
                    while(conti==true){
                        runOnUiThread(
                                () -> {
                                    //si chequea ninguna de las anteriores entonces todas las otras se desactivan pero el boton de continuar cambia de color a activo
                                    if(opcion7n.isChecked() == true){

                                        btFinalizar.setVisibility(View.VISIBLE);
                                        btContinuar.setVisibility(View.GONE);

                                        opcion1n.setChecked(false);
                                        opcion2n.setChecked(false);
                                        opcion3n.setChecked(false);
                                        opcion4n.setChecked(false);
                                        opcion5n.setChecked(false);
                                        opcion6n.setChecked(false);
                                    }


                                    //si escoge alguna opcion entonces el boton cambia de color a activo
                                    if(opcion1n.isChecked() == true || opcion2n.isChecked() == true || opcion3n.isChecked() == true ||
                                            opcion4n.isChecked() == true || opcion5n.isChecked() == true || opcion6n.isChecked() == true){

                                        btFinalizar.setVisibility(View.VISIBLE);
                                        btContinuar.setVisibility(View.GONE);

                                    }

                                    if(opcion1n.isChecked() == false && opcion2n.isChecked() == false && opcion3n.isChecked() == false &&
                                            opcion4n.isChecked() == false && opcion5n.isChecked() == false && opcion6n.isChecked() == false &&opcion7n.isChecked()==false){

                                        btFinalizar.setVisibility(View.GONE);
                                        btContinuar.setVisibility(View.VISIBLE);

                                    }
                                }
                        );
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
        ).start();





        btContinuar.setOnClickListener(
                (view) -> {
                    Toast.makeText(this, "Escoja una opción", Toast.LENGTH_LONG).show();
                }
        );

        btFinalizar.setOnClickListener(
                (view) -> {
                    conti = false;

                    //set de puntaje de riesgo
                    if(opcion1n.isChecked() == true){
                        puntajeS += 4;
                    }
                    if(opcion2n.isChecked() == true){
                        puntajeS += 4;
                    }
                    if(opcion3n.isChecked() == true){
                        puntajeS += 4;
                    }
                    if(opcion4n.isChecked() == true){
                        puntajeS += 4;
                    }
                    if(opcion5n.isChecked() == true){
                        puntajeS += 4;
                    }
                    if(opcion6n.isChecked() == true){
                        puntajeS += 4;
                    }
                    if(opcion7n.isChecked() == true){
                        puntajeS = 0;
                    }

                    puntajeS += puntosT;;


                    if(opcion1n.isChecked() == true || opcion2n.isChecked() == true || opcion3n.isChecked() == true ||
                            opcion4n.isChecked() == true || opcion5n.isChecked() == true || opcion6n.isChecked() == true || opcion7n.isChecked() == true){

                        //shared preferences de los puntajes de los sintomas
                        SharedPreferences preferencesPuntaje = getSharedPreferences("puntos", MODE_PRIVATE);
                        totalYa = preferencesPuntaje.getString("todosLosPuntos","");

                        totalP = ""+puntajeS;

                        String yaTodo = totalYa+":"+totalP;
                        preferencesPuntaje.edit().putString("todosLosPuntos", yaTodo).apply();

                        Intent i = new Intent(this, MainActivity.class);


                        startActivity(i);
                        finish();

                    }
                }
        );

    }
}