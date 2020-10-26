package com.example.casillasverificacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


        public void onCheckboxClicked(View view) {
        // Is the view now checked?
        TextView res = (TextView) findViewById(R.id.respuesta);
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.lun:
                if (checked) {
                Toast.makeText(getApplicationContext(), "Lunes añadido", Toast.LENGTH_SHORT).show();
                break;
            }
                else break;
            case R.id.mar:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "martes añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.mie:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "miercoles añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.jue:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "jueves añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.vie:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "viernes añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.sab:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "sabado añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.dom:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "domingo añadido", Toast.LENGTH_SHORT).show();
                    res.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    res.setVisibility(View.INVISIBLE);
                    break;
                }
        }

    }
}