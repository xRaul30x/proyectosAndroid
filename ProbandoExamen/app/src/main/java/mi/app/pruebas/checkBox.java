package mi.app.pruebas;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class checkBox extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        TextView wtf = (TextView) findViewById(R.id.wtf);
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.lunes:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "Lunes añadido", Toast.LENGTH_SHORT).show();
                    break;
                } else break;
            case R.id.martes:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "martes añadido", Toast.LENGTH_SHORT).show();
                    break;
                } else break;
            case R.id.miercoles:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "miercoles añadido", Toast.LENGTH_SHORT).show();
                    break;
                } else break;
            case R.id.jueves:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "jueves añadido", Toast.LENGTH_SHORT).show();
                    break;
                } else break;
            case R.id.viernes:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "viernes añadido", Toast.LENGTH_SHORT).show();
                    break;
                } else break;
            case R.id.sabado:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "sabado añadido", Toast.LENGTH_SHORT).show();
                    break;
                } else break;
            case R.id.domingo:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "domingo añadido", Toast.LENGTH_SHORT).show();
                    wtf.setVisibility(View.VISIBLE);
                    break;
                } else {
                    wtf.setVisibility(View.INVISIBLE);
                    break;
                }
        }
    }
}
