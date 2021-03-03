package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Ayuda extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayuda_layout);


    }

    

    public void atras(View view){

        Intent intent = new Intent(this, Configuracion.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) { //botón atrás del menu de navegación del movil

        if(keyCode == KeyEvent.KEYCODE_BACK){

            Intent intent = new Intent(this, Configuracion.class);
            startActivity(intent);

            return true;
        }else{
            return super.onKeyUp(keyCode, event);
        }
    }
}
