package mi.app.pruebas;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class valores extends AppCompatActivity {

    String contraseña = "1234";
    String pass,nb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_autenticacion);


        final Button btnE = (Button) findViewById(R.id.btnE);

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Enb = (EditText)  findViewById(R.id.nombre);
                EditText Epass = (EditText)  findViewById(R.id.Pass1);
                nb = Enb.getText().toString();
                pass = Epass.getText().toString();
                if (pass.equals(contraseña)){
                    Intent comunicacion = new Intent(v.getContext(), MainActivity.class);
                    comunicacion.putExtra("nb",nb);
                    startActivity(comunicacion);
                }else {
                    TextView TV = (TextView) findViewById(R.id.TV3);
                    TV.setText("Contraseña incorrecta");
                    TextView TV2 = (TextView) findViewById(R.id.TV4);
                    TV2.setText("Prueba 1234");
                }

            }
        });
    }
}

