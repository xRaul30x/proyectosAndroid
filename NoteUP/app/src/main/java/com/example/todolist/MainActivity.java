package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //Siempre que hagamos algo en la bd, lo hacemos tambien en el arraylist
    SharedPreferences preferences; SharedPreferences.Editor editor;
    List<String> lista;
    String listaToString;
    ArrayList<CheckBox> listaChbx;
    String estilo, orden;

    LinearLayout layout;
    LinearLayout nuevoLayout;
    LinearLayout.LayoutParams params;

    String separador = "&"; //separador nota
    String separadorColor = "#"; //separador nota-color

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        layout = findViewById(R.id.layout);

        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = preferences.edit();

        try{
            actualizarPantalla(); //actualiza nuestro array a través de la bd y tambien los layouts
        }catch(Exception e){}
    }

    @Override
    protected void onResume() { //método que se llama cuando cambia el flujo de las actividades
        super.onResume();

        try{
            actualizarPantalla(); //actualiza nuestro array a través de la bd y tambien los layouts
        }catch(Exception e){}

        comprobarSiEstaVacia();

    }

    private void comprobarSiEstaVacia() {

        String listaToStringAux = preferences.getString("listaToString",null);
        if(listaToStringAux == null || listaToStringAux == "") Toast.makeText(this, "Vaya! No tienes tareas...", Toast.LENGTH_SHORT).show();
    }

    public void actualizarPantalla(){

        try{
            actualizarArray();
            actualizarLayouts();
        }catch(Exception e){}

    }

    public void actualizarArray(){ //actualiza el array a través de la bd

        lista = new ArrayList<>();
        listaChbx = new ArrayList<>();

        //cogemos las notas que tenemos en la bd
        listaToString = preferences.getString("listaToString",null);
        orden = preferences.getString("orden","Última modificación"); //sistema de ordenacion que haya elegido el usuario

        if(listaToString != null){

            String listaSplit[] = listaToString.split(separador);

            //switch para ver que orden ha seleccionado el usuario
            switch (orden){
                case "Última modificación":

                    colocarPorUltMod(listaSplit);
                    break;
                case "Por colores":

                    ordenarPorColores(listaSplit);
                    break;
                case "Alfabéticamente":

                    alfabeticamente(listaSplit);
                    break;
            }

        }else{

            lista = null;
        }
    }

    //------------------------------------------------MÉTODOS DE ORDENACIÓN

    private void ordenarPorColores(String[] listaSplit) {


        for(int j=0;j<5;j++) { //por cada color empezando por el 0, recorremos las notas en busca de estos
            for(int i=0;i<listaSplit.length;i++) {

                if(listaSplit[i].substring(listaSplit[i].indexOf(separadorColor)+1).equals(String.valueOf(j))) { //si la nota 1 tiene el color 0, se coloca, si no se evalua la siguiente

                    lista.add(listaSplit[i]);
                }
            }
        }

        //Toast.makeText(this, lista.toString()+"", Toast.LENGTH_SHORT).show();

    }

    private void colocarPorUltMod(String[] listaSplit) {

        for(int i=0;i<listaSplit.length;i++) {
            lista.add(listaSplit[i]);
        }

    }

    private void alfabeticamente(String[] listaSplit) {

        for(int i=0;i<listaSplit.length;i++) {
            lista.add(listaSplit[i]);
        }
        Collections.sort(lista);

    }
    //------------------------------------------------FIN MÉTODOS DE ORDENACIÓN

    public void actualizarLayouts(){ //actualiza los layout (las notas y los checkbox) a través del array actualizado

        layout.removeAllViewsInLayout();

        if(lista != null){

            for(int i=0;i<lista.size();i++){ //luego, añadimos los nuevos

                addNota(lista.get(i)); //añadir al layout
            }
        }
    }

    public void addNota(final String contenido){

        nuevoLayout = new LinearLayout(this);
        CheckBox ch = new CheckBox(this);
        TextView tx = new TextView(this);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layout.addView(nuevoLayout, params);

        nuevoLayout.setOrientation(LinearLayout.HORIZONTAL);

        String color = contenido.substring(contenido.indexOf(separadorColor)+1); //cogemos el color de contenido expresado en un numero

        //ponemos el color de fondo de las notas que haya elegido el usuario en la configuracion (calidos, frios, pastel)
        estilo = preferences.getString("estilo","Colores cálidos");
        colorDeFondo(color, ch);

        nuevoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editarNota(contenido.substring(0,contenido.indexOf(separadorColor))); //en la base de datos cada nota se almacena con un # al final, pero estel usuario no lo ve
                editarNota(contenido);
            }
        });

        params.setMargins(0, 0, 0, 10); //distancia entre nota y nota

        tx.setText(contenido.substring(0,contenido.indexOf(separadorColor))); //texto de la nota
        tx.setPadding(20,0,0,25);

        //ch.setBackgroundResource(R.color.chDefault);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        for(int i = 0; i < listaChbx.size(); i++){

                            if(listaChbx.get(i).isChecked()){
                                borrarPorContenido(contenido);
                            }
                        }
                    }
                }, 1000);

            }
        });

        listaChbx.add(ch);

        nuevoLayout.addView(ch);
        nuevoLayout.addView(tx);
    }

    public void colorDeFondo(String color, CheckBox ch){

        if(estilo.equals("Colores cálidos")) {
            switch (color) {
                case "0":
                    nuevoLayout.setBackgroundResource(R.color.rojo);
                    ch.setBackgroundResource(R.color.chRojo);
                    break;
                case "1":
                    nuevoLayout.setBackgroundResource(R.color.naranja);
                    ch.setBackgroundResource(R.color.chNaranja);
                    break;
                case "2":
                    nuevoLayout.setBackgroundResource(R.color.amarillo);
                    ch.setBackgroundResource(R.color.chAmarillo);
                    break;
                case "3":
                    nuevoLayout.setBackgroundResource(R.color.blanco);
                    ch.setBackgroundResource(R.color.chBlanco);
                    break;
                case "4":
                    nuevoLayout.setBackgroundResource(R.color.gris);
                    ch.setBackgroundResource(R.color.chGris);
                    break;
            }
        }else if(estilo.equals("Colores fríos")){
            switch (color) {
                case "0":
                    nuevoLayout.setBackgroundResource(R.color.purpura);
                    ch.setBackgroundResource(R.color.chPurpura);
                    break;
                case "1":
                    nuevoLayout.setBackgroundResource(R.color.azul);
                    ch.setBackgroundResource(R.color.chAzul);
                    break;
                case "2":
                    nuevoLayout.setBackgroundResource(R.color.celeste);
                    ch.setBackgroundResource(R.color.chCeleste);
                    break;
                case "3":
                    nuevoLayout.setBackgroundResource(R.color.blanco);
                    ch.setBackgroundResource(R.color.chBlanco);
                    break;
                case "4":
                    nuevoLayout.setBackgroundResource(R.color.gris);
                    ch.setBackgroundResource(R.color.chGris);
                    break;
            }
        }else{
            switch (color) {
                case "0":
                    nuevoLayout.setBackgroundResource(R.color.pastel1);
                    ch.setBackgroundResource(R.color.pastel1);
                    break;
                case "1":
                    nuevoLayout.setBackgroundResource(R.color.pastel2);
                    ch.setBackgroundResource(R.color.pastel2);
                    break;
                case "2":
                    nuevoLayout.setBackgroundResource(R.color.pastel3);
                    ch.setBackgroundResource(R.color.pastel3);
                    break;
                case "3":
                    nuevoLayout.setBackgroundResource(R.color.pastel4);
                    ch.setBackgroundResource(R.color.pastel4);
                    break;
                case "4":
                    nuevoLayout.setBackgroundResource(R.color.pastel5);
                    ch.setBackgroundResource(R.color.pastel5);
                    break;
            }
        }
    }

    public void borrarPorContenido(String contenido){

        borrarEnBD(contenido); //actualizamos la bd
        actualizarPantalla(); // actualizamos la pantalla para actualizar el array y la pantalla

    }

    public void borrarEnBD(String contenido){ //actualiza la bd a través del array

        if(lista.size() != 0){

            listaToString = listaToString.replace(contenido+separador,""); //reemplazamos el contenido por un blanco

            editor = preferences.edit(); //editor de preferencias
            editor.putString("listaToString", listaToString);

        }else{ //si la lista está vacía, vaciamos tambien la bd

            editor = preferences.edit().remove("listaToString");
        }

        editor.commit();
        comprobarSiEstaVacia();

    }

    public void btnConfig(View view){
        Intent intent = new Intent(this, Configuracion.class);
        startActivity(intent);
    }


    public void rellenarNuevaNota(View view){ //desde aqui rellenamos solo la base de datos

        Intent intent = new Intent(this, Agregar.class);
        startActivity(intent);
    }

    public void editarNota(String contenidoEdit){ //desde aqui rellenamos solo la base de datos

        Intent intent = new Intent(this, Agregar.class);
        intent.putExtra("contenidoEdit", contenidoEdit);
        startActivity(intent);
    }


    public void borrarBD(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        //alert.setTitle("Borrando!");

        alert.setMessage("Seguro que quieres borrar todas las notas?");

        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //borramos de la base de datos y actualizamos
                editor = preferences.edit().remove("listaToString");
                editor.commit();

                actualizarPantalla();
                comprobarSiEstaVacia();
            }
        });

        alert.setNeutralButton("Cancelar", null);

        alert.show();

    }

    public void salir(View view){ //botón SALIR de la pantalla
        salir();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) { //botón atrás del menu de navegación del movil

        if(keyCode == KeyEvent.KEYCODE_BACK){

            salir();

            return true;
        }else{
            return super.onKeyUp(keyCode, event);
        }
    }

    public void salir(){

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        alert.setTitle("Saliendo de Note UP!");

        alert.setMessage("Estás seguro que quieres salir?");

        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        alert.setNeutralButton("Cancelar", null);

        alert.show();

    }




}