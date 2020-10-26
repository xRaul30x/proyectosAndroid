package com.example.intentscronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////WEB CENTRO
        Button webCentro = (Button)findViewById(R.id.webCentro);
        webCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri paginaCentro = Uri.parse("https://palomatica.info/");
                Intent intent = new Intent(Intent.ACTION_VIEW, paginaCentro);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        ////////////////LLAMAR
        Button llamar = (Button)findViewById(R.id.llamaCentro);
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "913980300"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        /////////////RUTA
        Button ruta = (Button)findViewById(R.id.ruta);
        ruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 40.453151, -3.688405"));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });

        /////////////CONTACTAR
        Button contactos = (Button)findViewById(R.id.contactar);
        contactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Hola, insti!");
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Enviando mail");
                intent.putExtra(Intent.EXTRA_EMAIL,new String [] {"xraul30x@gmail.com"});

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });

        /////////////AGENDA
        Button agenda = (Button)findViewById(R.id.agenda);
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });


        /////////////CAMARA
        Button camara = (Button)findViewById(R.id.camara);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
            }
        });
    }
}