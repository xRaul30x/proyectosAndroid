package com.example.sqlite_ejercicio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class GestorClientes extends SQLiteOpenHelper {

    String sqlCrearClientes;
    //String sqlMostrarClientes = "SELECT * FROM Clientes";

    public GestorClientes(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        sqlCrearClientes = "CREATE TABLE Clientes (dni INTEGER PRIMARY KEY,nombre TEXT,direccion TEXT,tfno TEXT)";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Error",""+db.toString()+" en OnCreate");
        db.execSQL(sqlCrearClientes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL(sqlCrearClientes);
    }

    //public void visualizarClientes(SQLiteDatabase db){

        //db.execSQL(sqlMostrarClientes);

    //}
}
