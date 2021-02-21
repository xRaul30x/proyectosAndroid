package com.example.appradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
                //respuesta.setText(seleccionado.getContentDescription());

                respuesta.setText(seleccionado.getText().toString());


            }
        });
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioMatrix:
                if (checked) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Matrix clickeado", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }

                break;
        }
    }

}