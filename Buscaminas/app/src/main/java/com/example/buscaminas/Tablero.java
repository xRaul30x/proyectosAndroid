package com.example.buscaminas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Tablero extends View implements View.OnTouchListener{

    int cordX,cordY;
    int fila=0;
    int columna=0;
    //Casilla[][] casillas = new Casilla[8][8];
    boolean[][] pulsada = new boolean[8][8];

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
        //int x=0;
        //int y=0;
        //int celdas = 8;

        //pintamos las celdas
        pincel.setStrokeWidth(3);
        for(int i = 0; i < 8; i++){ //verticales
            canvas.drawLine(ancho/8*i, 0, ancho/8*i, alto, pincel); //linea
        }

        for(int i = 0; i < 8; i++){ //horizontales
            canvas.drawLine(0, ancho/8*i, ancho, ancho/8*i, pincel); //linea
        }

        //Casilla casilla = new Casilla();
        //casillas[cordX/131][cordY/131] = casilla;
        //pintarCasilla(canvas);

        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                if(pulsada[f][c]){
                    Paint cuadrado = new Paint();
                    cuadrado.setStrokeWidth(2);
                    cuadrado.setARGB(255, 50, 50, 50);//255, 192, 188, 181
                    int x = (c*131); //las columnas se incrementan según se incrementa la x
                    int y = (f*131);
                    canvas.drawRect(x, y,x+131,y+131,cuadrado);
                }
            }
        }

        System.out.println(fila+","+columna);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        cordY = (int) event.getY();
        cordX = (int) event.getX();


        fila = (cordY/131);
        columna = (cordX/131);

        pulsada[fila][columna] = true;

        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                System.out.print("["+pulsada[f][c]+"]  ");
            }
            System.out.println("");
            System.out.println("");
        }
        System.out.println("---------------------------------------------------");

        invalidate();
        return true;
    }

}