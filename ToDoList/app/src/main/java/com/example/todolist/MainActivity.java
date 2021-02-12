package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
    ArrayList<CheckBox> listaChbx;

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
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(nuevoLayout, params);

        nuevoLayout.setOrientation(LinearLayout.HORIZONTAL);
        nuevoLayout.setBackgroundResource(R.color.white);

        params.setMargins(0, 0, 0, 10); //distancia entre nota y nota

        CheckBox ch = new CheckBox(this);
        TextView tx = new TextView(this);

        tx.setText(contenido); //texto de la nota
        tx.setPadding(0,0,0,25); //el texto no se cortará por abajo

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

    public void borrarPorPos(int pos){

        if(lista.size()==1){

            editor = preferences.edit().remove("listaToString");
            editor.commit();

        }else{

            lista.remove(pos);
            actualizarBD();
        }

        actualizarPantalla();

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

    public void salir(View view){

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


}