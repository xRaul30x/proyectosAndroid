package com.example.buscaminasv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Tablero extends View implements View.OnTouchListener{

    int cordX,cordY;
    int fila=0;
    int columna=0;
    int[][] casillas = new int[8][8];
    boolean[][] pulsada = new boolean[8][8];

    public Tablero(Context context) {

        super(context);
        this.setOnTouchListener(this);

        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                casillas[f][c] = 0;
                pulsada[f][c] = false;
            }
        }

        for (int i = 0; i < 6; i++) { //6 bombas

            int fr, cr; //fila random, columna random
            fr = (int)(Math.random()*8);
            cr = (int)(Math.random()*8);

            if(casillas[fr][cr] != 10) {
                casillas[fr][cr] = 10;

            }else {

                i--; //lo volvemos a intentar
            }

        }

        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                try{ if(casillas[f][c]!=10) if(casillas[f-1][c-1] == 10) casillas[f][c]++; }catch (Exception e){}
                try{ if(casillas[f][c]!=10) if(casillas[f][c-1] == 10) casillas[f][c]++; }catch (Exception e){}
                try{ if(casillas[f][c]!=10) if(casillas[f+1][c-1] == 10) casillas[f][c]++; }catch (Exception e){}
                try{ if(casillas[f][c]!=10) if(casillas[f-1][c] == 10) casillas[f][c]++; }catch (Exception e){}
                try{ if(casillas[f][c]!=10) if(casillas[f+1][c] == 10) casillas[f][c]++; }catch (Exception e){}
                try{ if(casillas[f][c]!=10) if(casillas[f-1][c+1] == 10) casillas[f][c]++; }catch (Exception e){}
                try{ if(casillas[f][c]!=10) if(casillas[f][c+1] == 10) casillas[f][c]++; }catch (Exception e){}
                try{ if(casillas[f][c]!=10) if(casillas[f+1][c+1] == 10) casillas[f][c]++; }catch (Exception e){}
            }
        }


    }

    protected void onDraw(Canvas canvas){

        Paint pincel = new Paint();
        pincel.setARGB(255, 7, 59, 58);
        pincel.setStyle(Paint.Style.STROKE);

        int ancho = canvas.getWidth(); //1050
        int alto = canvas.getHeight(); //1050

        //pintamos las celdas
        pincel.setStrokeWidth(3);
        for(int i = 0; i < 8; i++){ //verticales
            canvas.drawLine(ancho/8*i, 0, ancho/8*i, alto, pincel); //linea
        }

        for(int i = 0; i < 8; i++){ //horizontales
            canvas.drawLine(0, ancho/8*i, ancho, ancho/8*i, pincel); //linea
        }


        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                if(pulsada[f][c]){
                    Paint cuadrado = new Paint();
                    cuadrado.setStrokeWidth(2);
                    cuadrado.setARGB(255, 50, 50, 50);//255, 192, 188, 181
                    int x = (c*131); //las columnas se incrementan según se incrementa la x
                    int y = (f*131);
                    canvas.drawRect(x, y,x+131,y+131,cuadrado);

                    Paint texto = new Paint();

                    if(casillas[f][c] == 10){
                        //GameOver
                        //break;
                        texto.setColor(Color.RED);
                        texto.setTextSize(50);
                        canvas.drawText("X", c*131+50, f*131+100, texto);
                    }else if(casillas[f][c] == 0){
                        //expandir
                        texto.setColor(Color.WHITE);
                        texto.setTextSize(50);
                        canvas.drawText(" ", c*131+50, f*131+100, texto);
                    }else{
                        texto.setColor(Color.WHITE);
                        texto.setTextSize(50);
                        canvas.drawText(casillas[f][c]+"", c*131+50, f*131+100, texto);

                    }
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

        invalidate();
        return true;
    }

}