package com.example.sqlite_ejercicio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Visualizar extends AppCompatActivity {

    SQLiteDatabase db, db2;
    Cursor c,c2;
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
        facturas = new GestorFacturas(this,"facturas",null,1);

        //inicializamos la bd y el cursor
        db = clientes.getReadableDatabase();
        db2 = facturas.getReadableDatabase();
        c = db.rawQuery(" SELECT * FROM Clientes ", null);
        c2 = db2.rawQuery(" SELECT * FROM Facturas ", null);

        actualizarLayoutClientes();
    }

    public void actualizarLayoutClientes(){

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

    public void addCliente(final String contenido){
        cliente = new LinearLayout(this);
            cliente.setOrientation(LinearLayout.HORIZONTAL);
            cliente.setPadding(0,0,0,15);

        TextView tx = new TextView(this);
        tx.setText(contenido);

        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] contenidoSplit = contenido.split(", ");
                int dni = Integer.parseInt(contenidoSplit[1]);
                getFacturas(dni);
            }
        });

        cliente.addView(tx);
        listaCliente.addView(cliente);
    }

    public void getFacturas(int dni){
        c2 = db2.rawQuery(" SELECT * FROM Facturas WHERE dni="+dni, null);

        if(c2 != null){
            listaFacturas.removeAllViewsInLayout(); //primero borramos las que hubiese

            if (c2.moveToFirst())
            {
                Toast toast = Toast.makeText(this, "Mostrando las facturas de [dni="+dni+"]", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();

                //Recorremos el cursor hasta que no haya más registros
                do
                {
                    int num= c2.getInt(0);
                    dni = c2.getInt(1);
                    String concepto = c2.getString(2);
                    Double valor = c2.getDouble(3);
                    addFactura(num+", "+dni+", "+concepto+", "+valor);

                } while(c2.moveToNext());// Iteramos sobre los resultados obtenidos.
            }else{
                Toast toast = Toast.makeText(this, "El cliente [dni="+dni+"] no tiene facturas", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();
            }
        }else{
            Toast.makeText(this, "Nada que mostrar", Toast.LENGTH_SHORT).show();
        }

    }

    public void addFactura(String contenido){
        factura = new LinearLayout(this);
            factura.setOrientation(LinearLayout.HORIZONTAL);
            factura.setPadding(0,0,0,15);

        TextView tx = new TextView(this);
        tx.setText(contenido);

        factura.addView(tx);
        listaFacturas.addView(factura);
    }

    public void volver(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void borrar(View view){
        //borar y actualizar layouts
        c = db.rawQuery("delete from Clientes", null);
        c2 = db2.rawQuery("delete from Facturas", null);
        if(c != null) {
            if (c.moveToFirst()) {
                Toast.makeText(this, "No se han borrado los clientes", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Se han borrado los clientes", Toast.LENGTH_SHORT).show();
            }
        }
        if(c2 != null) {
            if (c2.moveToFirst()) {
                Toast.makeText(this, "No se han borrado las facturas", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Se han borrado las facturas", Toast.LENGTH_SHORT).show();
            }
        }

        actualizarLayoutClientes();
        listaFacturas.removeAllViewsInLayout();

        Toast toast = Toast.makeText(this, "Datos borrados de la base de datos", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
    }
}

/*

Por cada registro en el cursor, cremos en tiempo real un layout con nuestros clientes


Cursor c = db.rawQuery(" SELECT NIA,nombre FROM Alumnos
WHERE nombre=‘FRANK' ", null);


        if(c != null) {
            if (c.moveToFirst()) {
                Toast.makeText(this, "No se han borrado los clientes", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Se han borrado los clientes", Toast.LENGTH_SHORT).show();
            }
        }
 */