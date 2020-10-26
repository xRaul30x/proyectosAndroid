package com.example.appradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aceptar = (Button)findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup lista = (RadioGroup)findViewById(R.id.lista);
                int idSeleccionado = lista.getCheckedRadioButtonId();
                RadioButton seleccionado = (RadioButton)findViewById(idSeleccionado);

                TextView respuesta = (TextView)findViewById(R.id.respuesta);
                respuesta.setText(seleccionado.getContentDescription());

                /*
                    Otra forma:

			    int idSeleccionado = lista.getCheckedRadioButtonId();

		        lista = (RadioButton) findViewById(idSeleccionado);

                Toast.makeText(MyAndroidAppActivity.this, lista.getText(), Toast.LENGTH_SHORT).show();
                 */
            }
        });
    }
}