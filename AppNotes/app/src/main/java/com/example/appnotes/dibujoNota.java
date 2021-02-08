package com.example.appnotes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class dibujoNota extends View{
    int ancho,alto;
    int unidad;
    int x,y;
    int f,c;

    public dibujoNota(Context context, int fila, int columna) {

        super(context);

        this.f=fila;
        this.c=columna;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        ancho = canvas.getWidth();
        unidad = ancho/4;

        x = (c*unidad); //las columnas se incrementan según se incrementa la x
        y = (f*unidad);

        Paint nota = new Paint();
        nota.setARGB(255, 120, 120, 120);
        canvas.drawRect(x+5, y+5,x+unidad-5,y+unidad-5,nota);
        nota.setARGB(255, 190, 190, 190);
        canvas.drawRect(x+5, y+(unidad/4),x+unidad-5,y+unidad-5,nota);

    }


}

