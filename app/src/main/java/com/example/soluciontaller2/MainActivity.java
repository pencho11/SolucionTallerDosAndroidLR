package com.example.soluciontaller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }



    public void abrirOperadores(View view){
        Intent ventana = new Intent(this, OperadoresApp.class);
        startActivity(ventana);
    }

    public void abrirCreditos(View view){
        Intent ventana = new Intent(this, CreditosActivity.class);
        startActivity(ventana);
    }


    public void abirPreguntas(View view){
        Intent preguntas = new Intent(this, StartingScreenActivity.class);
        startActivity(preguntas);
    }


}
