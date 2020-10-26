package com.example.alertaconbotones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

//import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Esto es un mensaje de alerta!");
        alert.setTitle("Mensaje con AlertDialog");
        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }); //El segundo parámetro puede ser una función OnClickListener, para realizar alguna acción.
        //alert.setNegativeButton("Opción No", null);//Igual que el anterior pero para la otra opción.
        alert.setNeutralButton("Cancelar", null);//Tercera opción a realizar.
        alert.show();
    }

}