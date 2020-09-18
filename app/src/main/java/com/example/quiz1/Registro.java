package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText txNombre, txID;
    private Button btContinuar;
    private String nom, iden, idUserR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //referenciar
        txNombre = findViewById(R.id.txNombre);
        txID = findViewById(R.id.txID);
        btContinuar = findViewById(R.id.btContinuar);

        //clic boton continuar
        btContinuar.setOnClickListener(
                (view) -> {

                    SharedPreferences preferencesUser = getSharedPreferences("user",MODE_PRIVATE);
                    String registroYa = preferencesUser.getString("registro", "");
                    String idYa = preferencesUser.getString("identificacion","");

                    nom = txNombre.getText().toString().trim();
                    iden = txID.getText().toString().trim();


                    if(iden.isEmpty()==true){
                        Toast.makeText(this, "Llene todos los datos", Toast.LENGTH_LONG).show();
                    }else{
                        if(idYa.contains(iden)){
                            Toast.makeText(this, "Usario ya registrado", Toast.LENGTH_LONG).show();

                        }else{
                            String registro = registroYa+":"+nom;
                            String identificacion = idYa+":"+iden;

                            preferencesUser.edit().putString("registro",registro).apply();
                            preferencesUser.edit().putString("identificacion",identificacion).apply();

                            Intent i = new Intent(this, Nexo.class);
                            startActivity(i);
                            finish();

                        }
                    }

                }
        );
    }
}