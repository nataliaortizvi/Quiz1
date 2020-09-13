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

        //shared preferences que recibe los usuarios que ya estan registrados
        SharedPreferences preferences2 = getSharedPreferences("usuariosRegistrados",MODE_PRIVATE);
        idUserR = preferences2.getString("noNew","no_user");

        //clic boton continuar
        btContinuar.setOnClickListener(
                (view) -> {

                    nom = txNombre.getText().toString();
                    iden = txID.getText().toString();

                    if(nom.equals(" ")){
                        Toast.makeText(this, "Complete todos los datos", Toast.LENGTH_LONG).show();
                    }else{
                        SharedPreferences preferences3 = getSharedPreferences("user",MODE_PRIVATE);
                        preferences3.edit().putString("nom",nom).apply();

                        SharedPreferences preferences4 = getSharedPreferences("user",MODE_PRIVATE);
                        preferences4.edit().putString("id",iden).apply();


                        Intent i = new Intent(this, Nexo.class);
                        //i.putExtra("nombre", nom);
                        //i.putExtra("identificacion", iden);
                        //setResult(RESULT_OK, i);
                        startActivity(i);
                        finish();

                        Log.d("iddddd", ""+iden);
                        Log.d("nommmm", ""+nom);


                    }


                    /*if(iden.equals(idUserR)){
                        Toast.makeText(this, "Este usuario ya existe, coloque otro", Toast.LENGTH_LONG).show();
                    }else{
                        startActivity(i);
                        finish();
                    }*/

                }
        );
    }
}