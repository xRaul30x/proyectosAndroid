package com.example.buscaminas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Tablero extends View implements View.OnTouchListener {

    int cordX,cordY;

    public Tablero(Context context) {

        super(context);
        this.setOnTouchListener(this);
    }

    protected void onDraw(Canvas canvas){

        Paint pincel = new Paint();
        pincel.setARGB(255, 7, 59, 58);
        pincel.setStyle(Paint.Style.STROKE);

        int ancho = canvas.getWidth(); //1050
        int alto = canvas.getHeight(); //1050
        int x=0;
        int y=0;
        //int celdas = 8;

        //el ancho entre el total de casillas por el numero de iteracion por la que vamos es la coordenada de la linea que toca
        pincel.setStrokeWidth(3);
        for(int i = 0; i < 8; i++){ //verticales
            canvas.drawLine(ancho/8*i, 0, ancho/8*i, alto, pincel); //linea
        }

        for(int i = 0; i < 8; i++){ //horizontales
            canvas.drawLine(0, ancho/8*i, ancho, ancho/8*i, pincel); //linea
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        cordX = (int) event.getX();
        cordY = (int) event.getY();

        //cada casilla mide 131, asi que el total de pixeles es 8x131
        //por ejemplo, una casilla de columna 2 mide 2x131, asi que podemos saber en cual estamos si dividimos el total entre la unidad
        int columna = (cordX/131) + 1; //sumamos uno porque si no, nos dice cuantas casillas caben hasta donde tenemos el ratón, no la casilla en la que estamos
        int fila = (cordY/131) + 1;
        Toast toast = Toast.makeText(v.getContext(), columna+","+fila, Toast.LENGTH_SHORT);
        toast.show();

        invalidate();
        return true;
    }
}