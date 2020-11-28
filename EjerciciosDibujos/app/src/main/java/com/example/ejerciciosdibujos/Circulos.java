package com.example.ejerciciosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Circulos extends View {
    public Circulos(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255,255,255); //fondo

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        Paint pincel = new Paint();
        pincel.setARGB(255, 255, 0, 0);
        pincel.setStrokeWidth(3);
        pincel.setStyle(Paint.Style.STROKE);

        int i = 0;
        while(i<10){
            i++;
            canvas.drawCircle(ancho/2, alto/2, i*50,  pincel);
        }

    }
}


