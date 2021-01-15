package com.example.buscaminasv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


public class Tablero extends View implements View.OnTouchListener{

    int cordX,cordY;
    int ancho = 0;
    int alto = 0;
    int fila=0;
    int columna=0;
    int[][] casillas = new int[8][8];
    boolean[][] pulsada = new boolean[8][8];
    boolean[][] banderas = new boolean[8][8];
    int bombas = 6;
    int puntos = 0; //si los puntos llegan a 64-bombas (es decir destapar todas las casillas menos las bombas), has ganado
    int estadoDelJuego = 0;

    public Tablero(Context context) {

        super(context);
        this.setOnTouchListener(this);

        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                casillas[f][c] = 0;
                pulsada[f][c] = false;
                banderas[f][c] = false;
            }
        }

        for (int i = 0; i < bombas; i++) { //7 bombas

            int fr, cr; //fila random, columna random
            fr = (int)(Math.random()*8);
            cr = (int)(Math.random()*8);

            if(casillas[fr][cr] != 10) {
                casillas[fr][cr] = 10;

            }else {

                i--; //lo volvemos a intentar
            }
        }

        //incrementamos el numero de cada casilla
        //si la casilla no es una bomba, miramos las bombas que tiene alrededor
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

        ancho = canvas.getWidth(); //1050
        alto = canvas.getHeight(); //1050

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

                expandir(f,c); //miramos cada casilla y expandimos

                if(pulsada[f][c]){

                    Paint casilla = new Paint();
                    casilla.setStrokeWidth(2);
                    casilla.setARGB(255, 50, 50, 50);//255, 192, 188, 181
                    int x = (c*(ancho/8)); //las columnas se incrementan según se incrementa la x
                    int y = (f*(alto/8));
                    canvas.drawRect(x, y,x+(ancho/8),y+(alto/8),casilla);

                    Paint texto = new Paint();

                    if(casillas[f][c] == 10){ //bomba

                        texto.setColor(Color.RED);
                        texto.setTextSize(50);
                        canvas.drawText("X", c*(ancho/8)+50, f*(alto/8)+100, texto);

                        estadoDelJuego = 1;
                        break;

                    }else if(casillas[f][c] == 0){

                        texto.setColor(Color.WHITE);
                        texto.setTextSize(50);
                        canvas.drawText(" ", c*(ancho/8)+50, f*(alto/8)+100, texto);

                    }else{ //no hay bomba ni se expande

                        texto.setColor(Color.WHITE);
                        texto.setTextSize(50);
                        canvas.drawText(casillas[f][c]+"", c*(ancho/8)+50, f*(alto/8)+100, texto);

                    }
                }else if(banderas[f][c]){ //si la casilla no ha sido destapada, es que hemos puesto una bandera

                    int x = (c*(ancho/8)); //las columnas se incrementan según se incrementa la x
                    int y = (f*(alto/8));

                    //pintamos bandera


                    Paint bandera = new Paint();
                    bandera.setStyle(Paint.Style.FILL);
                    bandera.setColor(Color.BLACK);

                    canvas.drawRect(x+ancho/32, y+alto/32,x+(ancho/64),y+4*(alto/32),bandera);// I

                    bandera.setStrokeWidth(2);

                    canvas.drawLine(x+ancho/32,y+alto/32,x+3*ancho/32,y+2*alto/32,pincel);//  \
                    canvas.drawLine(x+ancho/32,y+3*alto/32,x+3*ancho/32,y+2*alto/32,pincel);//  /

                }

            }
        }

        //-------------------------------------------------------------------------método de sumar puntos
        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                if(pulsada[f][c]) puntos++;
            }
        }

        if(puntos == (64-bombas)) estadoDelJuego = 2; //GANASTE!
        else puntos = 0; //si tras la comprobacion no tienes todos los puntos, aún no has ganado


        //
        switch (estadoDelJuego){
            case 0:

                break;
            case 1:
                Paint paint2 = new Paint();
                paint2.setColor(Color.BLACK);
                //paint2.setARGB(200,0,0,0);
                paint2.setStyle(Paint.Style.STROKE);
                canvas.drawPaint(paint2);

                paint2.setColor(Color.RED);
                paint2.setTextSize(150);
                canvas.drawText("GAME OVER", 70, 500, paint2);
                break;
            case 2:
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawPaint(paint);

                paint.setColor(Color.GREEN);
                paint.setTextSize(150);
                canvas.drawText("HAS GANADO", 50, 500, paint);
                break;

        }
    }

    private void expandir(int f, int c) { //--------------------- expandir casillas
        System.out.println("Intentamos expandir");

        for (f = 0; f < 8; f++) { //filas
            for (c = 0; c < 8; c++) { //columnas
                if(pulsada[f][c] && casillas[f][c] == 0){

                        try{ pulsada[f-1][c-1] = true;  }catch (Exception e){}
                        try{ pulsada[f][c-1] = true;  }catch (Exception e){}
                        try{ pulsada[f+1][c-1] = true;  }catch (Exception e){}
                        try{ pulsada[f-1][c] = true;  }catch (Exception e){}
                        try{ pulsada[f+1][c] = true;  }catch (Exception e){}
                        try{ pulsada[f-1][c+1] = true;  }catch (Exception e){}
                        try{ pulsada[f][c+1] = true;  }catch (Exception e){}
                        try{ pulsada[f+1][c+1] = true;  }catch (Exception e){}

                }
            }
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        cordY = (int) event.getY();
        cordX = (int) event.getX();


        fila = (cordY/(alto/8)); //dividimos las coordenadas entre lo qe mide una casilla (ancho/8)
        columna = (cordX/(ancho/8));

        if(Game.isModoBanderas()){ //si estamos en modo banderas, no podemos pulsar casillas, solo pintar banderas

            banderas[fila][columna] = true;
        }else{

            pulsada[fila][columna] = true;
        }


        invalidate();
        return true;
    }

}