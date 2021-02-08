package com.example.sqlite_ejercicio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CrearFacturas extends SQLiteOpenHelper {

    String sqlTablaFacturas = "CREATE TABLE Facturas (num INTEGER PRIMARY KEY,dni INTEGER,concepto TEXT,valor DOUBLE)";

    public CrearFacturas(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


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
}
