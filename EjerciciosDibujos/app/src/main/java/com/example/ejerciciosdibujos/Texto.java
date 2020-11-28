package com.example.ejerciciosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class Texto extends View {


    public Texto(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas){

        canvas.drawRGB(255,255,255); //fondo

        Paint pincel = new Paint();
        //pincel.setARGB(255,50,255,100);

        pincel.setARGB(255, 255, 0, 0);
        pincel.setTextSize(50);
        Typeface tf = Typeface.create(Typeface.SERIF,Typeface.ITALIC);
        pincel.setTypeface(tf);
        canvas.drawText("El tiempo todo lo cura menos vejez y locura", 50, 150, pincel);

        pincel.setARGB(255, 0, 100, 100);
        pincel.setTextSize(60);
        tf = Typeface.create(Typeface.MONOSPACE,Typeface.BOLD);
        pincel.setTypeface(tf);
        canvas.drawText("Haz bien y no mires a quien", 50, 250, pincel);

        pincel.setARGB(255, 100, 20, 100);
        pincel.setTextSize(35);
        tf = Typeface.create(Typeface.SERIF,Typeface.BOLD_ITALIC);
        pincel.setTypeface(tf);
        canvas.drawText("Con la barriga vacía, ninguno muestra alegría”", 50, 350, pincel);

        pincel.setARGB(255, 150, 20, 35);
        pincel.setTextSize(70);
        tf = Typeface.create(Typeface.SANS_SERIF,Typeface.NORMAL);
        pincel.setTypeface(tf);
        canvas.drawText("Cortesía de boca, mucho gana y poco cuesta", 50, 450, pincel);

    }
}
