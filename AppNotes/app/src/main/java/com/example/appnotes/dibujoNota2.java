package com.example.appnotes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

public class dibujoNota2 extends View{
    int ancho,alto;
    int unidad;
    int x,y;

    public dibujoNota2(Context context) {

        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        ancho = canvas.getWidth();
        unidad = ancho/4;

        pintarNotas(canvas);

    }


    public void pintarNotas(Canvas canvas){
        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 4; c++) {

                if (MainActivity.tieneNota[f][c]) { //si es una casilla con nota, la pintamos

                    x = (c*unidad); //las columnas se incrementan según se incrementa la x
                    y = (f*unidad);

                    Paint nota = new Paint();
                    nota.setARGB(255, 120, 120, 120);
                    canvas.drawRect(x+5, y+5,x+unidad-5,y+unidad-5,nota);
                    nota.setARGB(255, 190, 190, 190);
                    canvas.drawRect(x+5, y+(unidad/4),x+unidad-5,y+unidad-5,nota);

                }else{

                    x = (c*unidad); //las columnas se incrementan según se incrementa la x
                    y = (f*unidad);

                    Paint nota = new Paint();
                    nota.setARGB(255, 255, 255, 255);
                    canvas.drawRect(x+5, y+5,x+unidad-5,y+unidad-5,nota);
                    canvas.drawRect(x+5, y+(unidad/4),x+unidad-5,y+unidad-5,nota);
                }
            }
        }
    }


}

/*
public void establecerSiguiente(){

        for (int f = 0; f < 6; f++) { //filas
            for (int c = 0; c < 4; c++) { //columnas

                if(!MainActivity.tieneNota[f][c]){ //vamos buscando hasta encontrar la siguiente que no tenga nota

                    MainActivity.tieneNota[f][c] = true; //cuando la encontramos, colocamos una nota
                    return;
                }

            }
        }
    }


    dibujoNota drawNota = new dibujoNota(this);
            layout.addView(drawNota);
 */
