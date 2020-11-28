package com.example.ejerciciosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

class PintaTriangulos extends View implements View.OnTouchListener
{
    int cordX,cordY;
    public PintaTriangulos(Context context)
    {
        super(context);
        setFocusable(true);
        this.setOnTouchListener(this);
    }

    protected void onDraw(Canvas canvas)
    {
        Paint pincel = new Paint();
        pincel.setStrokeWidth(3);
        canvas.drawLine(cordX,cordY,cordX+80,cordY,pincel);
        canvas.drawLine(cordX,cordY,cordX+40,cordY-70,pincel);
        canvas.drawLine(cordX+80,cordY,cordX+40,cordY-70,pincel);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        cordX = (int) event.getX();
        cordY = (int) event.getY();
        invalidate();
        return true;
    }

}
