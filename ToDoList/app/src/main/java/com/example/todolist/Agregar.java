package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Agregar extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    EditText cuerpo;
    RelativeLayout paleta_layout;
    String cuerpoToString;
    String listaToString;

    String contenidoEdit;
    boolean modoEditor;
    int posColor;

    String separador = "&"; //separador entre notas
    String separadorColor = "#"; //separador nota-color

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addedit_layout);

        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = preferences.edit();
        cuerpo = (EditText)findViewById(R.id.cuerpo);
        paleta_layout = (RelativeLayout)findViewById(R.id.paleta_layout);

        listaToString = preferences.getString("listaToString",""); //lista de la base de datos
        contenidoEdit = getIntent().getStringExtra("contenidoEdit"); //valores que el usuario quiere editar (ejemplo#rojo)

        if(contenidoEdit != null){

            modoEditor = true;
            posColor = Integer.parseInt(contenidoEdit.substring(contenidoEdit.indexOf(separadorColor)+1)); //cogemos el color
            cuerpo.setText(contenidoEdit.substring(0,contenidoEdit.indexOf(separadorColor))); //cogemos el texto
        }else{

            posColor = 3;
            modoEditor = false;
        }

        String estilo = preferences.getString("estilo","Colores cálidos");
        Paleta paleta = new Paleta(this, posColor, estilo);
        paleta_layout.addView(paleta);

    }

    public void guardar(View view){

        cuerpoToString = cuerpo.getText().toString().trim();

        if(cuerpoToString.length() != 0){ //si hay algo escrito...

            if(modoEditor){ //si estamos en modo edición, reemplazamos

                cuerpoToString +=separadorColor; //le sumamos # a lo que ha puesto el usuario(ejemplo2#)
                cuerpoToString += Paleta.pos; //(ejemplo2#1)

                if(listaToString.contains(cuerpoToString)){
                    Toast.makeText(this, "Ya has escrito esa nota!!", Toast.LENGTH_SHORT).show();

                }else {

                    if(!caracterProhibido()){

                        reemplazarEnBD(view);
                    }else{

                        Toast.makeText(this, "Estos caracteres están restringidos: # &", Toast.LENGTH_SHORT).show();
                    }

                }

            }else{ //si no estamos en modo edición, añadimos

                cuerpoToString += separadorColor;
                cuerpoToString += Paleta.pos;

                if(listaToString.contains(cuerpoToString)){
                    Toast.makeText(this, "Ya has escrito esa nota!!", Toast.LENGTH_SHORT).show();

                }else {

                    if(!caracterProhibido()){

                        addToBD();
                    }else{

                        Toast.makeText(this, "Estos caracteres están restringidos: # &", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }else{ //si no hay nada escrito...

            cuerpo.setText(""); //si el usuario sólo ha puesto espacios en blanco, se los quitamos
            Toast.makeText(this, "Debes escribir una nota antes!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean caracterProhibido(){
        String contenidoUsuario = cuerpo.getText().toString().trim();

        if(contenidoUsuario.contains(separador) || contenidoUsuario.contains(separadorColor)){
            return true;
        }else{
            return false;
        }

    }

    private void addToBD() {
        listaToString = cuerpoToString + separador + listaToString; //añadimos lo que haya puesto el usuario y una coma

        editor.putString("listaToString", listaToString); //aplastamos la lista que había en configuración
        editor.commit();

        cuerpo.setText("");
        Toast toast = Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    private void reemplazarEnBD(View view) {
        String replaceInLista = listaToString.replace(contenidoEdit, cuerpoToString); //reemplazamos la info del intent (ejemplo#3) por lo que ha puesto el usuario (ejemplo2#2)

        replaceInLista = replaceInLista.replace(cuerpoToString + separador, ""); // borramos precisamente el nuevo edit para añadirlo al principio
        replaceInLista = cuerpoToString + separador + replaceInLista;

        editor.putString("listaToString", replaceInLista); //aplastamos la lista que había en configuración
        editor.commit();

        Toast.makeText(this, "Editado correctamente", Toast.LENGTH_SHORT).show();
        atras(view);
    }

    public void atras(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) { //botón atrás del menu de navegación del movil

        if(keyCode == KeyEvent.KEYCODE_BACK){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        }else{
            return super.onKeyUp(keyCode, event);
        }
    }
}
