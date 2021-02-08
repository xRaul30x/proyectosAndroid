package com.example.sqlite_ejercicio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class CrearClientes extends SQLiteOpenHelper {

    String sqlTablaClientes = "CREATE TABLE Clientes (dni INTEGER PRIMARY KEY,nombre TEXT,direccion TEXT,tfno TEXT)";

    public CrearClientes(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Error",""+db.toString()+" en OnCreate");
        db.execSQL(sqlTablaClientes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL(sqlTablaClientes);
    }
}
