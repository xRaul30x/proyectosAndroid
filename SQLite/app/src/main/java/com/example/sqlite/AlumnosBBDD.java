package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlumnosBBDD extends SQLiteOpenHelper
{

    //Sentencia SQL para crear la tabla de Alumnos
    String sqlCreate = "CREATE TABLE Alumnos (NIA INTEGER, nombre TEXT)";

    public AlumnosBBDD(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(contexto, nombre, factory, version);
        /* Cuando creamos un objeto de este tipo:
                  1) Si la base de datos ya existe y su versión actual coincide con la solicitada simplemente se realizará la conexión con ella.
                  2) Si la base de datos existe pero su versión actual es anterior a la solicitada, se llamará automáticamente al método onUpgrade() para convertir la base de datos a la nueva versión y se conectará con la base de datos convertida.
                  3) Si la base de datos no existe, se llamará automáticamente al método onCreate() para crearla y se conectará con la base de datos creada.
        */
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva)
    {
        //Se elimina la versión anterior de la tabla
        //Ojo, haciendo esto eliminas también TODOS los registros, habría que pensar en un volcado previo.
        db.execSQL("DROP TABLE IF EXISTS Alumnos");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}
