package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //Siempre que hagamos algo en la bd, lo hacemos tambien en el arraylist
    SharedPreferences preferences; SharedPreferences.Editor editor;
    List<String> lista;
    String listaToString;

    LinearLayout layout;
    LinearLayout nuevoLayout;

    //CheckBox[] ch;
    ArrayList<CheckBox> listaChbx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        layout = findViewById(R.id.layout);
        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = preferences.edit();

        actualizarPantalla(); //actualiza nuestro array a través de la bd y tambien los layouts
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

        if(listaToString != null){

            String listaSplit[] = listaToString.split(",");

            for(int i=0;i<listaSplit.length;i++){

                lista.add(listaSplit[i]); //añadimos al arraylist
            }

        }else{

            lista = null;
        }
    }

    public void actualizarLayouts(){ //actualiza los layout (las notas y los checkbox) a través del array actualizado

        layout.removeAllViewsInLayout();

        if(lista != null){

            for(int i=0;i<lista.size();i++){ //luego, añadimos los nuevos

                addNota(lista.get(i)); //añadir al layout
            }
        }
    }

    public void addNota(String contenido){

        nuevoLayout = new LinearLayout(this);
        layout.addView(nuevoLayout);

        nuevoLayout.setGravity(Gravity.LEFT);
        nuevoLayout.setOrientation(LinearLayout.HORIZONTAL);

        int id = lista.size()-1;
        nuevoLayout.setId(id); //su id es su posicion en la lista

        CheckBox ch = new CheckBox(this);
        TextView tx = new TextView(this);

        tx.setText(contenido);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //borrar pero por la pos de listaChbx
                for(int i = 0; i < listaChbx.size(); i++){

                    if(listaChbx.get(i).isChecked()){
                        borrarPorPos(i);
                    }
                }
            }
        });

        listaChbx.add(ch);

        nuevoLayout.addView(ch);
        nuevoLayout.addView(tx);
    }

    public void actualizarBD(){ //actualiza la bd a través del array

        if(lista != null){

            StringBuilder stbuilder = new StringBuilder(); //objeto para convertir el array en un string
            for(String nt: lista){
                stbuilder.append(nt);
                stbuilder.append(","); //cada nota esta separada por una coma
            }

            editor = preferences.edit(); //editor de preferencias
            editor.putString("listaToString", stbuilder.toString());

        }else{

            editor = preferences.edit().remove("listaToString");
        }

        editor.commit();

    }

    public void btnActualizar(View view){
        actualizarPantalla();
    }


    public void rellenarNuevaNota(View view){ //desde aqui rellenamos solo la base de datos

        Intent intent = new Intent(this, Agregar.class);
        startActivity(intent);
    }

    public void borrarBD(View view){
        //borramos de la base de datos y actualizamos
        editor = preferences.edit().remove("listaToString");
        editor.commit();

        actualizarPantalla();
    }

    public void borrarPorPos(int pos){

        //a raiz del array, sincronizamos la base de datos
        lista.remove(pos);
        actualizarBD();
        actualizarPantalla();

    }

    public void salir(View view){

        //if(lista != null) Toast.makeText(this, lista.toString(), Toast.LENGTH_SHORT).show();
        //else Toast.makeText(this, "Vacía", Toast.LENGTH_SHORT).show();
        finish();
    }


}

/*
    Contar hijos del layout: layout.getChildCount()
 */

/*

        Toast.makeText(this, "intentamos", Toast.LENGTH_SHORT).show();

        LinearLayout nuevoLayout = new LinearLayout(this);
        layout.addView(nuevoLayout);

        nuevoLayout.setGravity(Gravity.LEFT);
        nuevoLayout.setOrientation(LinearLayout.HORIZONTAL);

        CheckBox ch = new CheckBox(this);
        TextView tx = new TextView(this);

        tx.setText("Esto es un mensaje generado");

        nuevoLayout.addView(ch);
        nuevoLayout.addView(tx);


 */


/*

        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE); //cogemos la configuracion
        listaNotas = preferences.getStringSet("lista",new HashSet<String>()); //guardamos lo que haya en nuestro array

        SharedPreferences.Editor editor = preferences.edit(); //editor de preferencias
        editor.putStringSet("lista",aux); //aplastamos la lista que había en configuración
        editor.commit();

 */