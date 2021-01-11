package com.example.buscaminas;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;


public class Buscaminas extends AppCompatActivity{// implements View.OnTouchListener {

    int cordX,cordY;
    int[][] casillas = new int[8][8];
    //Casilla[][] Casilla = new Casilla[8][8];
    //boolean[][] pulsada = new boolean[8][8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscaminas_layout);

        RelativeLayout layout = findViewById(R.id.layout);

        Tablero pintarTablero = new Tablero(this);

        layout.addView(pintarTablero);
        //layout.setOnTouchListener(this);

        //rellenamos cada casilla con ceros (asi solo tenemos que incrementar este numero cada vez que encontramos una bomba)
        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                casillas[f][c] = 0;
                //pulsada[f][c] = false;
            }
        }

        //colocamos las bombas, representadas por el número 10
        for (int i = 0; i < 6; i++) { //6 bombas

            int fr, cr; //fila random, columna random
            fr = (int)(Math.random()*8);
            cr = (int)(Math.random()*8);

            if(casillas[fr][cr] != 10) {
                casillas[fr][cr] = 10;
                //Casilla[fr][cr] = new Casilla(fr,cr,10,false);
                //System.out.println("bomba colocada en ["+fr+","+cr+"]");
            }else {
                //System.out.println("(!!) ya habia bomba en ["+fr+","+cr+"]");
                i--; //lo volvemos a intentar
            }

        }

        //rellenamos cada casilla casillas[f][c]
        //tenemos que evaluar cada una de las 8 casillas que hay alrededor de cada una en busca de bombas
        //cada vez que una casilla de alrededor tenga bomba, incrementamos el valor de la casilla del medio
        //capturamos ArrayIndexOutOfBoundsException, que va a saltar cuando intentemos evaluar una casilla fuera del tablero

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

        //visualización

        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                System.out.print("["+casillas[f][c]+"]  ");
            }
            System.out.println();System.out.println();
        }

        /*for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                System.out.print("["+pulsada[f][c]+"]  ");
            }
            System.out.println();System.out.println();
        }*/

    }

    public void volver(View view){
        Intent intent = new Intent(view.getContext(),MenuPrincipal.class);
        startActivity(intent);
    }



}
