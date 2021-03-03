package com.example.sqlite_ejercicio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestorFacturas extends SQLiteOpenHelper {

    String sqlTablaFacturas;
    String sqlMostrarFacturas;

    public GestorFacturas(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        sqlTablaFacturas = "CREATE TABLE Facturas (num INTEGER PRIMARY KEY,dni INTEGER,concepto TEXT,valor DOUBLE)";
        sqlMostrarFacturas = "SELECT * FROM Facturas";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlTablaFacturas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Facturas");
        db.execSQL(sqlTablaFacturas);
    }

    public void visualizarFacturas(SQLiteDatabase db){

        db.execSQL(sqlMostrarFacturas);
    }
}
