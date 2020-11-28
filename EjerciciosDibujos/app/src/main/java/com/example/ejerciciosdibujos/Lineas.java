package com.example.ejerciciosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Lineas extends View {


    public Lineas(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas){

        canvas.drawRGB(255,255,255); //fondo

        Paint pincel = new Paint();
        //pincel.setARGB(255,50,255,100);

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        pincel.setARGB(255, 0, 0, 0);


        //inicial
        int x = 5;
        int y = 5;
        int maxLineas = canvas.getHeight()/30;

        //Log.d("mensaje",""+maxLineas);

        for (int i = 0; i<maxLineas; i++){

            if(i%2==0){
                pincel.setStrokeWidth(1);
                canvas.drawLine(i*25, 0, i*25, alto, pincel); //linea
            }else{
                pincel.setStrokeWidth(3);
                canvas.drawLine(i*25, 0, i*25, alto, pincel); //linea
            }
        }

        for (int i = 0; i<5; i++){

            pincel.setARGB(255, 255, 0, 0);
            canvas.drawLine(0, (i^2)*150, ancho, (i^2)*150, pincel); //linea

        }

    }
}
