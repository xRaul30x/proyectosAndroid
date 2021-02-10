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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //Siempre que hagamos algo en la bd, lo hacemos tambien en el arraylist
    SharedPreferences preferences; SharedPreferences.Editor editor;
    List<String> lista;
    String listaToString;

    LinearLayout layout;
    LinearLayout nuevoLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        layout = findViewById(R.id.layout);
        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        lista =  new ArrayList<>();

        sincronizarYactualizar(); //cogemos la bd, añadimos al arraylist y aplastamos la bd y actualizamos el layout
    }

    public void sincronizarYactualizar(){ //

        /*
        Miramos cuantas notas hay en la bd
        Por cada registro:
            Añadimos al arraylist
            Aplastamos la bd con el nuevo registro
            Creamos un layout con id "numero de registros-1"
                Creamos una checkbox por cada nota
                Rellenamos el mensaje que haya en la posición de cada nota en el array de la bd
         */

        //cogemos las notas que tenemos en la variable lista de la bd
        listaToString = preferences.getString("listaToString",null);

        //debemos sacar el contenido de cada nota para hacer varios addNota
        if(listaToString != null){
            String listaSplit[] = listaToString.split(",");

            //una vez tenemos el arraylist local sincronizado con el arraylist de la bd, podemos utilzarlo para hacer los controles en tiempo real
            for(int i=0;i<listaSplit.length;i++){

                sincronizarNota(listaSplit[i]); //añadir al arraylist y a la bd
                addNota(listaSplit[i]); //añadir al layout
            }
        }else{
            Toast.makeText(this, "Base de datos vacía", Toast.LENGTH_SHORT).show();
        }


        //Toast.makeText(this, "Notas en bd: "+notas.size(), Toast.LENGTH_SHORT).show();

    }

    public void sincronizarNota(String notaNueva){

        lista.add(notaNueva); //añadimos al arraylist

        StringBuilder stbuilder = new StringBuilder(); //objeto para convertir el array en un string
        for(String nt: lista){
            stbuilder.append(nt);
            stbuilder.append(","); //cada nota esta separada por una coma
        }

        editor = preferences.edit(); //editor de preferencias
        editor.putString("listaToString", stbuilder.toString());
        editor.commit();
    }

    public void addNota(String contenido){

        nuevoLayout = new LinearLayout(this);
        layout.addView(nuevoLayout);

        nuevoLayout.setGravity(Gravity.LEFT);
        nuevoLayout.setOrientation(LinearLayout.HORIZONTAL);

        CheckBox ch = new CheckBox(this);
        TextView tx = new TextView(this);

        tx.setText(contenido);

        nuevoLayout.addView(ch);
        nuevoLayout.addView(tx);
    }

    public void rellenarNota(View view){ //desde aqui rellenamos solo la base de datos

        Intent intent = new Intent(this, Agregar.class);
        startActivity(intent);
    }

    public void actualizar(View view){

        //llamamos a todos los metodos
        sincronizarYactualizar(); //recordatorio: este metodo coge los datos de la bd, rellena el arraylist y crea los controles
    }

    public void borrar(View view){
        //borramos de la base de datos y actualizamos to
        editor = preferences.edit().remove("listaToString"); //editor de preferencias
        editor.commit();

        layout.removeAllViewsInLayout();
    }

    //Borrar todas: layout.removeAllViewsInLayout();

    public void salir(View view){
        //Toast.makeText(this, preferences.getString("listaToString"," ")+"", Toast.LENGTH_SHORT).show();
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