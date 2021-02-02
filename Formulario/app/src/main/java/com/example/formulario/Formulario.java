package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Formulario extends AppCompatActivity {

    boolean error = true;
    EditText nacimientoEditText;
    int nacimiento;
    int edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Toast.makeText(getApplicationContext(), "DATOS CORRECTOS", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "DATOS INCORRECTOS", Toast.LENGTH_SHORT).show();
        }

    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        nacimientoEditText = (EditText)findViewById(R.id.nacimiento);
        nacimiento = Integer.parseInt(nacimientoEditText.getText().toString());
        edad = Calendar.getInstance().get(Calendar.YEAR)-nacimiento;

        switch(view.getId()) {
            case R.id.mayor:
                if (checked) {
                    if(edad > 17){
                        error = false;
                    }else{
                        error = true;
                        Toast toast = Toast.makeText(getApplicationContext(), "No eres mayor de edad!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    if(edad < 18){
                        error = false;
                    }else{
                        error = true;
                        Toast toast = Toast.makeText(getApplicationContext(), "Eres mayor de edad!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }


                break;
        }
    }

    public void guardarDatos(View view){
        boolean checked = ((CheckBox)findViewById(R.id.mayor)).isChecked();

        nacimientoEditText = (EditText)findViewById(R.id.nacimiento);
        nacimiento = Integer.parseInt(nacimientoEditText.getText().toString());
        edad = Calendar.getInstance().get(Calendar.YEAR)-nacimiento;

        if(!checked && edad < 18 || checked && edad > 17){
            error = false;
        }else{
            error = true;
        }

        if(!error){
            Intent intent = new Intent(view.getContext(), Respuesta.class);

            RadioGroup generos = (RadioGroup)findViewById(R.id.genero);
            int idSeleccionado = generos.getCheckedRadioButtonId();
            RadioButton hombreMujer = (RadioButton)findViewById(idSeleccionado);
            String genero = hombreMujer.getText().toString();

            String mayorDeEdad;
            if(edad>17) mayorDeEdad = "Sí"; else mayorDeEdad = "No";

            intent.putExtra("nombre", ((EditText)findViewById(R.id.nombre)).getText().toString());
            intent.putExtra("nacimiento", nacimientoEditText.getText().toString());
            intent.putExtra("genero", genero);
            intent.putExtra("mayorDeEdad", mayorDeEdad);

            startActivityForResult(intent,0);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Hay errores en el formulario", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}