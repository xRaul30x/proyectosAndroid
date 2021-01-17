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

        //------------------inicializamos el tablero a 0 y las casillas pulsadas en false
        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas

                casillas[f][c] = 0;
                pulsada[f][c] = false;
                banderas[f][c] = false;
            }
        }

        //------------------colocamos las bombas
        for (int i = 0; i < bombas; i++) {

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


    } //fin constructor

    protected void onDraw(Canvas canvas){

        Paint pincel = new Paint();
        pincel.setARGB(255, 7, 59, 58);
        pincel.setStyle(Paint.Style.STROKE);

        ancho = canvas.getWidth(); //1050
        alto = canvas.getHeight(); //1050

        //----------------------------------pintamos las celdas
        pincel.setStrokeWidth(3);
        for(int i = 0; i < 8; i++){ //verticales
            canvas.drawLine(ancho/8*i, 0, ancho/8*i, alto, pincel); //linea
        }

        for(int i = 0; i < 8; i++){ //horizontales
            canvas.drawLine(0, ancho/8*i, ancho, ancho/8*i, pincel); //linea
        }

        //----------------------------------revisamos cada casilla para ver cuales podemos expandir y pintar
        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas

                expandir(); //miramos cada casilla y expandimos

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

                    //-------------------------------pintamos bandera
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

        //-------------------------------------------------------------------------Sumar puntos
        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas
                if(pulsada[f][c]) puntos++;
            }
        }

        if(puntos == (64-bombas)) estadoDelJuego = 2; //GANASTE!
        else puntos = 0; //si tras la comprobacion no tienes todos los puntos, aún no has ganado

        //-------------------------Estado del juego: si es distinto de 0, has ganado o has perdido
        switch (estadoDelJuego){
            default:
                break;
            case 1:
                Paint paint2 = new Paint();
                //paint2.setColor(Color.BLACK);
                paint2.setARGB(170,0,0,0);
                paint2.setStyle(Paint.Style.STROKE);
                canvas.drawPaint(paint2);

                paint2.setColor(Color.RED);
                paint2.setTextSize(150);
                canvas.drawText("GAME OVER", 70, 500, paint2);
                break;
            case 2:
                Paint paint = new Paint();
                //paint.setColor(Color.BLACK);
                paint.setARGB(170,0,0,0);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawPaint(paint);

                paint.setColor(Color.GREEN);
                paint.setTextSize(150);
                canvas.drawText("HAS GANADO", 50, 500, paint);
                break;

        }
    } //fin onDraw

    private void expandir() { //--------------------- expandir casillas
        //System.out.println("Intentamos expandir");

        //Revisamos todas las casillas por cada iteración
        for (int f = 0; f < 8; f++) { //filas
            for (int c = 0; c < 8; c++) { //columnas

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

    int veces = 0; //variable para que el valor de la bandera cambie una vez cuando bajamos el dedo (case 1) pero no vuelva a cambiar cuando lo levantamos (case 2)
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //-----------------------------------Si estado de juego es distinto de 0 es que has perdido o has ganado
        if(estadoDelJuego == 0){

            cordY = (int) event.getY();
            cordX = (int) event.getX();

            fila = (cordY/(alto/8)); //dividimos las coordenadas entre lo qe mide una casilla (ancho/8)
            columna = (cordX/(ancho/8));

            if(Game.modoBanderas){ //si estamos en modo banderas, no podemos pulsar casillas, solo pintar banderas

                if(!pulsada[fila][columna]){ //solo si la casilla no está pulsada podemos poder una bandera

                    veces++;
                    switch (veces){
                        case 1:
                            banderas[fila][columna] = !banderas[fila][columna];
                            break;
                        case 2:
                            veces = 0;
                            break;
                    }

                }

            }else{

                if(!pulsada[fila][columna]){

                    banderas[fila][columna] = false;
                }

                pulsada[fila][columna] = true;


            }

            //-----------destapamos alrededor de la casilla pulsada si contiene un número que no es 0 y si el número de la casilla coincide con el numero de banderas alrededor
            if(casillas[fila][columna] != 0 && pulsada[fila][columna]){

                //----comprobamos las 8 casillas de alrededor en busca de banderas
                int flagFound = 0;
                try{ if(banderas[fila-1][columna-1]) flagFound++;  }catch (Exception e){}
                try{ if(banderas[fila][columna-1]) flagFound++;  }catch (Exception e){}
                try{ if(banderas[fila+1][columna-1]) flagFound++;  }catch (Exception e){}
                try{ if(banderas[fila-1][columna]) flagFound++;  }catch (Exception e){}
                try{ if(banderas[fila+1][columna]) flagFound++;  }catch (Exception e){}
                try{ if(banderas[fila-1][columna+1]) flagFound++;  }catch (Exception e){}
                try{ if(banderas[fila][columna+1]) flagFound++;  }catch (Exception e){}
                try{ if(banderas[fila+1][columna+1]) flagFound++;  }catch (Exception e){}

                //----rellenamos las 8 casillas de alrededor que no sean banderas y que no estén ya pulsadas
                if(flagFound == casillas[fila][columna]){
                    try{ if(!banderas[fila-1][columna-1] && !pulsada[fila-1][columna-1]) pulsada[fila-1][columna-1]=true;  }catch (Exception e){}
                    try{ if(!banderas[fila][columna-1] && !pulsada[fila][columna-1]) pulsada[fila][columna-1]=true; }catch (Exception e){}
                    try{ if(!banderas[fila+1][columna-1] && !pulsada[fila+1][columna-1]) pulsada[fila+1][columna-1]=true;  }catch (Exception e){}
                    try{ if(!banderas[fila-1][columna] && !pulsada[fila-1][columna]) pulsada[fila-1][columna]=true;  }catch (Exception e){}
                    try{ if(!banderas[fila+1][columna] && !pulsada[fila+1][columna]) pulsada[fila+1][columna]=true;  }catch (Exception e){}
                    try{ if(!banderas[fila-1][columna+1] && !pulsada[fila-1][columna+1]) pulsada[fila-1][columna+1]=true;  }catch (Exception e){}
                    try{ if(!banderas[fila][columna+1] && !pulsada[fila][columna+1]) pulsada[fila][columna+1]=true;;  }catch (Exception e){}
                    try{ if(!banderas[fila+1][columna+1] && !pulsada[fila+1][columna+1]) pulsada[fila+1][columna+1]=true;  }catch (Exception e){}
                }
            }//fin if

        }//fin estado = 0


        invalidate();
        return true;
    }

}