package com.example.ejerciciosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class Rectangulos extends View {
    public Rectangulos(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255, 255, 255);
        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        Paint pincel = new Paint();
        pincel.setARGB(255, 0, 0, 0);

        canvas.drawRect(50, 150,ancho-50,alto-150,pincel);

        pincel.setARGB(255, 255, 255, 255);
        canvas.drawRect(100, 200,ancho-100,alto-200,pincel);

        pincel.setARGB(255, 0, 0, 0);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(2);
        canvas.drawRect(150, 250,ancho-150,alto-250,pincel);

        pincel.setARGB(255, 0, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawRect(350, 450,ancho-350,alto-450,pincel);
    }
}
