package com.example.ejerciciosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Espiral extends View {


    public Espiral(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255,255,255); //fondo

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        Paint pincel = new Paint();
        pincel.setARGB(255, 0, 0, 0);
        pincel.setStrokeWidth(1);

        int i = 0;
        while(i<50){
            i++;
            canvas.drawLine(5, 5, 5, 5, pincel);
        }
    }
}
