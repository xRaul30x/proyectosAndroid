package com.example.intentscronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crono = (TextView)findViewById(R.id.crono);

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
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 40.459418, -3.717462"));
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

                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA); //.ACTION_IMAGE_CAPTURE
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
            }
        });
    }

    int min=0,seg=0,mili=0;
    Thread hilo;
    Boolean encendido = false;
	TextView crono;
	
    public void iniciarCrono(View view) {
        Button iniciar = (Button)findViewById(R.id.iniciar);
        Button reiniciar = (Button)findViewById(R.id.reiniciar);

        if (iniciar.getText().equals("Iniciar"))
        {
            //-----------------------INICIAMOS EL CRONO
            encendido = true;
            hiloCrono(view);

            iniciar.setText("Parar");
        }
        else
        {
            //-----------------------PARAMOS EL CRONO
            encendido = false;

            reiniciar.setEnabled(true);
            iniciar.setEnabled(false);
        }

    }

    public void reiniciarCrono(View view)
    {
        crono = (TextView)findViewById(R.id.crono);

        Button iniciar = (Button)findViewById(R.id.iniciar);
        Button reiniciar = (Button)findViewById(R.id.reiniciar);

        //-----------------------INICIAMOS EL CRONO
        encendido = true;

        min=0;
        seg=0;
        mili=0;
        crono.setText("00:00:000");

        hiloCrono(view);
        iniciar.setEnabled(true); //text en modo PARAR
        reiniciar.setEnabled(false);
    }

    Handler h = new Handler(); // sirve para modificar el textview

    private void hiloCrono(View view) {

        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){ // while infinito
                    if(encendido){ // se activa la variable encendido si se presiona el boton iniciar
                        try {
                            Thread.sleep(1);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        mili++;
                        if(mili >= 999){
                            seg++;
                            mili=0;
                        }if(seg>=59){
                            min++;
                            seg=0;
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                String m="",s="",mi="";
                                if(mili<10){ //Modificar la variacion de los 0
                                    m="00"+mili;
                                }else if (mili<=100){
                                    m="0"+mili;
                                }else{
                                    m=""+mili;
                                }
                                if (seg<=10){
                                    s="0"+seg;
                                }else{
                                    s=""+seg;
                                }
                                if(min<=10){
                                    mi="0"+min;
                                }else{
                                    mi=""+min;
                                }
                                crono.setText(mi+":"+s+":"+m);
                            }
                        });
                    }
                }
            }
        });
        hilo.start();
    }
}