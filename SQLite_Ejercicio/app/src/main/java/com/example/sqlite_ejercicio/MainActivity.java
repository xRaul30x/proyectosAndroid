package com.example.sqlite_ejercicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre, dni, direccion, telefono;
    EditText num, dni2, concepto, valor;

    GestorClientes clientes;
    GestorFacturas facturas;

    SQLiteDatabase db;
    SQLiteDatabase db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //clientes = new GestorClientes(this, "clientes", null, 1);
        //facturas = new GestorFacturas(this, "facturas", null, 1);
        //ConsultarFacturas consultarFacturas = new ConsultarFacturas(this, "ConsultarFacturas", null, 1);

        //db = clientes.getWritableDatabase();
        //db2 = facturas.getWritableDatabase();
        //SQLiteDatabase db3 = consultarFacturas.getReadableDatabase();

        nombre = (EditText)findViewById(R.id.nombre);
        dni = (EditText)findViewById(R.id.dni);
        direccion = (EditText)findViewById(R.id.direccion);
        telefono = (EditText)findViewById(R.id.telefono);

        num = (EditText)findViewById(R.id.num);
        dni2 = (EditText)findViewById(R.id.dni2);
        concepto = (EditText)findViewById(R.id.concepto);
        valor = (EditText)findViewById(R.id.valor);

    }

    public void guardarCliente(View view) {
        clientes = new GestorClientes(this, "clientes", null, 1);

        db = clientes.getWritableDatabase();

        if (db != null) {

            try {

                int dniInt = Integer.parseInt(dni.getText().toString());
                String nombreSt = nombre.getText().toString();
                String direccionSt = direccion.getText().toString();
                String telefonoSt = telefono.getText().toString();

                db.execSQL("INSERT INTO Clientes (dni, nombre, direccion, tfno) " + "VALUES(" + dniInt + ", '" + nombreSt + "', '" + direccionSt + "', '" + telefonoSt + "')");
                db.close();

                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();

                //reinciamos las textbox
                dni.setText("");
                nombre.setText("");
                direccion.setText("");
                telefono.setText("");

            }catch(Exception e){

                e.printStackTrace();
                Log.e("error","error");
                Toast.makeText(this, "Faltan datos por rellenar...", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void guardarFactura(View view) { //num INTEGER PRIMARY KEY,dni INTEGER,concepto TEXT,valor DOUBLE

        facturas = new GestorFacturas(this, "facturas", null, 1);

        db2 = facturas.getWritableDatabase();

        if (db2 != null) {
            try {

                int numInt = Integer.parseInt(num.getText().toString());
                int dni2Int = Integer.parseInt(dni2.getText().toString());
                String conceptoString = concepto.getText().toString();
                double valorDbl = Double.parseDouble(valor.getText().toString());

                if(buscarClientePorDni(dni2Int)){
                    db2.execSQL("INSERT INTO Facturas (num, dni, concepto, valor) " + "VALUES("+numInt+", "+dni2Int+", '"+conceptoString+"', "+valorDbl+")");
                    db2.close();

                    Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();

                    num.setText("");
                    dni2.setText("");
                    concepto.setText("");
                    valor.setText("");
                }else{

                    Toast.makeText(this, "No hay clientes con ese dni", Toast.LENGTH_SHORT).show();
                }


            }catch(Exception e){

                e.printStackTrace();
                Log.e("error","error");
                Toast.makeText(this, "Faltan datos por rellenar o el dni estaba duplicado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean buscarClientePorDni(int dni){
            clientes = new GestorClientes(this, "clientes", null, 1);
            boolean encontrado = false;

            db = clientes.getReadableDatabase();

            if (db != null) {

                try {

                    Cursor c = db.rawQuery("SELECT * FROM Clientes WHERE dni='"+dni+"'",null);

                    if(c.moveToFirst()){ //si podemos encontrar el primer dato en el cursor entonces return true

                        return true;
                    }else{

                        return false;
                    }

                }catch(Exception e){

                    e.printStackTrace();
                    Log.e("error","error");
                    Toast.makeText(this, "Error al buscar el cliente...", Toast.LENGTH_SHORT).show();
                }
            }

            return encontrado;
    }

    public void visualizar(View view) {

        Intent intent = new Intent(this, Visualizar.class);
        startActivity(intent);
    }

    public void salir(View view) {

        finish();
    }
}