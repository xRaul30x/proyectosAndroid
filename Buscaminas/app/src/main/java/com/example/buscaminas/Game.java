package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.buscaminasv2.R;

public class Game extends AppCompatActivity {

    static boolean modoBanderas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        RelativeLayout layout = findViewById(R.id.layout);
        Tablero pintarTablero = new Tablero(this);
        layout.addView(pintarTablero);


    }

    public void volver(View view){
        finish();
        Intent intent = new Intent(view.getContext(),MenuPrincipal.class);
        startActivity(intent);
    }

    public void reiniciar(View view){
        finish();
        Intent intent = new Intent(view.getContext(),Game.class);
        startActivity(intent);
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.banderas:
                if (checked) {
                    modoBanderas = true;
                    Toast toast = Toast.makeText(getApplicationContext(), "Modo colocar banderas", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }
                else modoBanderas = false;

                break;
        }
    }

    public static boolean isModoBanderas(){
        if(modoBanderas) return true;
        else return false;
    }
}