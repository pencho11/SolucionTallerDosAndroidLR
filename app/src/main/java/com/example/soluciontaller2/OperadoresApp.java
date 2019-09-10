package com.example.soluciontaller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OperadoresApp extends AppCompatActivity {

    private EditText etNombre;
    private TextView tvBestScore;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operadores_app);

        etNombre = (EditText)findViewById(R.id.txtNombre);
        tvBestScore = (TextView)findViewById(R.id.textViewBestScore);

        etNombre = (EditText)findViewById(R.id.txtNombre);
        tvBestScore = (TextView)findViewById(R.id.textViewBestScore);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        baseDeDatos();

        mp = MediaPlayer.create(this, R.raw.ghettoakon);
        mp.start();
        mp.setLooping(true);
    }

    public void Jugar(View view){
        String nombre = etNombre.getText().toString();
        if(!nombre.equals("")){
            mp.stop();
            mp.release();

            Intent intent = new Intent(this, OperadoresActivityNivel1.class);

            intent.putExtra("jugador", nombre);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Primero escribir tu nombre", Toast.LENGTH_SHORT).show();
            etNombre.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(etNombre, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void baseDeDatos(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bd", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor consulta = bd.rawQuery(
                "select * from puntaje where score  = (select max(score) from puntaje)", null);
        if(consulta.moveToFirst()){
            String tempNombre = consulta.getString(0);
            String tempScore = consulta.getString(1);
            tvBestScore.setText("Record: " + tempScore+ " de " + tempNombre);
        }
        else
        {
            bd.close();
        }
    }


    @Override
    public void onBackPressed(){
        mp.stop();
    }

    public void salir(View view){
        finish();
        mp.stop();
    }
}
