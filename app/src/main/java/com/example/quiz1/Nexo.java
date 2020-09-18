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

public class Nexo extends AppCompatActivity {

    private Button btContinuar;
    private CheckBox opcion1, opcion2, opcion3, opcion4, opcion5;
    private int puntajeN = 0;
    private boolean conti = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nexo);

        //referenciar
        btContinuar = findViewById(R.id.btContinuar);
        opcion1 = findViewById(R.id.opcion1n);
        opcion2 = findViewById(R.id.opcion2n);
        opcion3 = findViewById(R.id.opcion3n);
        opcion4 = findViewById(R.id.opcion4n);
        opcion5 = findViewById(R.id.opcion5n);



        new Thread(
                () ->{
                    while(conti==true){
                        runOnUiThread(
                                () -> {
                                    //si chequea ninguna de las anteriores entonces todas las otras se desactivan pero el boton de continuar cambia de color a activo
                                    if(opcion5.isChecked() == true){

                                        btContinuar.setBackgroundColor(Color.rgb(226,0,45));

                                        opcion1.setChecked(false);
                                        opcion2.setChecked(false);
                                        opcion3.setChecked(false);
                                        opcion4.setChecked(false);
                                    }


                                    //si escoge alguna opcion entonces el boton cambia de color a activo
                                    if(opcion1.isChecked() == true || opcion2.isChecked() == true || opcion3.isChecked() == true ||
                                            opcion4.isChecked() == true){
                                        btContinuar.setBackgroundColor(Color.rgb(226,0,45));

                                    }

                                    if(opcion1.isChecked() == false && opcion2.isChecked() == false && opcion3.isChecked() == false &&
                                            opcion4.isChecked() == false && opcion5.isChecked() == false){

                                        btContinuar.setBackgroundColor(Color.rgb(214,204,205));

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
                    conti = false;

                    //set de puntaje de riesgo
                    if(opcion1.isChecked() == true){
                        puntajeN += 3;
                    }
                    if(opcion2.isChecked() == true){
                        puntajeN += 3;
                    }
                    if(opcion3.isChecked() == true){
                        puntajeN += 3;
                    }
                    if(opcion4.isChecked() == true){
                        puntajeN += 3;
                    }
                    if(opcion5.isChecked() == true){
                        puntajeN = 0;
                    }



                    if(opcion1.isChecked() == true || opcion2.isChecked() == true || opcion3.isChecked() == true ||
                            opcion4.isChecked() == true || opcion5.isChecked() == true ){

                        Intent i = new Intent(this, Sintomas.class);

                        i.putExtra("puntoN", puntajeN);

                        startActivity(i);
                        finish();

                    }else{
                        Toast.makeText(this, "Escoja una opción", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}