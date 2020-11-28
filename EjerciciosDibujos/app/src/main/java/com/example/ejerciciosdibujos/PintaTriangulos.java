package com.example.ejerciciosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class PintaTriangulos extends View {
    public PintaTriangulos(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255, 255, 255);
        int corx;
        int cory;

        Paint pincel = new Paint();
        pincel.setARGB(255, 0, 0, 0);

        canvas.drawRGB(0,255,0);

        pincel.setARGB(255,255,0,0);
        pincel.setStrokeWidth(4);
        pincel.setStyle(Paint.Style.STROKE);


        //Tringle t = new Triangle();
        //canvas.drawTriangle(corx, cory, 20, pincel);

        pintado.setOnTouchListener((view, motionEvent) ->
                {
                    corx = (int) motionEvent.getX();
                    cory = (int) motionEvent.getY();
                    pintado.invalidate();return true;}
        );

    }
}
