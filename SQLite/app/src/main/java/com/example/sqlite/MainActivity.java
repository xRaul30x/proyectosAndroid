package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Abrimos la base de datos Alumnos
        AlumnosBBDD alumnosbbdd = new AlumnosBBDD(this, "DBAlumnos", null, 1);

        //La abrimos en modo escritura
        SQLiteDatabase db = alumnosbbdd.getWritableDatabase();
        //  Si solo queremos leerla llamaríamos a getReadableDatabase()

        //Si hemos abierto correctamente la base de datos, es decir, existe.
        if (db != null)
        {
            int nia;
            String nombre;
            //Generamos los datos
            for (int i = 1; i <= 5; i++)
            {

                nia = i;
                nombre = "Alumno" + i;
                //Insertamos los datos en la tabla Alumnos
                db.execSQL("INSERT INTO Alumnos (NIA, nombre) " + "VALUES (" + nia + ", '" + nombre + "')");
            }
            //Cerramos la base de datos
            db.close();
        }
    }
}