package com.example.buscaminasv3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


public class Tablero extends View implements View.OnTouchListener{

    int numFilas;
    int numColumnas;
    int tamTexto;
    int cordX,cordY;
    int ancho = 0;
    int alto = 0;
    int fila=0;
    int columna=0;
    int[][] casillas;
    boolean[][] pulsada;
    boolean[][] banderas;
    int bombas;
    int puntos = 0; //si los puntos llegan a 64-bombas (es decir destapar todas las casillas menos las bombas), has ganado
    int estadoDelJuego = 0;

    public Tablero(Context context, int numColumnas, int numFilas, int tamTexto, int bombas) {

        super(context);
        this.setOnTouchListener(this);

        this.bombas = bombas;
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.tamTexto = tamTexto;

        casillas = new int[numFilas][numColumnas];
        pulsada = new boolean[numFilas][numColumnas];
        banderas = new boolean[numFilas][numColumnas];

        //------------------inicializamos el tablero a 0 y las casillas pulsadas en false
        for (int f = 0; f < numFilas; f++) { //filas
            for (int c = 0; c < numColumnas; c++) { //columnas

                casillas[f][c] = 0;
                pulsada[f][c] = false;
                System.out.println(pulsada[f][c]);
                banderas[f][c] = false;
            }
        }

        //------------------colocamos las bombas
        for (int i = 0; i < bombas; i++) {

            int fr, cr; //fila random, columna random
            fr = (int)(Math.random()*numFilas);
            cr = (int)(Math.random()*numColumnas);

            if(casillas[fr][cr] != 10) {
                casillas[fr][cr] = 10;

            }else {

                i--; //lo volvemos a intentar
            }
        }

        //incrementamos el numero de cada casilla
        //si la casilla no es una bomba, miramos las bombas que tiene alrededor
        for (int f = 0; f < numFilas; f++) { //filas
            for (int c = 0; c < numColumnas; c++) { //columnas

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
        //pincel.setARGB(255, 169, 160, 137);
        //pincel.setColor(Color.LTGRAY);
        pincel.setStyle(Paint.Style.STROKE);

        ancho = canvas.getWidth(); //1050
        alto = canvas.getHeight(); //1050
        int unidad = ancho/numColumnas;

        //----------------------------------revisamos cada casilla para ver cuales podemos expandir y pintar
        for (int f = 0; f < numFilas; f++) { //filas
                for (int c = 0; c < numColumnas; c++) { //columnas

                expandir(); //miramos cada casilla y expandimos

                if(pulsada[f][c]){

                    Paint casilla = new Paint();
                    casilla.setStrokeWidth(2);
                    casilla.setARGB(255,20, 168, 118);//255, 192, 188, 181
                    int x = (c*unidad); //las columnas se incrementan según se incrementa la x
                    int y = (f*unidad);
                    canvas.drawRect(x, y,x+unidad,y+unidad,casilla);

                    Paint texto = new Paint();

                    if(casillas[f][c] == 10){ //bomba

                        casilla.setARGB(200,150, 0, 0);
                        texto.setColor(Color.BLACK);
                        texto.setTextSize(tamTexto);

                        canvas.drawRect(x, y,x+unidad,y+unidad,casilla);
                        canvas.drawText("X", x+(unidad/3), y+(unidad/2), texto);

                        estadoDelJuego = 1;

                    }else if(casillas[f][c] == 0){

                        texto.setColor(Color.WHITE);
                        texto.setTextSize(tamTexto);
                        canvas.drawText(" ", x+(unidad/3), y+(unidad/2), texto);

                    }else{ //no hay bomba ni se expande

                        if(casillas[f][c] == 1) texto.setARGB(255,54, 13, 183);
                        else if(casillas[f][c] == 2) texto.setARGB(255,0, 100, 0);
                        else if(casillas[f][c] == 3) texto.setARGB(255,92, 0, 41);
                        else texto.setColor(Color.MAGENTA);

                        texto.setTextSize(tamTexto);
                        canvas.drawText(casillas[f][c]+"", x+(unidad/3), y+(unidad/2), texto);

                    }
                }else if(banderas[f][c]){ //si la casilla no ha sido destapada, es que hemos puesto una bandera

                    int x = (c*unidad); //las columnas se incrementan según se incrementa la x
                    int y = (f*unidad);

                    //-------------------------------pintamos bandera
                    Paint bandera = new Paint();
                    bandera.setStyle(Paint.Style.FILL);

                    bandera.setColor(Color.RED);

                    canvas.drawRect(x+(unidad/4), y+(unidad/4),x+3*(unidad/4),y+5*(unidad/8),bandera);// D

                    bandera.setColor(Color.BLACK);

                    canvas.drawRect(x+(unidad/4), y+(unidad/4),x+3*(unidad/8),y+4*(unidad/4),bandera);// I


                }

            }
        }

        //----------------------------------pintamos las celdas por encima

        //pincel.setARGB(255, 255, 255, 255);
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(3);

        for(int i = 0; i < numFilas; i++){ //verticales
            canvas.drawLine(unidad*i, 0, unidad*i, alto, pincel); //linea
        }

        for(int i = 0; i < numColumnas; i++){ //horizontales
            canvas.drawLine(0, unidad*i, ancho, unidad*i, pincel); //linea
        }

        //-------------------------------------------------------------------------Sumar puntos
        for (int f = 0; f < numFilas; f++) { //filas
            for (int c = 0; c < numColumnas; c++) { //columnas
                if(pulsada[f][c]) puntos++;
            }
        }

        if(puntos == ((numFilas*numColumnas)-bombas)) estadoDelJuego = 2; //GANASTE!
        else puntos = 0; //si tras la comprobacion no tienes todos los puntos, aún no has ganado

        //-------------------------Estado del juego: si es distinto de 0, has ganado o has perdido
        switch (estadoDelJuego){
            default:
                break;
            case 1://GAME OVER

                //--Destapamos todas las bombas
                for (int f = 0; f < numFilas; f++) {
                    for (int c = 0; c < numColumnas; c++) {
                        if(casillas[f][c] == 10) pulsada[f][c] = true;
                    }
                }

                //--Pintamos el fondo de Game Over
                Paint paint2 = new Paint();
                //paint2.setColor(Color.BLACK);
                paint2.setARGB(170,0,0,0);
                //paint2.setStyle(Paint.Style.STROKE);
                canvas.drawPaint(paint2); //aplicamos fondo

                paint2.setARGB(255,216, 0, 0);
                paint2.setTextSize(150);
                canvas.drawText("GAME OVER", 70, 500, paint2);

                break;
            case 2: //--HAS GANADO

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
        for (int f = 0; f < numFilas; f++) { //filas
            for (int c = 0; c < numColumnas; c++) { //columnas

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

            fila = (cordY/(alto/numFilas)); //dividimos las coordenadas entre lo qe mide una casilla (ancho/8)
            columna = (cordX/(ancho/numColumnas));

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

            }else{ //modo normal (destapar casillas)

                if(!pulsada[fila][columna] && !banderas[fila][columna]){ //solo destapamos si no estaba pulsada y no habia una bandera

                    pulsada[fila][columna] = true;
                }


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