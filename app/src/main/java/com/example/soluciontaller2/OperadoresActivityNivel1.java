package com.example.soluciontaller2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class OperadoresActivityNivel1 extends AppCompatActivity {

    Random miAleatorio = new Random();

    private TextView aleatorio;
    TextView nombre;
    TextView tvNombre;
    private EditText respuesta;
    private MediaPlayer mpwon;
    private MediaPlayer mpbad;


    private int resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String nombreJugador;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operadores_nivel1);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        aleatorio = findViewById(R.id.textViewAleatorio);
        nombre = findViewById(R.id.txtNombre);
        respuesta = findViewById(R.id.etRespuesta);
        tvNombre = findViewById(R.id.textViewNombre);

        mpwon = MediaPlayer.create(this, R.raw.wonderful);
        mpbad = MediaPlayer.create(this, R.raw.bad);

        nombreJugador = getIntent().getStringExtra("jugador");
        tvNombre.setText("Jugador: " + nombreJugador);

        numAleatorio();
    }

    public void comparar(View view){

        String resp = respuesta.getText().toString();
        if(!resp.equals("")){
            int respuestaJugador = Integer.parseInt(resp);

            if(resultado == respuestaJugador){

                Toast.makeText(this, "Bueno",Toast.LENGTH_LONG).show();
                respuesta.setText("");
                mpwon.start();
                finish();
                startActivity(getIntent());

            }else{
                Toast.makeText(this, "Malo",Toast.LENGTH_LONG).show();
                mpbad.start();
                finish();
                startActivity(getIntent());
            }
        }else{
            Toast.makeText(this, "escribe tu respuesta",Toast.LENGTH_LONG).show();
        }
    }

    public void numAleatorio(){

        int n = miAleatorio.nextInt(50);
            int n2 = miAleatorio.nextInt(50);

        if( n <= 10 ) {
            aleatorio.setText(n + " + " + n2);
            resultado = n + n2;
        } else if(n <= 20 && n2 <= 10 ){
            aleatorio.setText(n + " - " + n2);
            resultado = n - n2;
        } else if(n <= 30 && n2 <= 20 ){
            aleatorio.setText(n + " * " + n2);
            resultado = n * n2;
        } else if(n <=50  && n2 <= 10){
            aleatorio.setText(n + " / " + n2);
            resultado = n / n2;
        }
        else{
            numAleatorio();
        }
    }
}
