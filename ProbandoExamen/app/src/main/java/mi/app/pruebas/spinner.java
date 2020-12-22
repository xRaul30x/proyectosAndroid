package mi.app.pruebas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class spinner extends AppCompatActivity {
    Spinner ciudades,paises;
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        paises = findViewById(R.id.paises);
        ciudades = findViewById(R.id.Ciudades);
        final Button volver= (Button) findViewById(R.id.volver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), MainActivity.class);
                startActivity(comunicacion);
            }
        });
        paises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] r;
                Log.d("DEPURANDO", "valor de position" + position);
                switch (position) {
                    case (0):
                        r = getResources().getStringArray(R.array.ESP);
                        ciudades.setAdapter(new ArrayAdapter<String>(spinner.this, android.R.layout.simple_spinner_dropdown_item, r));
                        break;
                    case (1):
                        r = getResources().getStringArray(R.array.GER);
                        ciudades.setAdapter(new ArrayAdapter<String>(spinner.this, android.R.layout.simple_spinner_dropdown_item, r));
                        break;
                    case (2):
                        r = getResources().getStringArray(R.array.BEL);
                        ciudades.setAdapter(new ArrayAdapter<String>(spinner.this, android.R.layout.simple_spinner_dropdown_item, r));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ciudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv = (TextView) findViewById(R.id.xd) ;
                tv.setText(ciudades.getSelectedItem().toString()) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
