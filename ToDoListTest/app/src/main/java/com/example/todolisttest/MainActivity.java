package com.example.todolisttest;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        layout = findViewById(R.id.layout);

        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = preferences.edit();

        actualizarPantalla(); //actualiza nuestro array a través de la bd y tambien los layouts

    }

    @Override
    protected void onResume() { //método que se llama cuando cambia el flujo de las actividades
        super.onResume();

        actualizarPantalla();
        if(lista == null) Toast.makeText(this, "Vaya, no tienes tareas!", Toast.LENGTH_SHORT).show();
    }

    public void actualizarPantalla(){
        actualizarArray();
        actualizarLayouts();
    }

    public void actualizarArray(){ //actualiza el array a través de la bd

        //al entrar o actualizar vaciamos los arrays
        lista = new ArrayList<>();
        listaChbx = new ArrayList<>();

        //cogemos las notas que tenemos en la bd
        listaToString = preferences.getString("listaToString",null);
        orden = preferences.getString("orden","Sin orden"); //sistema de ordenacion que haya elegido el usuario

        if(listaToString != null){

            String listaSplit[] = listaToString.split(",");

            //switch para ver que orden ha seleccionado el usuario
            switch (orden){
                case "Sin orden":

                    sinOrden(listaSplit);
                    break;
                case "Por colores":

                    ordenarPorColores(listaSplit);
                    break;
                case "Última modificación":

                    ultimaModificacion(listaSplit);
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
    private void sinOrden(String[] listaSplit) {

        for(int i=0;i<listaSplit.length;i++) {
            lista.add(listaSplit[i]);
        }

    }

    private void ordenarPorColores(String[] listaSplit) {

        for(int i=0;i<listaSplit.length;i++) {
            for(int j=0;j<listaSplit.length;j++) {
                if(listaSplit[j].substring(listaSplit[j].indexOf("#")+1).equals(String.valueOf(i))) {
                    lista.add(listaSplit[j]);
                }
            }
        }

    }

    private void ultimaModificacion(String[] listaSplit) {

        for(int i=0;i<listaSplit.length;i++) {
            lista.add(listaSplit[i]);
        }

    }

    private void alfabeticamente(String[] listaSplit) {

        for(int i=0;i<listaSplit.length;i++) {
            lista.add(listaSplit[i]);
        }

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

        String color = contenido.substring(contenido.indexOf('#')+1); //cogemos el color de contenido expresado en un numero

        //ponemos el color de fondo de las notas que haya elegido el usuario en la configuracion (calidos, frios, pastel)
        estilo = preferences.getString("estilo","Colores cálidos");
        colorDeFondo(color, ch);

        nuevoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editarNota(contenido.substring(0,contenido.indexOf('#'))); //en la base de datos cada nota se almacena con un # al final, pero estel usuario no lo ve
                editarNota(contenido);
            }
        });

        params.setMargins(0, 0, 0, 10); //distancia entre nota y nota

        tx.setText(contenido.substring(0,contenido.indexOf('#'))); //texto de la nota
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
                                borrarPorPos(i);
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
        }else{
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
        }
    }

    public void borrarPorPos(int pos){

        lista.remove(pos); //borramos en el array
        actualizarBD(); //actualizamos la bd a tarvés del array
        actualizarPantalla(); // actualizamos la pantalla

    }

    public void actualizarBD(){ //actualiza la bd a través del array

        if(lista.size() != 0){

            StringBuilder stbuilder = new StringBuilder(); //objeto para convertir el array en un string
            for(String nt: lista){
                stbuilder.append(nt);
                stbuilder.append(","); //cada nota esta separada por una coma
            }

            editor = preferences.edit(); //editor de preferencias
            editor.putString("listaToString", stbuilder.toString());

        }else{ //si la lista está vacía, vaciamos tambien la bd

            editor = preferences.edit().remove("listaToString");
        }

        editor.commit();

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

        alert.setTitle("Saliendo de ToDoList!");

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

    public void test(View view){
        listaToString = preferences.getString("listaToString",null);
        Toast.makeText(this, listaToString+"", Toast.LENGTH_LONG).show();
    }




}