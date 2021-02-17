package com.example.sqlite_ejercicio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Visualizar extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor c;
    GestorClientes clientes;
    GestorFacturas facturas;


    LinearLayout listaCliente;
    LinearLayout listaFacturas;

    LinearLayout cliente; //creamos uno por cada cliente
    LinearLayout factura; //creamos uno por cada factura

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_layout);

        listaCliente = findViewById(R.id.listaClientes_layout);
        listaFacturas = findViewById(R.id.listaFacturas_layout);

        clientes = new GestorClientes(this,"clientes",null,1);
        //facturas = GestorFacturas();

        //inicializamos la bd y el cursor
        db = clientes.getReadableDatabase();
        c = db.rawQuery(" SELECT * FROM Clientes ", null);

        actualizarLayout();
    }

    public void actualizarLayout(){

        listaCliente.removeAllViewsInLayout();

        //Comprobamos que hemos obtenido algún resultado
        if(c != null){
            if (c.moveToFirst())
            {
                //Recorremos el cursor hasta que no haya más registros
                do
                {
                    String dni= c.getString(0);
                    String nombre = c.getString(1);
                    String direccion = c.getString(2);
                    String telefono = c.getString(3);
                    addCliente(nombre+", "+dni+", "+direccion+", "+telefono);

                } while(c.moveToNext());// Iteramos sobre los resultados obtenidos.
            }
        }else{
            Toast.makeText(this, "Nada que mostrar", Toast.LENGTH_SHORT).show();
        }


    }

    public void addCliente(String contenido){
        cliente = new LinearLayout(this);
        cliente.setOrientation(LinearLayout.HORIZONTAL);
        cliente.setPadding(0,0,0,15);

        listaCliente.addView(cliente);

        TextView tx = new TextView(this);
        tx.setText(contenido);

        cliente.addView(tx);
    }

    public void volver(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void borrar(View view){
        //borar y actualizar layouts
        c = db.rawQuery(" delete from Clientes  ", null);
        actualizarLayout();
    }
}

/*

Por cada registro en el cursor, cremos en tiempo real un layout con nuestros clientes


Cursor c = db.rawQuery(" SELECT NIA,nombre FROM Alumnos
WHERE nombre=‘FRANK' ", null);


 */