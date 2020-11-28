package com.example.ejerciciosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class OvalosRectangulos extends View {
    public OvalosRectangulos(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255, 255, 255);
        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        Paint pincel = new Paint();

        pincel.setARGB(255, 0, 0, 0);
        pincel.setStrokeWidth(5);
        pincel.setStyle(Paint.Style.STROKE);

        RectF rectangulo1 = new RectF(0, 0, ancho, alto);
        canvas.drawOval(rectangulo1, pincel);

        pincel.setARGB(255, 255, 0, 0);
        canvas.drawRect(150, 250,ancho-150,alto-250,pincel);

        pincel.setARGB(255, 0, 0, 0);
        rectangulo1 = new RectF(150, 250,ancho-150,alto-250);
        canvas.drawOval(rectangulo1, pincel);

        pincel.setARGB(255, 0, 0, 255);
        canvas.drawRect(280, 390,ancho-280,alto-390,pincel);

        pincel.setARGB(255, 0, 0, 0);
        rectangulo1 = new RectF(280, 390,ancho-280,alto-390);
        canvas.drawOval(rectangulo1, pincel);
    }
}
